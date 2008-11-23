package com.googlecode.struts4rcp.client.event;

/**
 * 连接事件监听器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ConnectionListener extends Listener {

	/**
	 * 当连接时触发
	 * @param event 事件信息
	 */
	void onConnected(ConnectionEvent event);

	/**
	 * 当连接断开时触发
	 * @param event 事件信息
	 */
	void onDisconnected(ConnectionEvent event);

}