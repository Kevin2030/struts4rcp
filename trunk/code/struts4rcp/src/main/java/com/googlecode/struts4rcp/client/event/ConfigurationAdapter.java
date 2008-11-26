package com.googlecode.struts4rcp.client.event;

/**
 * 配置改变事件监听器空方法适配
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ConfigurationAdapter extends Adapter implements ConfigurationListener {

	public void onConfigurationChanged(ConfigurationEvent event) {}

	public void onConfigurationAdded(ConfigurationEvent event) {}

	public void onConfigurationRemoved(ConfigurationEvent event) {}

}
