package com.googlecode.struts4rcp.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import com.googlecode.struts4rcp.client.event.TransportationListener;

/**
 * 传输器策略接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Transporter extends ConnectionMonitor {

	/**
	 * 传输对象
	 * @param execution 执行项
	 * @return 传回对象
	 * @throws IOException 传输出错时抛出
	 */
	public abstract Serializable transport(Execution execution) throws IOException;

	/**
	 * 获取正在前台运行的传输项列表
	 * @return 正在运行的传输项列表
	 */
	public abstract Collection<Execution> getTransportingExecutions();

	/**
	 * 是否正在前台传输
	 * @return 是否正在前台传输
	 */
	public abstract boolean isTransporting();

	/**
	 * 添加传输事件监听器
	 * @param listener 传输事件监听器
	 */
	public abstract void addTransportationListener(TransportationListener listener);

	/**
	 * 移除传输事件监听器
	 * @param listener 传输事件监听器
	 */
	public abstract void removeTransportationListener(TransportationListener listener);

}
