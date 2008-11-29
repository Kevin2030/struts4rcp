package com.googlecode.struts4rcp;

import java.io.Serializable;

/**
 * Action工厂接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ActionFactory {

	/**
	 * 获取Action实例
	 * @param <M> 传入数据模型类型
	 * @param <R> 返回数据模型类型
	 * @param actionName Action名称
	 * @return Action实例
	 */
	<M extends Serializable, R extends Serializable> Action<M, R> getAction(String actionName);

}
