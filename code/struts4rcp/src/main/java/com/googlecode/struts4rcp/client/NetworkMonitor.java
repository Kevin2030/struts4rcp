package com.googlecode.struts4rcp.client;

import java.io.Closeable;
import java.util.Properties;

import com.googlecode.struts4rcp.client.event.NetworkListener;

/**
 * 网络监控器接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface NetworkMonitor extends Closeable {

	/**
	 * 初始化配置，只能被框架本身调用
	 * @param client 客户端实例
	 * @param properties 配置
	 */
	void init(Client client, Properties properties);

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
	public abstract void addNetworkListener(NetworkListener listener);

	/**
	 * 移除连接事件监听器
	 * @param listener 连接事件监听器
	 */
	public abstract void removeNetworkListener(NetworkListener listener);

}
