package com.googlecode.struts4rcp.client.event;

public abstract class Adapter implements Listener {

	public boolean isAsync() {
		return true;
	}

}
