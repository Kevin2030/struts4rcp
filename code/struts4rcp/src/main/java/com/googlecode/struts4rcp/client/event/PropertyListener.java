package com.googlecode.struts4rcp.client.event;

/**
 * 配置改变事件监听器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface PropertyListener extends Listener {

	/**
	 * 当配置改变时触发
	 * @param event 配置改变事件信息
	 */
	void onPropertyChanged(PropertyEvent event);

}
