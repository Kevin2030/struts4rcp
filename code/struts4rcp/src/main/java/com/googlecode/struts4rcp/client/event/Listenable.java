package com.googlecode.struts4rcp.client.event;

public interface Listenable {

	void addListener(Listener listener);

	void removeListener(Listener listener);

}
