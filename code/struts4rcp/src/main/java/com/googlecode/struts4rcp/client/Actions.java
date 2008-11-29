package com.googlecode.struts4rcp.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.util.PropertiesUtils;
import com.googlecode.struts4rcp.util.ThreadUtils;

/**
 * 客户端Action客户端静态门面
 *
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Actions {

	// 静态门面，私有化构造函数，禁止实例化
	private Actions() {
	}

	/**
	 * 初始化默认客户端实例
	 *
	 * @param configPath
	 *            配置文件路径
	 * @throws IOException
	 *             加载配置文件失败时抛出
	 */
	public static void init(String configPath) throws IOException {
		ThreadUtils.init();
		addClient(null, configPath);
	}

	/**
	 * 初始化默认客户端实例
	 *
	 * @see java.util.Properties#load(java.io.InputStream)
	 * @see com.googlecode.struts4rcp.util.PropertiesUtils#loadFromClassPath(String)
	 * @see com.googlecode.struts4rcp.util.PropertiesUtils#loadFromFileSystem(String)
	 * @param config
	 *            配置
	 */
	public static void init(Properties config) {
		ThreadUtils.init();
		addClient(null, config);
	}

	public static void init(Client client) {
		ThreadUtils.init();
		addClient(null, client);
	}

	// 客户端
	private static final Map<String, Client> clients = new HashMap<String, Client>();

	/**
	 * 添加客户端实例
	 *
	 * @param clientName 客户端实例名
	 * @param configPath
	 *            配置文件路径
	 * @throws IOException
	 *             加载配置文件失败时抛出
	 */
	public static void addClient(String clientName, String configPath)
			throws IOException {
		addClient(clientName, PropertiesUtils.load(configPath));
	}

	/**
	 * 添加客户端实例
	 *
	 * @see java.util.Properties#load(java.io.InputStream)
	 * @see com.googlecode.struts4rcp.util.PropertiesUtils#loadFromClassPath(String)
	 * @see com.googlecode.struts4rcp.util.PropertiesUtils#loadFromFileSystem(String)
	 * @param clientName 客户端实例名
	 * @param config
	 *            配置
	 */
	public static void addClient(String clientName, Properties config) {
		if (config == null)
			throw new NullPointerException("properties == null!");
		addClient(clientName, new Client(config));
	}

	/**
	 * 添加客户端实例
	 *
	 * @param clientName 客户端实例名
	 * @param client
	 *            客户端实例
	 */
	public static void addClient(String clientName, Client client) {
		synchronized (clients) {
			Client old = clients.get(clientName);
			if (old != null)
				old.shutdown();
			clients.put(clientName, client);
		}
	}

	/**
	 * 获取默认客户端实例
	 *
	 * @return 默认客户端实例
	 */
	public static Client getClient() {
		return getClient(null);
	}

	/**
	 * 获取指定客户端实例
	 *
	 * @param clientName 客户端实例名
	 */
	public static Client getClient(String clientName) {
		Client client;
		synchronized (clients) {
			client = clients.get(clientName);
		}
		if (client == null)
			throw new NullPointerException("Not found client: " + clientName);
		return client;
	}

	/**
	 * 移除指定客户端实例
	 *
	 * @param clientName 客户端实例名
	 */
	public static void removeClient(String clientName) {
		Client client;
		synchronized (clients) {
			client = clients.remove(clientName);
		}
		if (client != null)
			client.shutdown();
	}

	/**
	 * 锁毁所有客户端实例
	 */
	public static void destroy() {
		try {
			synchronized (clients) {
				for (Client client : clients.values()) {
					try {
						client.shutdown();
					} catch (Throwable t) {
						// ignore
					}
				}
				clients.clear();
			}
		} finally {
			ThreadUtils.destroy();
		}
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
		return getAction(clientName, actionName, true, true);
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
		return getClient(clientName).getActionFactory().getAction(
				getClient(clientName).getTransporter(), actionName,
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
		return getAsyncAction(clientName, actionName, actionCallback, true, true);
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
		return getClient(clientName).getActionFactory().getAsyncAction(
				getClient(clientName).getTransporter(), actionName,
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
		return getBackAction(clientName, actionName, true);
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
		return getClient(clientName).getActionFactory().getBackAction(
				getClient(clientName).getTransporter(), actionName, abortable);
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
		return getBackAsyncAction(clientName, actionName, actionCallback, true);
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
		return getClient(clientName).getActionFactory().getBackAsyncAction(
				getClient(clientName).getTransporter(), actionName,
				actionCallback, abortable);
	}

}
