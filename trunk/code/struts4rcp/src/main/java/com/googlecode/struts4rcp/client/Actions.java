package com.googlecode.struts4rcp.client;

import java.io.Serializable;

import com.googlecode.struts4rcp.Action;

/**
 * 客户端Action客户端静态门面
 *
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Actions {

	// 静态门面，私有化构造函数，禁止实例化
	private Actions() {
	}

	/////////////////////////////////////////

	/**
	 * 从默认客户端实例中，获取同步Action代理
	 *
	 * @param actionName
	 *            action名称
	 * @return 同步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getAction(
			String actionName) {
		return getAction(null, actionName);
	}

	/**
	 * 从默认客户端实例中，获取同步Action代理，并设置可选参数
	 *
	 * @param actionName action名称
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 同步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getAction(
			String actionName, boolean backable,
			boolean abortable) {
		return getAction(null, actionName, backable,
				abortable);
	}

	/**
	 * 从默认客户端实例中，获取异步Action代理
	 *
	 * @param actionName
	 *            action名称
	 * @param actionCallback
	 *            回调接口
	 * @return 异步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getAsyncAction(
			String actionName, ActionCallback<R> actionCallback) {
		return getAsyncAction(null, actionName, actionCallback);
	}

	/**
	 * 从默认客户端实例中，获取异步Action代理，并设置可选参数
	 *
	 * @param actionName
	 *            action名称
	 * @param actionCallback
	 *            回调接口
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 异步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getAsyncAction(
			String actionName, ActionCallback<R> actionCallback,
			boolean backable, boolean abortable) {
		return getAsyncAction(null, actionName, actionCallback,
				backable, abortable);
	}

	/**
	 * 从默认客户端实例中，获取后台同步Action代理
	 *
	 * @param actionName
	 *            action名称
	 * @return 后台同步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getBackAction(String actionName) {
		return getBackAction(null, actionName);
	}

	/**
	 * 从默认客户端实例中，获取后台同步Action代理，并设置可选参数
	 *
	 * @param actionName
	 *            action名称
	 * @param abortable 是否允许中止
	 * @return 后台同步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getBackAction(String actionName, boolean abortable) {
		return getBackAction(null, actionName, abortable);
	}

	/**
	 * 从默认客户端实例中，获取后台异步Action代理
	 *
	 * @param actionName
	 *            action名称
	 * @param actionCallback
	 *            回调接口
	 * @return 后台异步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getBackAsyncAction(String actionName,
			ActionCallback<R> actionCallback) {
		return getBackAsyncAction(null, actionName, actionCallback);
	}

	/**
	 * 从默认客户端实例中，获取后台异步Action代理
	 *
	 * @param actionName
	 *            action名称
	 * @param actionCallback
	 *            回调接口
	 * @param abortable 是否允许中止
	 * @return 后台异步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getBackAsyncAction(String actionName,
			ActionCallback<R> actionCallback, boolean abortable) {
		return getBackAsyncAction(null, actionName, actionCallback, abortable);
	}

	/////////////////////////////////////////

	/**
	 * 从指定客户端实例中，获取同步Action代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param actionName
	 *            action名称
	 * @return 同步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getAction(
			String clientName, String actionName) {
		return getAction(clientName, actionName);
	}

	/**
	 * 从指定客户端实例中，获取同步Action代理，并设置可选参数
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param actionName action名称
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 同步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getAction(
			String clientName, String actionName,
			boolean backable, boolean abortable) {
		return Client.getClient(clientName).getActionFactory().getAction(actionName,
				backable, abortable);
	}

	/**
	 * 从指定客户端实例中，获取异步Action代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param actionName
	 *            action名称
	 * @param actionCallback
	 *            回调接口
	 * @return 异步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getAsyncAction(
			String clientName, String actionName,
			ActionCallback<R> actionCallback) {
		return getAsyncAction(clientName, actionName, actionCallback);
	}

	/**
	 * 从指定客户端实例中，获取异步Action代理，并设置可选参数
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param actionName
	 *            action名称
	 * @param actionCallback
	 *            回调接口
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 异步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getAsyncAction(
			String clientName, String actionName,
			ActionCallback<R> actionCallback,
			boolean backable, boolean abortable) {
		return Client.getClient(clientName).getActionFactory().getAsyncAction(actionName,
				actionCallback, backable, abortable);
	}

	/**
	 * 从指定客户端实例中，获取后台同步Action代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param actionName
	 *            action名称
	 * @return 后台同步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getBackAction(
			String clientName, String actionName) {
		return getBackAction(clientName, actionName);
	}

	/**
	 * 从指定客户端实例中，获取后台同步Action代理，并设置可选参数
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param actionName
	 *            action名称
	 * @param abortable 是否允许中止
	 * @return 后台同步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getBackAction(
			String clientName, String actionName, boolean abortable) {
		return Client.getClient(clientName).getActionFactory().getBackAction(actionName, abortable);
	}

	/**
	 * 从指定客户端实例中，获取后台异步Action代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param actionName
	 *            action名称
	 * @param actionCallback
	 *            回调接口
	 * @return 后台异步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getBackAsyncAction(
			String clientName, String actionName,
			ActionCallback<R> actionCallback) {
		return getBackAsyncAction(clientName, actionName, actionCallback);
	}

	/**
	 * 从指定客户端实例中，获取后台异步Action代理
	 *
	 * @param clientName
	 *            客户端实例名称
	 * @param actionName
	 *            action名称
	 * @param actionCallback
	 *            回调接口
	 * @param abortable 是否允许中止
	 * @return 后台异步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getBackAsyncAction(
			String clientName, String actionName,
			ActionCallback<R> actionCallback, boolean abortable) {
		return Client.getClient(clientName).getActionFactory().getBackAsyncAction(actionName,
				actionCallback, abortable);
	}

}
