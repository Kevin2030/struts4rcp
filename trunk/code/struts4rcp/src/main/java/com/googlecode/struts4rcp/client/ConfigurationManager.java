package com.googlecode.struts4rcp.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.googlecode.struts4rcp.client.event.ConfigurationEvent;
import com.googlecode.struts4rcp.client.event.ConfigurationListener;
import com.googlecode.struts4rcp.client.event.ConfigurationPublisher;
import com.googlecode.struts4rcp.util.UnmodifiableProperties;

/**
 * 配置管理器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ConfigurationManager {

	private Client client;

	private final Properties values = new Properties();

	private final Map<String, String> names = Collections.synchronizedMap(new HashMap<String, String>());

	private final Map<String, String> descriptions = Collections.synchronizedMap(new HashMap<String, String>());

	private final Map<String, String> defaults = Collections.synchronizedMap(new HashMap<String, String>());

	private final Map<String, Collection<String>> options = Collections.synchronizedMap(new HashMap<String, Collection<String>>());

	/**
	 * 获取配置项
	 * @param key 配置项索引
	 * @return 配置项
	 */
	public Configuration getConfiguration(String key) {
		return new Configuration(key, values.getProperty(key), names.get(key), descriptions.get(key), defaults.get(key), options.get(key));
	}

	/**
	 * 获取所有配置项
	 * @return 所有配置项
	 */
	public Collection<Configuration> getConfigurations() {
		Collection<Configuration> configurations = new ArrayList<Configuration>();
		synchronized (values) {
			for (Map.Entry<Object, Object> entry : values.entrySet()) {
				String key = (String)entry.getKey();
				String value = (String)entry.getValue();
				configurations.add(new Configuration(key, value, names.get(key), descriptions.get(key), defaults.get(key), options.get(key)));
			}
		}
		return configurations;
	}

	/**
	 * 移除配置项
	 * @param key 配置项索引
	 */
	public void removeConfiguration(String key) {
		if (key == null)
			throw new NullPointerException("key == null!");
		Configuration configuration = new Configuration(key, values.getProperty(key), names.get(key), descriptions.get(key), defaults.get(key), options.get(key));
		values.remove(key);
		names.remove(key);
		descriptions.remove(key);
		configurationPublisher.publishEvent(new ConfigurationEvent(this, configuration, null, true));
	}

	@SuppressWarnings("unchecked")
	public ConfigurationManager(Client client, Properties config) {
		if (client == null)
			throw new NullPointerException("Client == null!");
		if (config == null)
			throw new NullPointerException("Configuration == null!");
		if (this.client != null)
			throw new IllegalStateException("Configuration already initialized!");
		this.client = client;
		this.values.putAll((Map)config);
	}

	/**
	 * 获取所有配置项
	 * @return 不可变配置集合
	 */
	public Properties getProperties() {
		return new UnmodifiableProperties(values);
	}

	/**
	 * 获取配置项值
	 * @param key 配置项索引
	 * @return 配置项值
	 */
	public String getProperty(String key) {
		if (key == null)
			throw new NullPointerException("key == null!");
		return values.getProperty(key);
	}

	/**
	 * 设置配置项值
	 * @param key 配置项索引
	 * @param value 配置项值
	 */
	public void setProperty(String key, String value) {
		if (key == null)
			throw new NullPointerException("key == null!");
		if (value == null)
			value = "";
		String old = values.getProperty(key);
		if (isChanged(old, value)) {
			values.put(key, value);
			configurationPublisher.publishEvent(new ConfigurationEvent(this, new Configuration(key, value, names.get(key), descriptions.get(key), defaults.get(key), options.get(key)), old, false));
		}
	}

	private boolean isChanged(String s1, String s2) {
		if (s1 == null && s2 == null)
			return false;
		if (s1 == null || s2 == null)
			return true;
		return ! s1.equals(s2);
	}

	/**
	 * 注册配置项名称和描述，当用户修改配置时，将提示该描述信息
	 * @param key 配置项索引
	 * @param name 配置项名
	 * @param desc 配置项描述
	 */
	public void register(String key, String name, String desc, String defaultValue, String... optionsValue) {
		if (key == null)
			throw new NullPointerException("key == null!");
		if (name == null)
			throw new NullPointerException("name == null!");
		synchronized (values) {
			if (! values.containsKey(key)) {
				values.put(key, "");
			}
		}
		names.put(key, name);
		if (desc != null && desc.length() > 0)
			descriptions.put(key, desc);
		if (defaultValue != null && defaultValue.length() > 0)
			defaults.put(key, defaultValue);
		if (optionsValue != null
				&& optionsValue.length > 0) {
			ArrayList<String> list = new ArrayList<String>();
			list.addAll(Arrays.asList(optionsValue));
			if (! list.contains(defaultValue))
				list.add(0, defaultValue);
			options.put(key, Collections.unmodifiableCollection(list));
		}
	}

	private final ConfigurationPublisher configurationPublisher = new ConfigurationPublisher();

	/**
	 * 添加配置改变事件监听器
	 * @param listener 配置改变事件监听器
	 */
	public void addConfigurationListener(ConfigurationListener listener) {
		configurationPublisher.addListener(listener);
	}

	/**
	 * 移除配置改变事件监听器
	 * @param listener 配置改变事件监听器
	 */
	public void removeConfigurationListener(ConfigurationListener listener) {
		configurationPublisher.removeListener(listener);
	}

	public void shutdown() {
		configurationPublisher.clearListeners();
	}

	public Client getClient() {
		return client;
	}

}
