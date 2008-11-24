package com.googlecode.struts4rcp.client.launcher;

import java.util.Properties;

import com.googlecode.struts4rcp.client.Actions;

public class AbstractLauncher implements Launcher {

	public void start(String serverHost,
			int serverPort,
			String contextPath,
			String actionSuffix) {
		Properties properties = new Properties();
		properties.setProperty("server.host", serverHost);
		properties.setProperty("server.port", String.valueOf(serverPort));
		properties.setProperty("context.path", contextPath);
		properties.setProperty("action.suffix", actionSuffix);
		start(properties);
	}

	protected void start(Properties properties) {
		Actions.init(properties);
	}

}
