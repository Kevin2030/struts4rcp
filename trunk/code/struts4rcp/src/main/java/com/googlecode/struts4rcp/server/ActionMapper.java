package com.googlecode.struts4rcp.server;

import javax.servlet.http.HttpServletRequest;

import com.googlecode.struts4rcp.util.Shutdownable;
import com.googlecode.struts4rcp.util.serializer.Serializer;

/**
 * Action收接方案映射器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ActionMapper extends Shutdownable {

	/**
	 * 判断收接方案
	 * @param request 请求信息
	 * @return 收接器
	 */
	Serializer getSerializer(HttpServletRequest request);

	/**
	 * 获取Action名称
	 * @param request 请求信息
	 * @return Action名称
	 * @throws Exception 异常均向上抛出，由框架统一处理
	 */
	String getActionName(HttpServletRequest request);

}
