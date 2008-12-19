package com.googlecode.struts4rcp.client;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.client.event.ConfigurationListener;
import com.googlecode.struts4rcp.client.event.NetworkListener;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.client.event.Listenable;
import com.googlecode.struts4rcp.client.event.Listener;
import com.googlecode.struts4rcp.client.event.TransferListener;
import com.googlecode.struts4rcp.client.event.WorkListener;
import com.googlecode.struts4rcp.client.transferrer.HttpURLConnectionTransferrer;
import com.googlecode.struts4rcp.util.KeyValue;
import com.googlecode.struts4rcp.util.PropertiesUtils;
import com.googlecode.struts4rcp.util.ServiceUtils;
import com.googlecode.struts4rcp.util.ThreadUtils;
import com.googlecode.struts4rcp.util.UnmodifiableProperties;

public class Client implements Listenable {

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

	public static void init(Properties config, Transferrer transferrer) {
		ThreadUtils.init();
		addClient(null, config, transferrer);
	}

	// 客户端
	private static final Map<String, Client> clients = new HashMap<String, Client>();

	/**
	 * 添加客户端实例
	 *
	 * @param clientName
	 *            客户端实例名
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
	 * @param clientName
	 *            客户端实例名
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
	 * @see java.util.Properties#load(java.io.InputStream)
	 * @see com.googlecode.struts4rcp.util.PropertiesUtils#loadFromClassPath(String)
	 * @see com.googlecode.struts4rcp.util.PropertiesUtils#loadFromFileSystem(String)
	 * @param clientName
	 *            客户端实例名
	 * @param config
	 *            配置
	 */
	public static void addClient(String clientName, Properties config,
			Transferrer transferrer) {
		if (config == null)
			throw new NullPointerException("properties == null!");
		addClient(clientName, new Client(config, transferrer));
	}

	/**
	 * 添加客户端实例
	 *
	 * @param clientName
	 *            客户端实例名
	 * @param client
	 *            客户端实例
	 */
	private static void addClient(String clientName, Client client) {
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
	 * @param clientName
	 *            客户端实例名
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
	 * @param clientName
	 *            客户端实例名
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

	/**
	 * 传输器配置参数名
	 */
	public static final String TRANSFERRER_KEY = "transferrer";

	/**
	 * 事件监听器配置参数名
	 */
	public static final String LISTENERS_KEY = "listeners";

	private final ConfigurationManager configurationManager;

	private final Transferrer transferrer;

	private Client(Properties config) {
		this(config, PropertiesUtils.getInstanceProperty(config,
				TRANSFERRER_KEY, Transferrer.class,
				HttpURLConnectionTransferrer.class));
	}

	private Client(Properties config, Transferrer transferrer) {
		if (config == null)
			throw new NullPointerException("properties == null!");
		if (transferrer == null)
			throw new NullPointerException("transferrer == null!");
		config = new UnmodifiableProperties(config);
		this.transferrer = transferrer;
		this.configurationManager = new ConfigurationManager(this, config);
		transferrer.init(this, config);
		configurationManager.register(TRANSFERRER_KEY, "传输策略",
				"暂未实现传输策略动态切换，修改后不会生效!", HttpURLConnectionTransferrer.class
						.getName(), ServiceUtils.getServiceClassNames(
						Transferrer.class.getName()).toArray(new String[0]));
		configurationManager.register(LISTENERS_KEY, "事件监听器",
				"暂未实现动态注册事件监听器，修改后不会生效!", "");
		// 读取监听器
		List<Listener> listeners = PropertiesUtils.getInstancesProperty(config,
				LISTENERS_KEY, Listener.class);
		for (Listener listener : listeners) {
			addListener(listener);
		}
	}

	public ConfigurationManager getConfigurationManager() {
		return configurationManager;
	}

	public Transferrer getTransferrer() {
		return transferrer;
	}

	private void shutdown() {
		try {
			transferrer.shutdown();
		} finally {
			configurationManager.shutdown();
		}
	}

	/**
	 * 向默认客户端实例中，注册事件监听器
	 *
	 * @param listener
	 *            事件监听器
	 */
	public void addListener(Listener listener) {
		if (listener instanceof NetworkListener)
			this.getTransferrer().addNetworkListener(
					(NetworkListener) listener);
		if (listener instanceof TransferListener)
			this.getTransferrer().addTransferListener(
					(TransferListener) listener);
		if (listener instanceof ConfigurationListener)
			this.getConfigurationManager().addConfigurationListener(
					(ConfigurationListener) listener);
		if (listener instanceof WorkListener)
			Worker.getWorker().addWorkListener((WorkListener) listener);
		if (listener instanceof ExceptionListener)
			Worker.getWorker().addExceptionListener(
					(ExceptionListener) listener);
	}

	/**
	 * 从默认客户端实例中，移除事件监听器
	 *
	 * @param listener
	 *            事件监听器
	 */
	public void removeListener(Listener listener) {
		if (listener instanceof NetworkListener)
			this.getTransferrer().removeNetworkListener(
					(NetworkListener) listener);
		if (listener instanceof TransferListener)
			this.getTransferrer().removeTransferListener(
					(TransferListener) listener);
		if (listener instanceof ConfigurationListener)
			this.getConfigurationManager().removeConfigurationListener(
					(ConfigurationListener) listener);
		if (listener instanceof WorkListener)
			Worker.getWorker().removeWorkListener((WorkListener) listener);
		if (listener instanceof ExceptionListener)
			Worker.getWorker().removeExceptionListener(
					(ExceptionListener) listener);
	}

	public Properties getProperties() {
		return configurationManager.getProperties();
	}

	public String getProperty(String key) {
		return configurationManager.getProperty(key);
	}

	public void setProperty(String key, String value) {
		configurationManager.setProperty(key, value);
	}

	/**
	 * 获取同步Action代理
	 *
	 * @param actionName action名称
	 * @return 同步Action代理
	 */
	public <M extends Serializable, R extends Serializable> Action<M, R> getAction(String actionName) {
		return new ActionProxy<M, R>(actionName);
	}

	/**
	 * 获取资源代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @param args 资源位置占位参数
	 * @return 资源
	 */
	public <R extends Serializable> Resource<R> getResource(String uri, Object... args) {
		return new ResourceProxy<R>(format(uri, args));
	}

	/**
	 * 获取资源目录代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @param args 资源位置占位参数
	 * @return 资源集合
	 */
	public <R extends Serializable> Resources<R> getResources(String uri, Object... args) {
		return new ResourcesProxy<R>(format(uri, args), false);
	}

	/**
	 * 获取资源集合引用代理 (该代理list()只下载引用URI，在每个资源get()时去取具体资源)
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @param args 资源位置占位参数
	 * @return 资源集合
	 */
	public <R extends Serializable> Resources<R> getLazyResources(String uri, Object... args) {
		return new ResourcesProxy<R>(format(uri, args), true);
	}

	private String format(String uri, Object... args) {
		if (uri != null && uri.length() > 0
				&& args != null && args.length > 0) {
			return new MessageFormat(uri, Locale.getDefault()).format(args);
		}
		return uri;
	}

	/**
	 * Action代理
	 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
	 */
	private final class ActionProxy<M extends Serializable, R extends Serializable> implements Action<M, R> {

		private final String actionName;

		ActionProxy(String actionName) {
			if (actionName == null)
				throw new NullPointerException("actionName == null!");
			this.actionName = actionName;
		}

		@SuppressWarnings("unchecked")
		public R execute(final M model) throws Exception {
			return (R)getTransferrer().transfer(new Transfer(actionName, model));
		}
	}

	private class ResourcesProxy<R extends Serializable> implements Resources<R> {

		private static final long serialVersionUID = 1L;

		private final String uri;

		private final boolean lazy;

		ResourcesProxy(String uri, boolean lazy) {
			if (uri == null)
				throw new NullPointerException("uri == null!");
			this.uri = uri;
			this.lazy = lazy;
		}

		public String getURI() {
			return uri;
		}

		public long count() throws Exception {
			return count(null);
		}

		public long count(R resource) throws Exception {
			String count = (String)getTransferrer().transfer(new Transfer(uri, resource, Transfer.HEAD_METHOD));
			return Long.parseLong(count);
		}

		public Resource<R>[] list() throws Exception {
			return list(null, NOSKIP, LIMITLESS);
		}

		public Resource<R>[] list(long start, long limit) throws Exception {
			return list(null, start, limit);
		}

		public Resource<R>[] list(R resource) throws Exception {
			return list(resource, NOSKIP, LIMITLESS);
		}

		public Resource<R>[] list(R resource, long start, long limit) throws Exception {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("lazy", String.valueOf(lazy));
			headers.put("start", String.valueOf(start));
			headers.put("limit", String.valueOf(limit));
			KeyValue<String, R>[] responses = (KeyValue<String, R>[])getTransferrer().transfer(new Transfer(uri, resource, Transfer.GET_METHOD, headers));
			return convertResources(responses);
		}

		@SuppressWarnings("unchecked")
		public Resource<R> create(R resource) throws Exception {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("lazy", String.valueOf(lazy));
			KeyValue<String, R> response = (KeyValue<String, R>)getTransferrer().transfer(new Transfer(uri, resource, Transfer.POST_METHOD, headers));
			return convertResource(response);
		}

		@SuppressWarnings("unchecked")
		private Resource<R>[] convertResources(KeyValue<String, R>[] responses) {
			if (responses == null)
				return null;
			Resource<R>[] resources = new Resource[responses.length];
			for (int i = 0, n = responses.length; i < n; i ++) {
				resources[i] = convertResource(responses[i]);
			}
			return resources;
		}

		private Resource<R> convertResource(KeyValue<String, R> response) {
			if (lazy)
				return new ResourceProxy<R>(response.getKey());
			else
				return new ResourceProxy<R>(response.getKey(), response.getValue());
		}

		public void flush() {
			// TODO 未实现缓存
		}

	}

	private class ResourceProxy<R extends Serializable> implements Resource<R> {

		private static final long serialVersionUID = 6614859999242411781L;

		private final String uri;

		private R resource;

		ResourceProxy(String uri) {
			if (uri == null)
				throw new NullPointerException("uri == null!");
			this.uri = uri;
		}

		ResourceProxy(String uri, R resource) {
			this(uri);
			this.resource = resource;
		}

		public String getURI() {
			return uri;
		}

		public void flush() {
			resource = null;
		}

		public boolean exist() throws Exception {
			String exist = (String)getTransferrer().transfer(new Transfer(uri, null, Transfer.HEAD_METHOD));
			return Boolean.parseBoolean(exist);
		}

		@SuppressWarnings("unchecked")
		public R read() throws Exception {
			if (resource == null)
				resource = (R)getTransferrer().transfer(new Transfer(uri, null, Transfer.GET_METHOD));
			return resource;
		}

		public void update(R resource) throws Exception {
			getTransferrer().transfer(new Transfer(uri, resource, Transfer.PUT_METHOD));
		}

		public void delete() throws Exception {
			getTransferrer().transfer(new Transfer(uri, null, Transfer.DELETE_METHOD));
		}

	}

}
