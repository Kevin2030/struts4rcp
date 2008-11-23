package com.googlecode.struts4rcp.client.event;

import com.googlecode.struts4rcp.client.Execution;

/**
 * 处理工作事件信息
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransportationEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final Execution execution;

	/**
	 * 构造处理工作事件信息
	 * @param source 事件源(事件发起者)
	 * @param execution 传输项
	 */
	public TransportationEvent(Object source, Execution execution) {
		super(source);
		this.execution = execution;
	}

	public Execution getExecution() {
		return execution;
	}

}
