package com.googlecode.struts4rcp.server;

import javax.servlet.http.HttpServletRequest;

import com.googlecode.struts4rcp.util.serializer.Serializer;

/**
 * Action名称映射器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ActionMapper {

	/**
	 * 获取序列化器
	 * @param request 请求信息
	 * @return 序列化器
	 */
	Serializer getSerializer(HttpServletRequest request);

	/**
	 * 获取Action名称
	 * @param request 请求信息
	 * @return Action名称
	 */
	String getActionName(HttpServletRequest request);

}
