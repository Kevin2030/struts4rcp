package com.googlecode.struts4rcp.client.event;

public class WorkPublisher extends Publisher<WorkListener, WorkEvent> {

	protected void doEvent(WorkListener listener, WorkEvent event) {
		if (event.getWorkContext().isWorked()) {
			listener.onWorked(event);
		} else {
			if (event.getWorkContext().isBack())
				listener.onBackWorking(event);
			else
				listener.onForeWorking(event);
		}
	}

}