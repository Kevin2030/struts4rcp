package com.googlecode.struts4rcp.client;

import java.io.Serializable;

/**
 * 资源目录
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public interface Directory<R extends Serializable> {

	/**
	 * 获取资源目录URI
	 * @return 资源目录URI
	 */
	String getURI();

	/**
	 * 获取目录中所有的资源
	 * @return 资源
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	R[] index() throws Exception;

	/**
	 * 获取目录中匹配的资源
	 * @param resource 匹配条件(如果条件复杂，可以传入资源类型的子类作为条件)
	 * @return 资源
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	R[] index(R resource) throws Exception;

	/**
	 * 创建资源(注：标识性属性值(如：ID值)无效，在服务器端接收时，将被替换为资源URI所指定的值)
	 * @param resource 资源信息
	 * @throws Exception 创建失败或网络连接出错时抛出
	 */
	Resource<R> create(R resource) throws Exception;

}