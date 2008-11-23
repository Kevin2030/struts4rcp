package com.googlecode.struts4rcp.client;

/**
 * 配置项
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Configuration {

	private final String key;

	private final String value;

	private final String name;

	private final String description;

	public Configuration(String key, String value, String name, String description) {
		super();
		this.key = key;
		this.value = value;
		this.name = name;
		this.description = description;
	}

	/**
	 * 获取配置项索引
	 * @return 配置项索引
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 获取配置项值
	 * @return 配置项值
	 */
	public String getValue() {
		return value;
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
	 * 获取配置项名称
	 * @return 配置项名称
	 */
	public String getNameOrKey() {
		if (name != null)
			return name;
		return key;
	}

	@Override
	public String toString() {
		return key + "=" + value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Configuration other = (Configuration) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

}
