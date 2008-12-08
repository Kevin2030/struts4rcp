package com.googlecode.struts4rcp.client.event;

/**
 * 传输事件监听器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface TransmissionListener extends Listener {

	/**
	 * 当传输开始时触发
	 * @param event 传输事件信息
	 */
	void onTransporting(TransmissionEvent event);

	/**
	 * 当处理传输完成时触发
	 * @param event 传输事件信息
	 */
	void onTransported(TransmissionEvent event);

}
