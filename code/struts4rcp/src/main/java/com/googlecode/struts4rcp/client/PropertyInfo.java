package com.googlecode.struts4rcp.client;

import java.util.Collection;

/**
 * 配置项
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class PropertyInfo {

	private final String name;

	private final String description;

	private final String defaultValue;

	private final Collection<String> optionValues;

	public PropertyInfo(String name, String description, String defaultValue, Collection<String> optionValues) {
		super();
		this.name = name;
		this.description = description;
		this.defaultValue = defaultValue;
		this.optionValues = optionValues;
	}

	/**
	 * 获取配置项名称
	 * @return 配置项名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取配置项描述
	 * @return 描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 获取默认值
	 * @return 默认值
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * 获取选项值
	 * @return 获取选项值
	 */
	public Collection<String> getOptionValues() {
		return optionValues;
	}

}
