package com.googlecode.struts4rcp.client.event;

import com.googlecode.struts4rcp.client.work.Work;

public class WorkEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final Work work;

	public WorkEvent(Object source, Work work) {
		super(source);
		this.work = work;
	}

	public Work getWork() {
		return work;
	}

}
