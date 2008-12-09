package com.googlecode.struts4rcp.client.launcher;

import java.util.Properties;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.transmitter.AbstractHttpTransmitter;

public class AbstractLauncher implements Launcher {

	public void launch(String serverHost,
			int serverPort,
			String contextPath) {
		Properties properties = new Properties();
		properties.setProperty(AbstractHttpTransmitter.SERVER_HOST_KEY, serverHost);
		properties.setProperty(AbstractHttpTransmitter.SERVER_PORT_KEY, String.valueOf(serverPort));
		properties.setProperty(AbstractHttpTransmitter.CONTEXT_PATH_KEY, contextPath);
		launch(properties);
	}

	protected void launch(Properties properties) {
		Client.init(properties);
	}

}
