package com.googlecode.struts4rcp.client.event;

public class ExceptionPublisher extends Publisher<ExceptionListener, ExceptionEvent> {

	@Override
	protected void doEvent(ExceptionListener listener, ExceptionEvent event) {
		if (event.isBack())
			listener.onBackCatched(event);
		else
			listener.onForeCatched(event);
	}

}
