package com.googlecode.struts4rcp.client.event;

import com.googlecode.struts4rcp.client.Transfer;

/**
 * 处理工作事件信息
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransferEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final Transfer transfer;

	/**
	 * 构造处理工作事件信息
	 * @param source 事件源(事件发起者)
	 * @param transfer 传输项
	 */
	public TransferEvent(Object source, Transfer transfer) {
		super(source);
		this.transfer = transfer;
	}

	public Transfer getTransfer() {
		return transfer;
	}

}
