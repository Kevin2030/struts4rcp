package com.googlecode.struts4rcp.client;

import java.io.Serializable;

/**
 * 批量资源
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 * @param <C> 条件类型
 */
public interface BatchResource<R extends Serializable> extends Resource<R[]> {

	/**
	 * 删除匹配的资源
	 * @param resource 匹配条件(如果条件复杂，可以传入资源类型的子类作为条件)
	 * @throws Exception 删除失败或网络连接出错时抛出
	 */
	void delete(R resource) throws Exception;

	/**
	 * 获取匹配的资源
	 * @param resource 匹配条件(如果条件复杂，可以传入资源类型的子类作为条件)
	 * @return 资源
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	R[] get(R resource) throws Exception;

}
