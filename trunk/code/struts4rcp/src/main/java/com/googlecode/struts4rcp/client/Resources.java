package com.googlecode.struts4rcp.client;

import java.io.Serializable;

/**
 * 资源集合接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public interface Resources<R extends Serializable> extends Serializable {

	/**
	 * 不跳过
	 */
	public static final int NOSKIP = 0;

	/**
	 * 不限制个数
	 */
	public static final int LIMITLESS = 0;

	/**
	 * 获取资源集合URI
	 * @return 资源集合URI
	 */
	String uri();

	/**
	 * 清除缓存
	 */
	void flush();

	/**
	 * 统计资源个数
	 * @return 资源个数
	 * @throws Exception 统计失败或网络连接出错时抛出
	 */
	long count() throws Exception;

	/**
	 * 统计资源个数
	 * @param condition 过滤条件
	 * @return 资源个数
	 * @throws Exception 统计失败或网络连接出错时抛出
	 */
	long count(R condition) throws Exception;

	/**
	 * 获取目录中所有的资源
	 * @return 资源列表
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	Resource<R>[] list() throws Exception;

	/**
	 * 分页获取目录中所有的资源
	 * @param skip 跳过个数
	 * @param limit 限制个数
	 * @return 资源列表
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	Resource<R>[] list(long skip, long limit) throws Exception;

	/**
	 * 获取目录中所有的资源
	 * @param condition 过滤条件
	 * @return 资源列表
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	Resource<R>[] list(R condition) throws Exception;

	/**
	 * 分页获取目录中所有的资源
	 * @param condition 过滤条件
	 * @param skip 跳过个数
	 * @param limit 限制个数
	 * @return 资源列表
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	Resource<R>[] list(R condition, long skip, long limit) throws Exception;

	/**
	 * 创建资源
	 * @param resource 资源信息
	 * @throws Exception 创建失败或网络连接出错时抛出
	 */
	Resource<R> create(R resource) throws Exception;

	/**
	 * 删除所有资源
	 * @throws Exception 删除失败或网络连接出错时抛出
	 */
	void clear() throws Exception;

}