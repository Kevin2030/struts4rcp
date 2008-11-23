package com.googlecode.struts4rcp.client;

import java.util.List;
import java.util.Properties;

import com.googlecode.struts4rcp.client.config.DefaultConfigurationManager;
import com.googlecode.struts4rcp.client.event.ConfigurationListener;
import com.googlecode.struts4rcp.client.event.ConnectionListener;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.client.event.ExecutionListener;
import com.googlecode.struts4rcp.client.event.Listener;
import com.googlecode.struts4rcp.client.event.TransportationListener;
import com.googlecode.struts4rcp.client.provider.DefaultActionProvider;
import com.googlecode.struts4rcp.client.transporter.HttpURLConnectionTransporter;
import com.googlecode.struts4rcp.util.PropertiesUtils;
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
	public static final String ACTION_PROVIDER_PARAM_NAME = "actionProvider";

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

	private final ActionProvider actionProvider;

	private static Transporter loadTransporter(Properties config) {
		 return PropertiesUtils.getInstanceProperty(config,
					TRANSPORTER_PARAM_NAME, Transporter.class,
					HttpURLConnectionTransporter.class);
	}

	private static ActionProvider loadActionProvider(Properties config) {
		 return PropertiesUtils.getInstanceProperty(
					config, ACTION_PROVIDER_PARAM_NAME, ActionProvider.class,
					DefaultActionProvider.class);
	}

	private static ConfigurationManager loadConfigurationManager(Properties config) {
		 return PropertiesUtils.getInstanceProperty(config,
					CONFIGURATION_MANAGER_PARAM_NAME, ConfigurationManager.class,
					DefaultConfigurationManager.class);
	}

	public Client(Properties config) {
		this(config, loadTransporter(config), loadActionProvider(config), loadConfigurationManager(config));
	}

	public Client(Properties config, Transporter transporter) {
		this(config, transporter, loadActionProvider(config), loadConfigurationManager(config));
	}

	public Client(Properties config, Transporter transporter,
			ActionProvider actionProvider) {
		this(config, transporter, actionProvider, loadConfigurationManager(config));
	}

	public Client(Properties config, Transporter transporter,
			ActionProvider actionProvider,
			ConfigurationManager configurationManager) {
		if (config == null)
			throw new NullPointerException("properties == null!");
		if (configurationManager == null)
			throw new NullPointerException("configurationManager == null!");
		if (transporter == null)
				throw new NullPointerException("transporter == null!");
		if (actionProvider == null)
			throw new NullPointerException("actionProvider == null!");

		this.configurationManager = configurationManager;
		this.transporter = transporter;
		this.actionProvider = actionProvider;
		config = new UnmodifiableProperties(config);
		configurationManager.init(this, config);
		transporter.init(this, config);
		actionProvider.init(this, config);
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

	public ActionProvider getActionProvider() {
		return actionProvider;
	}

	public void shutdown() {
		try {
			transporter.shutdown();
		} finally {
			try {
				actionProvider.shutdown();
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
			this.getActionProvider().addExecutionListener(
					(ExecutionListener) listener);
		if (listener instanceof ExceptionListener)
			this.getActionProvider().addExceptionListener(
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
			this.getActionProvider().removeExecutionListener(
					(ExecutionListener) listener);
		if (listener instanceof ExceptionListener)
			this.getActionProvider().removeExceptionListener(
					(ExceptionListener) listener);
		if (listener instanceof ConfigurationListener)
			this.getConfigurationManager()
					.removeConfigurationListener(
							(ConfigurationListener) listener);
	}

}
