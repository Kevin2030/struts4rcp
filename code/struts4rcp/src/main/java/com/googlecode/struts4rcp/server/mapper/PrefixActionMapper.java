package com.googlecode.struts4rcp.server.mapper;

import javax.servlet.http.HttpServletRequest;

/**
 * 前缀Action映射器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class PrefixActionMapper extends AbstractActionMapper {

	/**
	 * 获取序列化器的名称
	 * @param request 请求信息
	 * @return 序列化器名称
	 */
	protected String getSerializerName(HttpServletRequest request) {
		String serializerName = request.getRequestURI();
		String contextPath = request.getContextPath();
		if (contextPath != null && ! "/".equals(contextPath))
			serializerName = serializerName.substring(contextPath.length());
		if (serializerName.startsWith("/"))
			serializerName = serializerName.substring(1);
		int prefixIndex = serializerName.indexOf('/');
		if (prefixIndex == -1) // 前缀不能为空
			throw new NullPointerException("Action prefix is required!");
		return serializerName.substring(0, prefixIndex);
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
		int prefixIndex = actionName.indexOf('/');
		if (prefixIndex != -1)
			actionName = actionName.substring(prefixIndex + 1);
		return actionName;
	}

}