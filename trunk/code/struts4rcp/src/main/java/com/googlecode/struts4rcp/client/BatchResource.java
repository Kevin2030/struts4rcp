package com.googlecode.struts4rcp.client;

import java.io.Serializable;

/**
 * 批量资源
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 * @param <C> 条件类型
 */
public interface BatchResource<R extends Serializable, C extends Serializable> extends Resource<R[]> {

	/**
	 * 根据条件移除资源
	 * @param condition 条件
	 * @throws Exception 移除失败或网络连接出错时抛出
	 */
	void remove(C condition) throws Exception;

	/**
	 * 根据条件获取资源
	 * @param condition 条件
	 * @return 资源
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	R[] get(C condition) throws Exception;

}
