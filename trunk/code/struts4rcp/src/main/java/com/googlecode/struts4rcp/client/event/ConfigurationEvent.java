package com.googlecode.struts4rcp.client.event;

import com.googlecode.struts4rcp.client.Configuration;

/**
 * 配置改变事件信息
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ConfigurationEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final Configuration configuration;

	private final String oldValue;

	private final boolean removed;

	public ConfigurationEvent(Object source, Configuration configuration, String oldValue, boolean removed) {
		super(source);
		this.configuration = configuration;
		this.oldValue = oldValue;
		this.removed = removed;
	}

	/**
	 * 获取改变的配置项
	 * @return 改变的配置项
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	public String getOldValue() {
		return oldValue;
	}

	public boolean isRemoved() {
		return removed;
	}

}
