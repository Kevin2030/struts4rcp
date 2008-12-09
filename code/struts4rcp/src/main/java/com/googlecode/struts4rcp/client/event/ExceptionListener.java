package com.googlecode.struts4rcp.client.event;

public interface ExceptionListener extends Listener {

	void onForeExceptionCatched(ExceptionEvent event);

	void onBackExceptionCatched(ExceptionEvent event);

}
