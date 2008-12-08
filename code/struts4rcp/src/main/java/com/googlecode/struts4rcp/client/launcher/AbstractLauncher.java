package com.googlecode.struts4rcp.client.launcher;

import java.util.Properties;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.transmitter.AbstractHttpTransmitter;

public class AbstractLauncher implements Launcher {

	public void start(String serverHost,
			int serverPort,
			String contextPath) {
		Properties properties = new Properties();
		properties.setProperty(AbstractHttpTransmitter.SERVER_HOST_PARAM_NAME, serverHost);
		properties.setProperty(AbstractHttpTransmitter.SERVER_PORT_PARAM_NAME, String.valueOf(serverPort));
		properties.setProperty(AbstractHttpTransmitter.CONTEXT_PATH_PARAM_NAME, contextPath);
		start(properties);
	}

	protected void start(Properties properties) {
		Client.init(properties);
	}

}
