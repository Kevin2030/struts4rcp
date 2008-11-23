package com.googlecode.struts4rcp.client;

import java.util.Collection;
import java.util.Map;

import com.googlecode.struts4rcp.client.event.ConfigurationListener;

/**
 * 配置管理器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ConfigurationManager extends ClientElement {

	/**
	 * 获取所有配置项
	 * @return 所有配置项
	 */
	public Collection<Configuration> getConfigurations();

	/**
	 * 获取配置项
	 * @param key 配置项索引
	 * @return 配置项
	 */
	public Configuration getConfiguration(String key);

	/**
	 * 获取所有配置项
	 * @return 不可变配置集合
	 */
	public Map<String, String> getValues();

	/**
	 * 获取配置项值
	 * @param key 配置项索引
	 * @return 配置项值
	 */
	public String getValue(String key);

	/**
	 * 设置配置项值
	 * @param key 配置项索引
	 * @param value 配置项值
	 */
	public void setValue(String key, String value);

	public void setName(String key, String name);

	/**
	 * 添加配置项描述，当用户修改配置时，将提示该描述信息
	 * @param key 配置项索引
	 * @param name 配置项名
	 * @param desc 配置项描述
	 */
	public void setName(String key, String name, String desc);

	/**
	 * 移除配置项描述
	 * @param key 配置项索引
	 */
	public void removeName(String key);

	/**
	 * 添加配置改变事件监听器
	 * @param listener 配置改变事件监听器
	 */
	public void addConfigurationListener(ConfigurationListener listener);

	/**
	 * 移除配置改变事件监听器
	 * @param listener 配置改变事件监听器
	 */
	public void removeConfigurationListener(ConfigurationListener listener);

}
