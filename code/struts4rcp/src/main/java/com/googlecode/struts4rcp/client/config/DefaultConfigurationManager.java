package com.googlecode.struts4rcp.client.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.Configuration;
import com.googlecode.struts4rcp.client.ConfigurationManager;
import com.googlecode.struts4rcp.client.event.ConfigurationEvent;
import com.googlecode.struts4rcp.client.event.ConfigurationListener;
import com.googlecode.struts4rcp.client.event.ConfigurationPublisher;

public class DefaultConfigurationManager implements ConfigurationManager {

	private Client client;

	private final Map<String, String> values = Collections.synchronizedMap(new HashMap<String, String>());

	private final Map<String, String> names = Collections.synchronizedMap(new HashMap<String, String>());

	private final Map<String, String> descriptions = Collections.synchronizedMap(new HashMap<String, String>());

	public Configuration getConfiguration(String key) {
		return new Configuration(key, values.get(key), names.get(key), descriptions.get(key));
	}

	public Collection<Configuration> getConfigurations() {
		Collection<Configuration> configurations = new ArrayList<Configuration>();
		synchronized (values) {
			for (Map.Entry<String, String> entry : values.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				configurations.add(new Configuration(key, value, names.get(key), descriptions.get(key)));
			}
		}
		return configurations;
	}

	@SuppressWarnings("unchecked")
	public void init(Client client, Properties config) {
		if (client == null)
			throw new NullPointerException("Client == null!");
		if (config == null)
			throw new NullPointerException("Configuration == null!");
		if (this.client != null)
			throw new IllegalStateException("Configuration already initialized!");
		this.client = client;
		this.values.putAll((Map)config);
	}

	public Map<String, String> getValues() {
		return Collections.unmodifiableMap(values);
	}

	public String getValue(String key) {
		if (key == null)
			throw new NullPointerException("key == null!");
		return values.get(key);
	}

	public void setValue(String key, String value) {
		if (key == null)
			throw new NullPointerException("key == null!");
		if (value == null)
			value = "";
		String old = values.get(key);
		if (isChanged(old, value)) {
			values.put(key, value);
			configurationPublisher.publishEvent(new ConfigurationEvent(this, new Configuration(key, value, names.get(key), descriptions.get(key))));
		}
	}

	private boolean isChanged(String s1, String s2) {
		if (s1 == null && s2 == null)
			return false;
		if (s1 == null || s2 == null)
			return true;
		return ! s1.equals(s2);
	}

	public void setName(String key, String name) {
		setName(key, name, null);
	}

	public void setName(String key, String name, String desc) {
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
		if (desc != null)
			descriptions.put(key, desc);
	}

	public void removeName(String key) {
		if (key == null)
			throw new NullPointerException("key == null!");
		names.remove(key);
		descriptions.remove(key);
	}

	private final ConfigurationPublisher configurationPublisher = new ConfigurationPublisher();

	public void addConfigurationListener(ConfigurationListener listener) {
		configurationPublisher.addListener(listener);
	}

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
