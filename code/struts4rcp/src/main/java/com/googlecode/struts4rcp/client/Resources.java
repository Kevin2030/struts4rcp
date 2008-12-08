package com.googlecode.struts4rcp.client;

import java.io.Serializable;


/**
 * 资源静态门面
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Resources {

	private Resources() {}

	/**
	 * 从默认客户端实例中，获取同步Resource代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @return 同步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getResource(
			String resourceName) {
		return getResource(null, resourceName);
	}

	/**
	 * 从默认客户端实例中，获取同步Resource代理，并设置可选参数
	 *
	 * @param resourceName resource名称
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 同步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getResource(
			String resourceName, boolean backable,
			boolean abortable) {
		return getResource(null, resourceName, backable,
				abortable);
	}

	/**
	 * 从默认客户端实例中，获取异步Resource代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @return 异步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getAsyncResource(
			String resourceName, Callback<R> resourceCallback) {
		return getAsyncResource(null, resourceName, resourceCallback);
	}

	/**
	 * 从默认客户端实例中，获取异步Resource代理，并设置可选参数
	 *
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 异步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getAsyncResource(
			String resourceName, Callback<R> resourceCallback,
			boolean backable, boolean abortable) {
		return getAsyncResource(null, resourceName, resourceCallback,
				backable, abortable);
	}

	/**
	 * 从默认客户端实例中，获取后台同步Resource代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @return 后台同步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getBackResource(String resourceName) {
		return getBackResource(null, resourceName);
	}

	/**
	 * 从默认客户端实例中，获取后台同步Resource代理，并设置可选参数
	 *
	 * @param resourceName
	 *            resource名称
	 * @param abortable 是否允许中止
	 * @return 后台同步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getBackResource(String resourceName, boolean abortable) {
		return getBackResource(null, resourceName, abortable);
	}

	/**
	 * 从默认客户端实例中，获取后台异步Resource代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @return 后台异步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getBackAsyncResource(String resourceName,
			Callback<R> resourceCallback) {
		return getBackAsyncResource(null, resourceName, resourceCallback);
	}

	/**
	 * 从默认客户端实例中，获取后台异步Resource代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @param abortable 是否允许中止
	 * @return 后台异步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getBackAsyncResource(String resourceName,
			Callback<R> resourceCallback, boolean abortable) {
		return getBackAsyncResource(null, resourceName, resourceCallback, abortable);
	}

	/**
	 * 从指定客户端实例中，获取同步Resource代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @return 同步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getResource(
			String clientName, String resourceName) {
		return getResource(clientName, resourceName);
	}

	/**
	 * 从指定客户端实例中，获取同步Resource代理，并设置可选参数
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName resource名称
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 同步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getResource(
			String clientName, String resourceName,
			boolean backable, boolean abortable) {
		return Client.getClient(clientName).getResourceFactory().getResource(resourceName,
				backable, abortable);
	}

	/**
	 * 从指定客户端实例中，获取异步Resource代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @return 异步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getAsyncResource(
			String clientName, String resourceName,
			Callback<R> resourceCallback) {
		return getAsyncResource(clientName, resourceName, resourceCallback);
	}

	/**
	 * 从指定客户端实例中，获取异步Resource代理，并设置可选参数
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 异步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getAsyncResource(
			String clientName, String resourceName,
			Callback<R> resourceCallback,
			boolean backable, boolean abortable) {
		return Client.getClient(clientName).getResourceFactory().getAsyncResource(resourceName,
				resourceCallback, backable, abortable);
	}

	/**
	 * 从指定客户端实例中，获取后台同步Resource代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @return 后台同步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getBackResource(
			String clientName, String resourceName) {
		return getBackResource(clientName, resourceName);
	}

	/**
	 * 从指定客户端实例中，获取后台同步Resource代理，并设置可选参数
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @param abortable 是否允许中止
	 * @return 后台同步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getBackResource(
			String clientName, String resourceName, boolean abortable) {
		return Client.getClient(clientName).getResourceFactory().getBackResource(resourceName, abortable);
	}

	/**
	 * 从指定客户端实例中，获取后台异步Resource代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @return 后台异步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getBackAsyncResource(
			String clientName, String resourceName,
			Callback<R> resourceCallback) {
		return getBackAsyncResource(clientName, resourceName, resourceCallback);
	}

	/**
	 * 从指定客户端实例中，获取后台异步Resource代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @param abortable 是否允许中止
	 * @return 后台异步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getBackAsyncResource(
			String clientName, String resourceName,
			Callback<R> resourceCallback, boolean abortable) {
		return Client.getClient(clientName).getResourceFactory().getBackAsyncResource(resourceName,
				resourceCallback, abortable);
	}

	// batch ----------


	/**
	 * 从默认客户端实例中，获取同步Directory代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @return 同步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getDirectory(
			String resourceName) {
		return getDirectory(null, resourceName);
	}

	/**
	 * 从默认客户端实例中，获取同步Directory代理，并设置可选参数
	 *
	 * @param resourceName resource名称
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 同步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getDirectory(
			String resourceName, boolean backable,
			boolean abortable) {
		return getDirectory(null, resourceName, backable,
				abortable);
	}

	/**
	 * 从默认客户端实例中，获取异步Directory代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @return 异步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getAsyncDirectory(
			String resourceName, Callback<R[]> resourceCallback) {
		return getAsyncDirectory(null, resourceName, resourceCallback);
	}

	/**
	 * 从默认客户端实例中，获取异步Directory代理，并设置可选参数
	 *
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 异步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getAsyncDirectory(
			String resourceName, Callback<R[]> resourceCallback,
			boolean backable, boolean abortable) {
		return getAsyncDirectory(null, resourceName, resourceCallback,
				backable, abortable);
	}

	/**
	 * 从默认客户端实例中，获取后台同步Directory代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @return 后台同步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getBackDirectory(String resourceName) {
		return getBackDirectory(null, resourceName);
	}

	/**
	 * 从默认客户端实例中，获取后台同步Directory代理，并设置可选参数
	 *
	 * @param resourceName
	 *            resource名称
	 * @param abortable 是否允许中止
	 * @return 后台同步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getBackDirectory(String resourceName, boolean abortable) {
		return getBackDirectory(null, resourceName, abortable);
	}

	/**
	 * 从默认客户端实例中，获取后台异步Directory代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @return 后台异步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getBackAsyncDirectory(String resourceName,
			Callback<R[]> resourceCallback) {
		return getBackAsyncDirectory(null, resourceName, resourceCallback);
	}

	/**
	 * 从默认客户端实例中，获取后台异步Directory代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @param abortable 是否允许中止
	 * @return 后台异步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getBackAsyncDirectory(String resourceName,
			Callback<R[]> resourceCallback, boolean abortable) {
		return getBackAsyncDirectory(null, resourceName, resourceCallback, abortable);
	}

	/**
	 * 从指定客户端实例中，获取同步Directory代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @return 同步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getDirectory(
			String clientName, String resourceName) {
		return getDirectory(clientName, resourceName);
	}

	/**
	 * 从指定客户端实例中，获取同步Directory代理，并设置可选参数
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName resource名称
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 同步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getDirectory(
			String clientName, String resourceName,
			boolean backable, boolean abortable) {
		return Client.getClient(clientName).getResourceFactory().getDirectory(resourceName,
				backable, abortable);
	}

	/**
	 * 从指定客户端实例中，获取异步Directory代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @return 异步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getAsyncDirectory(
			String clientName, String resourceName,
			Callback<R[]> resourceCallback) {
		return getAsyncDirectory(clientName, resourceName, resourceCallback);
	}

	/**
	 * 从指定客户端实例中，获取异步Directory代理，并设置可选参数
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 异步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getAsyncDirectory(
			String clientName, String resourceName,
			Callback<R[]> resourceCallback,
			boolean backable, boolean abortable) {
		return Client.getClient(clientName).getResourceFactory().getAsyncDirectory(resourceName,
				resourceCallback, backable, abortable);
	}

	/**
	 * 从指定客户端实例中，获取后台同步Directory代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @return 后台同步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getBackDirectory(
			String clientName, String resourceName) {
		return getBackDirectory(clientName, resourceName);
	}

	/**
	 * 从指定客户端实例中，获取后台同步Directory代理，并设置可选参数
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @param abortable 是否允许中止
	 * @return 后台同步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getBackDirectory(
			String clientName, String resourceName, boolean abortable) {
		return Client.getClient(clientName).getResourceFactory().getBackDirectory(resourceName, abortable);
	}

	/**
	 * 从指定客户端实例中，获取后台异步Directory代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @return 后台异步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getBackAsyncDirectory(
			String clientName, String resourceName,
			Callback<R[]> resourceCallback) {
		return getBackAsyncDirectory(clientName, resourceName, resourceCallback);
	}

	/**
	 * 从指定客户端实例中，获取后台异步Directory代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param resourceName
	 *            resource名称
	 * @param resourceCallback
	 *            回调接口
	 * @param abortable 是否允许中止
	 * @return 后台异步Directory代理
	 */
	public static <R extends Serializable> Directory<R> getBackAsyncDirectory(
			String clientName, String resourceName,
			Callback<R[]> resourceCallback, boolean abortable) {
		return Client.getClient(clientName).getResourceFactory().getBackAsyncDirectory(resourceName,
				resourceCallback, abortable);
	}


}
