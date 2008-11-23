package com.googlecode.struts4rcp.client.event;

import java.util.Date;
import java.util.EventObject;

/**
 * 事件信息基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class Event extends EventObject {

	private final Date timestamp;

	private final Thread thread;

	/**
	 * 构造事件信息
	 * @param source 事件发起者
	 */
	public Event(Object source) {
		super(source);
		this.timestamp = new Date();
		this.thread = Thread.currentThread();
	}

	/**
	 * 获取传输起始时间
	 * @return 起始时间
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * 获取传输起始时间
	 * @return 起始时间
	 */
	public Thread getThread() {
		return thread;
	}

}
