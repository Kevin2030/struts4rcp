package com.googlecode.struts4rcp.server.mapper;

import javax.servlet.http.HttpServletRequest;

/**
 * 后缀Action映射器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class SuffixActionMapper extends AbstractActionMapper {

	/**
	 * 获取序列化器的名称
	 * @param request 请求信息
	 * @return 序列化器名称
	 */
	protected String getSerializerName(HttpServletRequest request) {
		String uri = request.getRequestURI();
		int suffixIndex = uri.lastIndexOf('.');
		if (suffixIndex == -1) // 后缀不能为空
			throw new NullPointerException("Action suffix is required!");
		return uri.substring(suffixIndex + 1);
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
		int suffixIndex = actionName.lastIndexOf('.');
		if (suffixIndex != -1)
			actionName = actionName.substring(0, suffixIndex);
		return actionName;
	}

}
