package com.googlecode.struts4rcp.client.event;

public class ExecutionPublisher extends Publisher<ExecutionListener, ExecutionEvent> {

	protected void doEvent(ExecutionListener listener, ExecutionEvent event) {
		if (event.getExecution().isExecuted()) {
			listener.onExecuted(event);
		} else {
			if (event.getExecution().isBack())
				listener.onBackExecuting(event);
			else
				listener.onExecuting(event);
		}
	}

}