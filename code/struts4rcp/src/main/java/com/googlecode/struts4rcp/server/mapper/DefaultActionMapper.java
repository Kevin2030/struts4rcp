package com.googlecode.struts4rcp.server.mapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.googlecode.struts4rcp.server.ActionMapper;
import com.googlecode.struts4rcp.server.ActionServletContext;
import com.googlecode.struts4rcp.server.ServletSerializer;
import com.googlecode.struts4rcp.server.serializer.StreamServletSerializer;
import com.googlecode.struts4rcp.server.serializer.TextServletSerializer;
import com.googlecode.struts4rcp.util.ServiceUtils;
import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;
import com.googlecode.struts4rcp.util.serializer.Serializer;
import com.googlecode.struts4rcp.util.serializer.stream.StreamSerializer;
import com.googlecode.struts4rcp.util.serializer.text.TextSerializer;

/**
 * 后缀Action映射器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class DefaultActionMapper implements ActionMapper {

	/**
	 * 日志输出接口
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected final Map<String, ServletSerializer> serializers = new HashMap<String, ServletSerializer>();

	private boolean directoryMapping;

	private boolean extensionMapping;

	public DefaultActionMapper() {
		init(ActionServletContext.getContext().getServletContext(), ActionServletContext.getContext().getServletConfig());
	}

	@SuppressWarnings("unchecked")
	protected void init(ServletContext servletContext, ServletConfig servletConfig) {
		directoryMapping = isDirectoryMapping(servletContext, servletConfig);
		extensionMapping = isExtensionMapping(servletContext, servletConfig);
		Collection<Serializer> serializerInstances = ServiceUtils.getServiceInstances(Serializer.class);
		for (Serializer serializer : serializerInstances) {
			String contentType = serializer.getContentType();
			if (contentType != null)
				contentType = contentType.toLowerCase();
			if (serializer instanceof ServletSerializer)
				serializers.put(contentType, (ServletSerializer)serializer);
			else if (serializer instanceof TextSerializer)
				serializers.put(contentType, new TextServletSerializer((TextSerializer)serializer));
			else if (serializer instanceof StreamSerializer)
				serializers.put(contentType, new StreamServletSerializer((StreamSerializer)serializer));
		}
	}

	public void shutdown() {}

	/**
	 * 目录映射的配置参数名
	 */
	protected static final String DIRECTORY_MAPPING_PARAM_NAME = "directoryMapping";

	/**
	 * 缺省后缀映射为false
	 */
	protected static final boolean DEFAULT_DIRECTORY_MAPPING = false;

	/**
	 * 是否为目录映射
	 * @return 是否为目录映射
	 */
	protected boolean isDirectoryMapping(ServletContext servletContext, ServletConfig servletConfig) {
		String directoryMappingConfig = servletConfig.getInitParameter(DIRECTORY_MAPPING_PARAM_NAME);
		if (directoryMappingConfig == null)
			return DEFAULT_DIRECTORY_MAPPING;
		directoryMappingConfig = directoryMappingConfig.trim();
		if (directoryMappingConfig.length() == 0)
			return DEFAULT_DIRECTORY_MAPPING;
		return "true".equals(directoryMappingConfig);
	}

	/**
	 * 后缀映射的配置参数名
	 */
	protected static final String EXTENSION_MAPPING_PARAM_NAME = "extensionMapping";

	/**
	 * 缺省后缀映射为true
	 */
	protected static final boolean DEFAULT_EXTENSION_MAPPING = true;

	/**
	 * 是否为后缀映射
	 * @return 是否为后缀映射
	 */
	protected boolean isExtensionMapping(ServletContext servletContext, ServletConfig servletConfig) {
		String extensionMappingConfig = servletConfig.getInitParameter(EXTENSION_MAPPING_PARAM_NAME);
		if (extensionMappingConfig == null)
			return DEFAULT_EXTENSION_MAPPING;
		extensionMappingConfig = extensionMappingConfig.trim();
		if (extensionMappingConfig.length() == 0)
			return DEFAULT_EXTENSION_MAPPING;
		return "true".equals(extensionMappingConfig);
	}

	/**
	 * 获取Action名称
	 * @param request 请求信息
	 * @return Action名称
	 */
	public String getActionName(HttpServletRequest request) {
		String actionName = request.getRequestURI();
		String contextPath = request.getContextPath();
		if (contextPath != null && ! "/".equals(contextPath))
			actionName = actionName.substring(contextPath.length());
		if (actionName.startsWith("/"))
			actionName = actionName.substring(1);
		if (directoryMapping) {
			int directoryIndex = actionName.indexOf('/');
			if (directoryIndex != -1)
				actionName = actionName.substring(directoryIndex + 1);
		}
		if (extensionMapping) {
			int extensionIndex = actionName.lastIndexOf('.');
			if (extensionIndex != -1)
				actionName = actionName.substring(0, extensionIndex);
		}
		return actionName;
	}

	public ServletSerializer getSerializer(HttpServletRequest request) {
		ServletSerializer serializer = getSerializer(request.getHeader("Accept")); // 根据接收类型查找
		if (serializer == null)
			serializer = getSerializer(request.getContentType()); // 如果没有设定接收类型，使用请求类型查找
		return serializer;
	}

	protected ServletSerializer getSerializer(String contentType) {
		if (contentType != null)
			contentType = contentType.trim().toLowerCase();
		return serializers.get(contentType);
	}

	protected ServletSerializer addSerializer(String contentType, ServletSerializer serializer) {
		return serializers.put(contentType, serializer);
	}

	protected ServletSerializer removeSerializer(String contentType) {
		return serializers.remove(contentType);
	}

}
