package com.googlecode.struts4rcp.client.event;

import com.googlecode.struts4rcp.client.Transmission;

public class ExecutionEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final Transmission execution;

	public ExecutionEvent(Object source, Transmission execution) {
		super(source);
		this.execution = execution;
	}

	public Transmission getExecution() {
		return execution;
	}

}
