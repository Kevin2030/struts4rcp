package com.googlecode.struts4rcp.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.googlecode.struts4rcp.client.event.ConfigurationListener;
import com.googlecode.struts4rcp.client.event.ConnectionListener;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.client.event.ExecutionListener;
import com.googlecode.struts4rcp.client.event.Listener;
import com.googlecode.struts4rcp.client.event.TransmissionListener;
import com.googlecode.struts4rcp.client.transmitter.HttpURLConnectionTransmitter;
import com.googlecode.struts4rcp.util.PropertiesUtils;
import com.googlecode.struts4rcp.util.ServiceUtils;
import com.googlecode.struts4rcp.util.ThreadUtils;
import com.googlecode.struts4rcp.util.UnmodifiableProperties;

public class Client {

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

	/**
	 * 传输器配置参数名
	 */
	public static final String TRANSPORTER_PARAM_NAME = "transporter";

	/**
	 * Action代理工厂配置参数名
	 */
	public static final String ACTION_FACTORY_PARAM_NAME = "actionFactory";

	/**
	 * 资源代理工厂配置参数名
	 */
	public static final String RESOURCE_FACTORY_PARAM_NAME = "resourceFactory";

	/**
	 * Action代理供给策略配置参数名
	 */
	public static final String CONFIGURATION_MANAGER_PARAM_NAME = "configurationManager";

	/**
	 * 事件监听器配置参数名
	 */
	public static final String LISTENERS_PARAM_NAME = "listeners";

	private final ConfigurationManager configurationManager;

	private final Transmitter transmitter;

	private final ActionFactory actionFactory;

	private final ResourceFactory resourceFactory;

	private static Transmitter loadTransporter(Properties config) {
		 return PropertiesUtils.getInstanceProperty(config,
					TRANSPORTER_PARAM_NAME, Transmitter.class,
					HttpURLConnectionTransmitter.class);
	}

	private static ActionFactory loadActionFactory(Properties config) {
		 return PropertiesUtils.getInstanceProperty(
					config, ACTION_FACTORY_PARAM_NAME, ActionFactory.class,
					ActionFactory.class);
	}

	private static ResourceFactory loadResourceFactory(Properties config) {
		 return PropertiesUtils.getInstanceProperty(
					config, RESOURCE_FACTORY_PARAM_NAME, ResourceFactory.class,
					ResourceFactory.class);
	}

	private static ConfigurationManager loadConfigurationManager(Properties config) {
		 return PropertiesUtils.getInstanceProperty(config,
					CONFIGURATION_MANAGER_PARAM_NAME, ConfigurationManager.class,
					ConfigurationManager.class);
	}

	public Client(Properties config) {
		this(config, loadTransporter(config), loadActionFactory(config), loadResourceFactory(config), loadConfigurationManager(config));
	}

	public Client(Properties config, Transmitter transporter) {
		this(config, transporter, loadActionFactory(config), loadResourceFactory(config), loadConfigurationManager(config));
	}

	public Client(Properties config, Transmitter transmitter,
			ActionFactory actionFactory, ResourceFactory resourceFactory,
			ConfigurationManager configurationManager) {
		if (config == null)
			throw new NullPointerException("properties == null!");
		if (configurationManager == null)
			throw new NullPointerException("configurationManager == null!");
		if (transmitter == null)
				throw new NullPointerException("transmitter == null!");
		if (actionFactory == null)
			throw new NullPointerException("actionFactory == null!");
		if (resourceFactory == null)
			throw new NullPointerException("resourceFactory == null!");

		this.configurationManager = configurationManager;
		this.transmitter = transmitter;
		this.actionFactory = actionFactory;
		this.resourceFactory = resourceFactory;
		config = new UnmodifiableProperties(config);
		configurationManager.init(this, config);
		transmitter.init(this, config);
		actionFactory.init(this, config);
		resourceFactory.init(this, config);
		configurationManager.register(TRANSPORTER_PARAM_NAME,
				"传输策略", "暂未实现传输策略动态切换，修改后不会生效!",
				HttpURLConnectionTransmitter.class.getName(), ServiceUtils.getServiceClassNames(Transmitter.class.getName()).toArray(new String[0]));
		configurationManager.register(ACTION_FACTORY_PARAM_NAME,
				"Action管理器", "暂未实现Action管理器动态切换，修改后不会生效!",
				ActionFactory.class.getName(), ServiceUtils.getServiceClassNames(ActionFactory.class.getName()).toArray(new String[0]));
		configurationManager.register(RESOURCE_FACTORY_PARAM_NAME,
				"资源管理器", "暂未实现资源管理器动态切换，修改后不会生效!",
				ResourceFactory.class.getName(), ServiceUtils.getServiceClassNames(ResourceFactory.class.getName()).toArray(new String[0]));
		configurationManager.register(CONFIGURATION_MANAGER_PARAM_NAME,
				"配置管理器", "暂未实现配置管理器动态切换，修改后不会生效!",
				ConfigurationManager.class.getName(), ServiceUtils.getServiceClassNames(ConfigurationManager.class.getName()).toArray(new String[0]));
		configurationManager.register(LISTENERS_PARAM_NAME,
				"事件监听器", "暂未实现动态注册事件监听器，修改后不会生效!", "");
		// 读取监听器
		List<Listener> listeners = PropertiesUtils.getInstancesProperty(
				config, LISTENERS_PARAM_NAME, Listener.class);
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

	public ActionFactory getActionFactory() {
		return actionFactory;
	}

	public ResourceFactory getResourceFactory() {
		return resourceFactory;
	}

	public void shutdown() {
		try {
			transmitter.shutdown();
		} finally {
			try {
				actionFactory.shutdown();
			} finally {
				try {
					resourceFactory.shutdown();
				} finally {
					configurationManager.shutdown();
				}
			}
		}
	}

	/**
	 * 向默认客户端实例中，注册事件监听器
	 *
	 * @param listener 事件监听器
	 */
	public void addListener(Listener listener) {
		if (listener instanceof ConnectionListener)
			this.getTransmitter().addConnectionListener(
					(ConnectionListener) listener);
		if (listener instanceof TransmissionListener)
			this.getTransmitter().addTransmissionListener(
					(TransmissionListener) listener);
		if (listener instanceof ExecutionListener)
			this.getActionFactory().addExecutionListener(
					(ExecutionListener) listener);
		if (listener instanceof ExceptionListener)
			this.getActionFactory().addExceptionListener(
					(ExceptionListener) listener);
		if (listener instanceof ConfigurationListener)
			this.getConfigurationManager()
					.addConfigurationListener((ConfigurationListener) listener);
	}

	/**
	 * 从默认客户端实例中，移除事件监听器
	 * @param listener 事件监听器
	 */
	public void removeListener(Listener listener) {
		if (listener instanceof ConnectionListener)
			this.getTransmitter().removeConnectionListener(
					(ConnectionListener) listener);
		if (listener instanceof TransmissionListener)
			this.getTransmitter()
					.removeTransmissionListener(
							(TransmissionListener) listener);
		if (listener instanceof ExecutionListener)
			this.getActionFactory().removeExecutionListener(
					(ExecutionListener) listener);
		if (listener instanceof ExceptionListener)
			this.getActionFactory().removeExceptionListener(
					(ExceptionListener) listener);
		if (listener instanceof ConfigurationListener)
			this.getConfigurationManager()
					.removeConfigurationListener(
							(ConfigurationListener) listener);
	}

}
