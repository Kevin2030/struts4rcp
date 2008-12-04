package com.googlecode.struts4rcp.client;

import java.io.Serializable;
import java.util.Properties;

import com.googlecode.struts4rcp.Resource;

/**
 * 资源代理工厂
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ResourceFactory implements ClientElement {

	private Client client;

	public Client getClient() {
		return client;
	}

	public void init(Client client, Properties properties) {
		this.client = client;
	}

	public void shutdown() {
	}

	/**
	 * 获取资源代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @return 资源
	 */
	public <R extends Serializable> Resource<R> getResource(String uri) {
		return null;
	}

	/**
	 * 获取异步资源代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @param callback 获取结果的回调接口
	 * @return 资源
	 */
	public <R extends Serializable> Resource<R> getAsyncResource(String uri, Callback<R> callback) {
		return null;
	}

	/**
	 * 获取资源代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 资源
	 */
	public <R extends Serializable> Resource<R> getResource(String uri, boolean backable, boolean abortable) {
		return null;
	}

	/**
	 * 获取异步资源代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @param callback 获取结果的回调接口
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 资源
	 */
	public <R extends Serializable> Resource<R> getAsyncResource(String uri, Callback<R> callback, boolean backable, boolean abortable) {
		return null;
	}

	/**
	 * 获取后台资源代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @return 资源
	 */
	public <R extends Serializable> Resource<R> getBackResource(String uri) {
		return null;
	}

	/**
	 * 获取后台异步资源代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @param callback 获取结果的回调接口
	 * @return 资源
	 */
	public <R extends Serializable> Resource<R> getBackAsyncResource(String uri, Callback<R> callback) {
		return null;
	}

	/**
	 * 获取后台资源代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @param abortable 是否允许中止
	 * @return 资源
	 */
	public <R extends Serializable> Resource<R> getBackResource(String uri, boolean abortable) {
		return null;
	}

	/**
	 * 获取后台异步资源代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @param callback 获取结果的回调接口
	 * @param abortable 是否允许中止
	 * @return 资源
	 */
	public <R extends Serializable> Resource<R> getBackAsyncResource(String uri, Callback<R> callback, boolean abortable) {
		return null;
	}

}
