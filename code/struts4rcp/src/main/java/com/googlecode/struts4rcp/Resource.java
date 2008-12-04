package com.googlecode.struts4rcp;

import java.io.Serializable;

/**
 * RESTful资源接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public interface Resource<R extends Serializable> {

	/**
	 * 创建资源
	 * @param resource 资源信息
	 * @throws Exception 创建失败或网络连接出错时抛出
	 */
	void create(R resource) throws Exception;

	/**
	 * 更新资源
	 * @param resource 资源信息
	 * @throws Exception 更新失败或网络连接出错时抛出
	 */
	void update(R resource) throws Exception;

	/**
	 * 移除资源
	 * @throws Exception 移除失败或网络连接出错时抛出
	 */
	void remove() throws Exception;

	/**
	 * 获取资源
	 * @return 资源
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	R get() throws Exception;

}