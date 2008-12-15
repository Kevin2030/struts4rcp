package com.googlecode.struts4rcp.client;

import java.io.Serializable;

import com.googlecode.struts4rcp.internal.ResourceRequest;

/**
 * 资源集合
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 * @param <S> 接收表形类型(将发送对应的Accept头信息)
 */
public interface Resources<R extends Serializable> extends Serializable {

	/**
	 * 不跳过
	 */
	public static final int NOSKIP = ResourceRequest.NOSKIP;

	/**
	 * 不限制个数
	 */
	public static final int LIMITLESS = ResourceRequest.LIMITLESS;

	/**
	 * 获取资源集合URI
	 * @return 资源集合URI
	 */
	String getURI();

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

}