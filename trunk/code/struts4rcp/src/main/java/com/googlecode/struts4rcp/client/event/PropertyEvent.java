package com.googlecode.struts4rcp.client.event;

import com.googlecode.struts4rcp.client.PropertyDescription;

/**
 * 配置改变事件信息
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class PropertyEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final PropertyDescription description;

	private final String oldValue;

	private final String newValue;

	public PropertyEvent(Object source, PropertyDescription description, String oldValue, String newValue) {
		super(source);
		this.description = description;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	/**
	 * 获取改变的配置项
	 * @return 改变的配置项
	 */
	public PropertyDescription getConfiguration() {
		return description;
	}

	/**
	 * 获取旧值
	 * @return 旧值
	 */
	public String getOldValue() {
		return oldValue;
	}

	/**
	 * 获取新值
	 * @return 新值
	 */
	public String getNewValue() {
		return newValue;
	}

}
