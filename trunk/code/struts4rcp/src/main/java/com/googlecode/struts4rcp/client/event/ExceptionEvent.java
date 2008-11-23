package com.googlecode.struts4rcp.client.event;

public class ExceptionEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final Throwable exception;

	private final boolean back;

	public ExceptionEvent(Object source, Throwable exception, boolean back) {
		super(source);
		this.exception = exception;
		this.back = back;
	}

	public Throwable getException() {
		return exception;
	}

	public boolean isBack() {
		return back;
	}

}
