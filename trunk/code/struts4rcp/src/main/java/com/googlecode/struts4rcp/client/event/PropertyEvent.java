package com.googlecode.struts4rcp.client.event;

import com.googlecode.struts4rcp.client.PropertyInfo;

/**
 * 配置改变事件信息
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class PropertyEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final String key;

	private final String oldValue;

	private final String newValue;

	private final PropertyInfo info;

	public PropertyEvent(Object source, String key, String oldValue, String newValue, PropertyInfo info) {
		super(source);
		this.key = oldValue;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.info = info;
	}

	/**
	 * 获取属性索引键
	 * @return 属性索引键
	 */
	public String getKey() {
		return key;
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

	/**
	 * 获取改变的配置项
	 * @return 改变的配置项
	 */
	public PropertyInfo getPropertyInfo() {
		return info;
	}

}
