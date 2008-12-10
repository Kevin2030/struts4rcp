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
import com.googlecode.struts4rcp.client.event.ConnectionListener;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.client.event.Listenable;
import com.googlecode.struts4rcp.client.event.Listener;
import com.googlecode.struts4rcp.client.event.TransmissionListener;
import com.googlecode.struts4rcp.client.event.WorkListener;
import com.googlecode.struts4rcp.client.transmitter.HttpURLConnectionTransmitter;
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

	public static void init(Properties config, Transmitter transmitter) {
		ThreadUtils.init();
		addClient(null, config, transmitter);
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
			Transmitter transmitter) {
		if (config == null)
			throw new NullPointerException("properties == null!");
		addClient(clientName, new Client(config, transmitter));
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
	public static final String TRANSMITTER_KEY = "transmitter";

	/**
	 * 事件监听器配置参数名
	 */
	public static final String LISTENERS_KEY = "listeners";

	private final ConfigurationManager configurationManager;

	private final Transmitter transmitter;

	private Client(Properties config) {
		this(config, PropertiesUtils.getInstanceProperty(config,
				TRANSMITTER_KEY, Transmitter.class,
				HttpURLConnectionTransmitter.class));
	}

	private Client(Properties config, Transmitter transmitter) {
		if (config == null)
			throw new NullPointerException("properties == null!");
		if (transmitter == null)
			throw new NullPointerException("transmitter == null!");
		config = new UnmodifiableProperties(config);
		this.transmitter = transmitter;
		this.configurationManager = new ConfigurationManager(this, config);
		transmitter.init(this, config);
		configurationManager.register(TRANSMITTER_KEY, "传输策略",
				"暂未实现传输策略动态切换，修改后不会生效!", HttpURLConnectionTransmitter.class
						.getName(), ServiceUtils.getServiceClassNames(
						Transmitter.class.getName()).toArray(new String[0]));
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

	public Transmitter getTransmitter() {
		return transmitter;
	}

	private void shutdown() {
		try {
			transmitter.shutdown();
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
		if (listener instanceof ConnectionListener)
			this.getTransmitter().addConnectionListener(
					(ConnectionListener) listener);
		if (listener instanceof TransmissionListener)
			this.getTransmitter().addTransmissionListener(
					(TransmissionListener) listener);
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
		if (listener instanceof ConnectionListener)
			this.getTransmitter().removeConnectionListener(
					(ConnectionListener) listener);
		if (listener instanceof TransmissionListener)
			this.getTransmitter().removeTransmissionListener(
					(TransmissionListener) listener);
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
	 * @return 资源
	 */
	public <R extends Serializable> Directory<R> getDirectory(String uri, Object... args) {
		return new DirectoryProxy<R>(format(uri, args));
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
			return (R)getTransmitter().transmit(actionName, model);
		}
	}

	private class ResourceProxy<R extends Serializable> implements Resource<R> {

		private final String uri;

		ResourceProxy(String uri) {
			if (uri == null)
				throw new NullPointerException("uri == null!");
			this.uri = uri;
		}

		public String getURI() {
			return uri;
		}

		@SuppressWarnings("unchecked")
		public R read() throws Exception {
			return (R)getTransmitter().transmit(Transmitter.GET_METHOD, uri, null);
		}

		public void update(R resource) throws Exception {
			getTransmitter().transmit(Transmitter.PUT_METHOD, uri, resource);
		}

		public void delete() throws Exception {
			getTransmitter().transmit(Transmitter.DELETE_METHOD, uri, null);
		}

		public Directory<R> getDirectory() throws Exception {
			String dir = (String)getTransmitter().transmit(Transmitter.HEAD_METHOD, uri, null);
			if (dir == null)
				return null;
			return new DirectoryProxy<R>(dir);
		}

	}

	private class DirectoryProxy<R extends Serializable> implements Directory<R> {

		private final String uri;

		DirectoryProxy(String uri) {
			if (uri == null)
				throw new NullPointerException("uri == null!");
			this.uri = uri;
		}

		public String getURI() {
			return uri;
		}

		public R[] list() throws Exception {
			return list(null);
		}

		public R[] list(R resource) throws Exception {
			return (R[])getTransmitter().transmit(Transmitter.GET_METHOD, uri, resource);
		}

		public Resource<R> create(R resource) throws Exception {
			String res = (String)getTransmitter().transmit(Transmitter.POST_METHOD, uri, resource);
			return new ResourceProxy<R>(res);
		}

	}

}
