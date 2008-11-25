package com.googlecode.struts4rcp.client.launcher;

import java.util.Properties;

import com.googlecode.struts4rcp.client.Actions;
import com.googlecode.struts4rcp.client.transporter.AbstractHttpTransporter;

public class AbstractLauncher implements Launcher {

	public void start(String serverHost,
			int serverPort,
			String contextPath) {
		Properties properties = new Properties();
		properties.setProperty(AbstractHttpTransporter.SERVER_HOST_PARAM_NAME, serverHost);
		properties.setProperty(AbstractHttpTransporter.SERVER_PORT_PARAM_NAME, String.valueOf(serverPort));
		properties.setProperty(AbstractHttpTransporter.CONTEXT_PATH_PARAM_NAME, contextPath);
		start(properties);
	}

	protected void start(Properties properties) {
		Actions.init(properties);
	}

}
