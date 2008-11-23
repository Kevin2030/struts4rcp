package com.googlecode.struts4rcp.server.resolver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.googlecode.struts4rcp.server.ActionResolver;
import com.googlecode.struts4rcp.server.ActionServletContext;
import com.googlecode.struts4rcp.server.serializer.JsonSerializer;
import com.googlecode.struts4rcp.server.serializer.JspSerializer;
import com.googlecode.struts4rcp.util.ClassUtils;
import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;
import com.googlecode.struts4rcp.util.serializer.JavaSerializer;
import com.googlecode.struts4rcp.util.serializer.Serializer;

/**
 * 基于请求后缀的决策方案
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class SuffixActionResolver implements ActionResolver {

	/**
	 * 日志输出接口
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public SuffixActionResolver() {
		init(ActionServletContext.getContext().getServletContext(), ActionServletContext.getContext().getServletConfig());
	}

	protected void init(ServletContext servletContext, ServletConfig servletConfig) {
		// 加载序列化器
		Map<String, Serializer> serializers = getSerializers(servletContext, servletConfig);
		if (serializers != null && serializers.size() > 0) {
			for (Map.Entry<String, Serializer> entry : serializers.entrySet()) {
				addSerializer(entry.getKey(), entry.getValue());
			}
		}
	}

	public Serializer getSerializer(HttpServletRequest request) {
		String suffix = getSuffix(request);
		Serializer serializer = getSerializer(suffix);
		if (serializer == null) { // 注册缺省序列化器
			serializer = getDefaultSerializer(suffix);
			if (serializer != null) {
				addSerializer(suffix, serializer);
			}
		}
		if (serializer == null) // 接收器不允许为空
			throw new NullPointerException("Not found Serializer by request: " + request.getRequestURI());
		return serializer;
	}

	protected String getSuffix(HttpServletRequest request) {
		String uri = request.getRequestURI();
		int suffixIndex = uri.lastIndexOf('.');
		if (suffixIndex < 0 || suffixIndex >= uri.length()) // 后缀不能为空
			throw new NullPointerException("Action suffix is required!");
		return uri.substring(suffixIndex + 1);
	}

	/**
	 * 获取缺省序列化器
	 * @param suffix 后缀
	 * @return 序列化器
	 */
	protected Serializer getDefaultSerializer(String suffix) {
		if ("data".equals(suffix)) {
			return new JavaSerializer();
		} else if ("json".equals(suffix)) {
			return new JsonSerializer();
		} else if ("page".equals(suffix)) {
			return new JspSerializer();
		}
		return null;
	}

	// 接收器
	private final Map<String, Serializer> serializers = new HashMap<String, Serializer>();

	/**
	 * 获取序列化器
	 * @param suffix 序列化器名
	 * @return 序列化器
	 */
	protected Serializer getSerializer(String suffix) {
		synchronized (serializers) {
			return serializers.get(suffix);
		}
	}

	/**
	 * 添加序列化器
	 * @param suffix 序列化器名
	 * @param serializer 序列化器
	 */
	protected void addSerializer(String suffix, Serializer serializer) {
		synchronized (serializers) {
			serializers.put(suffix, serializer);
		}
	}

	/**
	 * 移除序列化器
	 * @param suffix 序列化器名
	 */
	protected void removeSerializer(String suffix) {
		synchronized (serializers) {
			serializers.remove(suffix);
		}
	}

	public void shutdown() {
		synchronized (serializers) {
			serializers.clear();
		}
	}

	/**
	 * serializers的配置参数名
	 */
	protected static final String SERIALIZERS_PARAM_NAME = "serializers";

	/**
	 * 加载序列化器，子类可通过覆写该方法，拦截加载方式
	 * @return 序列化器
	 * @throws Exception 异常均向上抛出，由框架统一处理
	 */
	protected Map<String, Serializer> getSerializers(ServletContext servletContext, ServletConfig servletConfig) {
		// 加载序列化器
		Map<String, Serializer> serializers = new HashMap<String, Serializer>();
		String serializersConfig = servletConfig.getInitParameter("serializers");
		if (serializersConfig != null && serializersConfig.trim().length() > 0) {
			String[] serializerItems = serializersConfig.split("\\,");
			for (String serializerItem : serializerItems) {
				if (serializerItem != null && serializerItem.trim().length() > 0) {
					String[] serializerEntry = serializerItem.split("\\:");
					if (serializerEntry.length == 2) {
						String serializerKey = serializerEntry[0];
						String serializerClassName = serializerEntry[1];
						if (serializerKey != null && serializerKey.trim().length() > 0
								&& serializerClassName != null && serializerClassName.trim().length() > 0) {
							try {
								Class<?> serializerClass = ClassUtils.forName(serializerClassName.trim());
								if (Serializer.class.isAssignableFrom(serializerClass)) {
									Serializer serializer = (Serializer)serializerClass.newInstance();
									serializers.put(serializerKey.trim(), serializer);
								} else {
									logger.error(serializerClass.getName() + " unimplementet interface " + Serializer.class.getName());
								}
							} catch (Exception e) {
								logger.error(e.getMessage(), e);
							}
						}
					}
				}
			}
		}
		return serializers;
	}

	public String getActionName(HttpServletRequest request) {
		String actionName = request.getRequestURI();
		String context = request.getContextPath();
		if (context != null && ! "/".equals(context))
			actionName = actionName.substring(context.length());
		if (actionName.startsWith("/"))
			actionName = actionName.substring(1);
		int suffixIndex = actionName.lastIndexOf('.');
		if (suffixIndex > 0)
			actionName = actionName.substring(0, suffixIndex);
		return actionName;
	}

}
