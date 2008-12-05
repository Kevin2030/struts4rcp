package com.googlecode.struts4rcp.server;

import javax.servlet.http.HttpServletRequest;

/**
 * Action接收映射接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ActionMapper {

	/**
	 * 获取序列化器
	 * @param request 请求信息
	 * @return 序列化器
	 */
	ServletSerializer getSerializer(HttpServletRequest request);

	/**
	 * 获取Action名称
	 * @param request 请求信息
	 * @return Action名称
	 */
	String getActionName(HttpServletRequest request);

}
