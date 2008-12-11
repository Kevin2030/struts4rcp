package com.googlecode.struts4rcp.client;

import java.io.Serializable;

/**
 * 资源集合
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 * @param <S> 接收表形类型(将发送对应的Accept头信息)
 */
public interface Resources<R extends Serializable> extends Serializable {

	/**
	 * 获取资源目录URI
	 * @return 资源目录URI
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
	 * @return 资源个数
	 * @throws Exception 统计失败或网络连接出错时抛出
	 */
	long count(R resource) throws Exception;

	/**
	 * 获取目录中所有的资源
	 * @return 资源列表
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	Resource<R>[] index() throws Exception;

	/**
	 * 分页获取目录中所有的资源
	 * @param start 起始
	 * @param limit 个数
	 * @return 资源列表
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	Resource<R>[] index(long start, long limit) throws Exception;

	/**
	 * 获取目录中所有的资源
	 * @return 资源列表
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	Resource<R>[] index(R resource) throws Exception;

	/**
	 * 分页获取目录中所有的资源
	 * @param start 起始
	 * @param limit 个数
	 * @return 资源列表
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	Resource<R>[] index(R resource, long start, long limit) throws Exception;

	/**
	 * 创建资源(注：标识性属性值(如：ID值)无效，在服务器端接收时，将被替换为资源URI所指定的值)
	 * @param resource 资源信息
	 * @throws Exception 创建失败或网络连接出错时抛出
	 */
	Resource<R> create(R resource) throws Exception;

}