package com.googlecode.struts4rcp.client.event;

/**
 * 连接事件监听器空方法适配
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ConnectionAdapter extends Adapter implements ConnectionListener {

	public void onConnected(ConnectionEvent event) {}

	public void onDisconnected(ConnectionEvent event) {}

}
