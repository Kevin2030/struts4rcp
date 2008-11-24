package com.googlecode.struts4rcp.client.launcher;

public interface Launcher {

	void start(String serverHost, int serverPort, String contextPath, String actionSuffix);

}
