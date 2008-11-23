package com.googlecode.struts4rcp.client.event;

/**
 * 连接事件发布器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ConnectionPublisher extends Publisher<ConnectionListener, ConnectionEvent> {

	@Override
	protected void doEvent(ConnectionListener listener, ConnectionEvent event) {
		if (event.isConnected())
			listener.onConnected(event);
		else
			listener.onDisconnected(event);
	}

}
