package com.googlecode.struts4rcp.client.event;

import com.googlecode.struts4rcp.client.Transmission;

/**
 * 处理工作事件信息
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransmissionEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final Transmission transmission;

	/**
	 * 构造处理工作事件信息
	 * @param source 事件源(事件发起者)
	 * @param transmission 传输项
	 */
	public TransmissionEvent(Object source, Transmission transmission) {
		super(source);
		this.transmission = transmission;
	}

	public Transmission getTransmission() {
		return transmission;
	}

}
