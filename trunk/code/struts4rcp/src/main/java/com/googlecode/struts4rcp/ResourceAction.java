package com.googlecode.struts4rcp;

import java.io.Serializable;

/**
 * 资源(CRUD)Action接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 * @param <I> 资源ID类型
 */
public interface ResourceAction<R extends Serializable, I extends Serializable> {

	/**
	 * 查找所有资源
	 * @return 资源列表
	 * @throws Exception 查找失败或网络连接出错时抛出
	 */
	R[] list() throws Exception;

	/**
	 * 分页所有资源
	 * @param start 起始
	 * @param limit 个数
	 * @return 资源列表
	 * @throws Exception 查找失败或网络连接出错时抛出
	 */
	R[] list(int start, int limit) throws Exception;

	/**
	 * 查找资源
	 * @param condition 查找条件
	 * @return 资源列表
	 * @throws Exception 查找失败或网络连接出错时抛出
	 */
	R[] list(R condition) throws Exception;

	/**
	 * 分页查找资源
	 * @param condition 查找条件
	 * @param start 起始
	 * @param limit 个数
	 * @return 资源列表
	 * @throws Exception 查找失败或网络连接出错时抛出
	 */
	R[] list(R condition, int start, int limit) throws Exception;

	/**
	 * 创建资源
	 * @param resource 资源信息
	 * return 资源ID
	 * @throws Exception 创建失败或网络连接出错时抛出
	 */
	I create(R resource) throws Exception;

	/**
	 * 读取资源
	 * @param 资源ID
	 * @return 资源
	 * @throws Exception 读取失败或网络连接出错时抛出
	 */
	R read(I id) throws Exception;

	/**
	 * 更新资源
	 * @param resource 资源信息
	 * @throws Exception 更新失败或网络连接出错时抛出
	 */
	void update(R resource) throws Exception;

	/**
	 * 删除资源
	 * @param 资源ID
	 * @throws Exception 删除失败或网络连接出错时抛出
	 */
	void delete(I id) throws Exception;

}