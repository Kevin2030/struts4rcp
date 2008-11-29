package com.googlecode.struts4rcp.server;

import java.util.List;

import com.googlecode.struts4rcp.ActionFactory;
import com.googlecode.struts4rcp.ActionInterceptor;
import com.googlecode.struts4rcp.util.Shutdownable;

/**
 * Action工厂SPI接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ActionProvider extends ActionFactory, Shutdownable {

	/**
	 * 获取Action拦截器集合
	 * @return Action拦截器集合
	 */
	List<ActionInterceptor> getActionInterceptors();

}
