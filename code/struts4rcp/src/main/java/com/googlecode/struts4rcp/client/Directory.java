package com.googlecode.struts4rcp.client;

import java.io.Serializable;

/**
 * 资源目录
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 * @param <C> 条件类型
 */
public interface Directory<R extends Serializable> {

	/**
	 * 获取目录中所有的资源
	 * @return 资源
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	R[] find() throws Exception;

	/**
	 * 获取目录中匹配的资源
	 * @param resource 匹配条件(如果条件复杂，可以传入资源类型的子类作为条件)
	 * @return 资源
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	R[] find(R resource) throws Exception;

}
