package com.googlecode.struts4rcp.client.event;

/**
 * 连接事件信息
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class NetworkEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final boolean connected;

	/**
	 * 构造连接事件信息
	 * @param source 事件源(事件发起者)
	 * @param connected 连接状态
	 */
	public NetworkEvent(Object source, boolean connected) {
		super(source);
		this.connected = connected;
	}

	public boolean isConnected() {
		return connected;
	}

}
