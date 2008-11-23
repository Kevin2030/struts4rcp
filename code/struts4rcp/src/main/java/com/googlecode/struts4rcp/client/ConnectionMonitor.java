package com.googlecode.struts4rcp.client;

import com.googlecode.struts4rcp.client.event.ConnectionListener;

public interface ConnectionMonitor extends ClientElement {

	/**
	 * 检查服务器是否连通
	 * @return 服务器是否连通
	 */
	public abstract boolean ping();

	/**
	 * 刷新连接状态
	 * @return 状态是否变化
	 */
	public abstract boolean refresh();

	/**
	 * 是否已连接
	 * @return 是否已连接
	 */
	public abstract boolean isConnected();

	/**
	 * 添加连接事件监听器
	 * @param listener 连接事件监听器
	 */
	public abstract void addConnectionListener(ConnectionListener listener);

	/**
	 * 移除连接事件监听器
	 * @param listener 连接事件监听器
	 */
	public abstract void removeConnectionListener(ConnectionListener listener);

}
