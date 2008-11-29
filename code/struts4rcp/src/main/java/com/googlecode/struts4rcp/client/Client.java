package com.googlecode.struts4rcp.client;

import java.util.List;
import java.util.Properties;

import com.googlecode.struts4rcp.client.event.ConfigurationListener;
import com.googlecode.struts4rcp.client.event.ConnectionListener;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.client.event.ExecutionListener;
import com.googlecode.struts4rcp.client.event.Listener;
import com.googlecode.struts4rcp.client.event.TransportationListener;
import com.googlecode.struts4rcp.client.transporter.HttpURLConnectionTransporter;
import com.googlecode.struts4rcp.util.PropertiesUtils;
import com.googlecode.struts4rcp.util.ServiceUtils;
import com.googlecode.struts4rcp.util.Shutdownable;
import com.googlecode.struts4rcp.util.UnmodifiableProperties;

public class Client implements Shutdownable {

	/**
	 * 传输器配置参数名
	 */
	public static final String TRANSPORTER_PARAM_NAME = "transporter";

	/**
	 * Action代理供给策略配置参数名
	 */
	public static final String ACTION_MANAGER_PARAM_NAME = "actionFactory";

	/**
	 * Action代理供给策略配置参数名
	 */
	public static final String CONFIGURATION_MANAGER_PARAM_NAME = "configurationManager";

	/**
	 * 事件监听器配置参数名
	 */
	public static final String LISTENERS_PARAM_NAME = "listeners";

	private final ConfigurationManager configurationManager;

	private final Transporter transporter;

	private final ActionFactory actionFactory;

	private static Transporter loadTransporter(Properties config) {
		 return PropertiesUtils.getInstanceProperty(config,
					TRANSPORTER_PARAM_NAME, Transporter.class,
					HttpURLConnectionTransporter.class);
	}

	private static ActionFactory loadActionFactory(Properties config) {
		 return PropertiesUtils.getInstanceProperty(
					config, ACTION_MANAGER_PARAM_NAME, ActionFactory.class,
					ActionFactory.class);
	}

	private static ConfigurationManager loadConfigurationManager(Properties config) {
		 return PropertiesUtils.getInstanceProperty(config,
					CONFIGURATION_MANAGER_PARAM_NAME, ConfigurationManager.class,
					ConfigurationManager.class);
	}

	public Client(Properties config) {
		this(config, loadTransporter(config), loadActionFactory(config), loadConfigurationManager(config));
	}

	public Client(Properties config, Transporter transporter) {
		this(config, transporter, loadActionFactory(config), loadConfigurationManager(config));
	}

	public Client(Properties config, Transporter transporter,
			ActionFactory actionFactory,
			ConfigurationManager configurationManager) {
		if (config == null)
			throw new NullPointerException("properties == null!");
		if (configurationManager == null)
			throw new NullPointerException("configurationManager == null!");
		if (transporter == null)
				throw new NullPointerException("transporter == null!");
		if (actionFactory == null)
			throw new NullPointerException("actionFactory == null!");

		this.configurationManager = configurationManager;
		this.transporter = transporter;
		this.actionFactory = actionFactory;
		config = new UnmodifiableProperties(config);
		configurationManager.init(this, config);
		transporter.init(this, config);
		actionFactory.init(this, config);
		configurationManager.register(TRANSPORTER_PARAM_NAME,
				"传输策略", "暂未实现传输策略动态切换，修改后不会生效!",
				HttpURLConnectionTransporter.class.getName(), ServiceUtils.getServices(Transporter.class.getName()));
		configurationManager.register(ACTION_MANAGER_PARAM_NAME,
				"Action管理器", "暂未实现Action管理器动态切换，修改后不会生效!",
				ActionFactory.class.getName(), ServiceUtils.getServices(ActionFactory.class.getName()));
		configurationManager.register(CONFIGURATION_MANAGER_PARAM_NAME,
				"配置管理器", "暂未实现配置管理器动态切换，修改后不会生效!",
				ConfigurationManager.class.getName(), ServiceUtils.getServices(ConfigurationManager.class.getName()));
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

	public Transporter getTransporter() {
		return transporter;
	}

	public ActionFactory getActionFactory() {
		return actionFactory;
	}

	public void shutdown() {
		try {
			transporter.shutdown();
		} finally {
			try {
				actionFactory.shutdown();
			} finally {
				configurationManager.shutdown();
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
			this.getTransporter().addConnectionListener(
					(ConnectionListener) listener);
		if (listener instanceof TransportationListener)
			this.getTransporter().addTransportationListener(
					(TransportationListener) listener);
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
			this.getTransporter().removeConnectionListener(
					(ConnectionListener) listener);
		if (listener instanceof TransportationListener)
			this.getTransporter()
					.removeTransportationListener(
							(TransportationListener) listener);
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
