package com.googlecode.struts4rcp.client;

import java.io.Serializable;

/**
 * RESTful远程资源(CRUD)接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public interface Resource<R extends Serializable> {

	/**
	 * 创建资源(注：标识性属性值(如：ID值)无效，在服务器端接收时，将被替换为资源URI所指定的值)
	 * @param resource 资源信息
	 * @throws Exception 创建失败或网络连接出错时抛出
	 */
	void create(R resource) throws Exception;

	/**
	 * 获取资源
	 * @return 资源
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	R read() throws Exception;

	/**
	 * 更新资源(注：标识性属性值(如：ID值)无效，在服务器端接收时，将被替换为资源URI所指定的值)
	 * @param resource 资源信息
	 * @throws Exception 更新失败或网络连接出错时抛出
	 */
	void update(R resource) throws Exception;

	/**
	 * 删除资源
	 * @throws Exception 删除失败或网络连接出错时抛出
	 */
	void delete() throws Exception;

	/**
	 * 获取资源所在目录，通过HEAD请求查找
	 * @return 资源所在目录
	 */
	Directory<R> getDirectory() throws Exception;

}