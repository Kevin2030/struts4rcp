package com.googlecode.struts4rcp.client.event;

import com.googlecode.struts4rcp.client.Execution;

public class ExecutionEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final Execution execution;

	public ExecutionEvent(Object source, Execution execution) {
		super(source);
		this.execution = execution;
	}

	public Execution getExecution() {
		return execution;
	}

}
