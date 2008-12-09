package com.googlecode.struts4rcp.client.event;

import com.googlecode.struts4rcp.client.WorkContext;

public class WorkEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final WorkContext workContext;

	public WorkEvent(Object source, WorkContext workContext) {
		super(source);
		this.workContext = workContext;
	}

	public WorkContext getWorkContext() {
		return workContext;
	}

}
