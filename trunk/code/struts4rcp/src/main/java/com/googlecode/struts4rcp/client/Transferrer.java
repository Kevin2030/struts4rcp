package com.googlecode.struts4rcp.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import com.googlecode.struts4rcp.client.event.TransferListener;

/**
 * 传输器策略接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Transferrer extends ConnectionMonitor {

	/**
	 * 传输对象
	 * @param transfer 传输数据
	 * @return 传回对象
	 * @throws IOException 传输出错时抛出
	 */
	public abstract Serializable transfer(Transfer transfer) throws Exception;

	/**
	 * 获取正在传输的传输项列表
	 * @return 正在传输的传输项列表
	 */
	public abstract Collection<Transfer> getTransfers();

	/**
	 * 是否正在传输
	 * @return 是否正在传输
	 */
	public abstract boolean isTransferring();

	/**
	 * 添加传输事件监听器
	 * @param listener 传输事件监听器
	 */
	public abstract void addTransferListener(TransferListener listener);

	/**
	 * 移除传输事件监听器
	 * @param listener 传输事件监听器
	 */
	public abstract void removeTransferListener(TransferListener listener);

}
