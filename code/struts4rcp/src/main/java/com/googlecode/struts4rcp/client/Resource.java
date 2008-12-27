package com.googlecode.struts4rcp.client;

import java.io.Serializable;

/**
 * 单一资源接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public interface Resource<R extends Serializable> extends Serializable {

	/**
	 * 获取资源URI
	 * @return 资源URI
	 */
	String uri();

	/**
	 * 清除缓存
	 */
	void flush();

	/**
	 * 检测资源是否存
	 * @return 资源是否存
	 * @throws Exception 检测失败或网络连接出错时抛出
	 */
	boolean exist() throws Exception;

	/**
	 * 获取资源
	 * @return 资源
	 * @throws Exception 获取失败或网络连接出错时抛出
	 */
	R get() throws Exception;

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

}