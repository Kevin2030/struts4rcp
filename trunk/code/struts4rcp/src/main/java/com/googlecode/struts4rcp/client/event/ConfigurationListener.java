package com.googlecode.struts4rcp.client.event;

/**
 * 配置改变事件监听器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ConfigurationListener extends Listener {

	/**
	 * 当配置改变时触发
	 * @param event 配置改变事件信息
	 */
	void onConfigurationChanged(ConfigurationEvent event);

	/**
	 * 当配置改变时触发
	 * @param event 配置改变事件信息
	 */
	void onConfigurationAdded(ConfigurationEvent event);

	/**
	 * 当配置改变时触发
	 * @param event 配置改变事件信息
	 */
	void onConfigurationRemoved(ConfigurationEvent event);

}
