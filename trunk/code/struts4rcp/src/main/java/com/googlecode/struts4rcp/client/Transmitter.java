package com.googlecode.struts4rcp.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import com.googlecode.struts4rcp.client.event.TransmissionListener;

/**
 * 传输器策略接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Transmitter extends ConnectionMonitor {

	/**
	 * 传输对象
	 * @param transmission 传输数据
	 * @return 传回对象
	 * @throws IOException 传输出错时抛出
	 */
	public abstract Serializable transmit(Transmission transmission) throws Exception;

	/**
	 * 获取正在传输的传输项列表
	 * @return 正在传输的传输项列表
	 */
	public abstract Collection<Transmission> getTransmissions();

	/**
	 * 是否正在传输
	 * @return 是否正在传输
	 */
	public abstract boolean isTransmiting();

	/**
	 * 添加传输事件监听器
	 * @param listener 传输事件监听器
	 */
	public abstract void addTransmissionListener(TransmissionListener listener);

	/**
	 * 移除传输事件监听器
	 * @param listener 传输事件监听器
	 */
	public abstract void removeTransmissionListener(TransmissionListener listener);

}
