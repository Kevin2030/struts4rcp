package com.googlecode.struts4rcp.server;

import java.io.Serializable;
import java.util.List;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.util.Shutdownable;

/**
 * Action工厂SPI接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ActionProvider extends Shutdownable {

	/**
	 * 获取Action实例
	 * @param actionName Action名称
	 * @return Action实例
	 */
	Action<Serializable, Serializable> getAction(String actionName);

	/**
	 * 获取Action拦截器集合
	 * @return Action拦截器集合
	 */
	List<ActionInterceptor> getActionInterceptors();

}
