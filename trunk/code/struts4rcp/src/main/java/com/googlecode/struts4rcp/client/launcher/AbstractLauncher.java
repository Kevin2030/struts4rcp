package com.googlecode.struts4rcp.client.launcher;

import java.util.Properties;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.transferrer.AbstractHttpTransferrer;

/**
 * 启动器基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class AbstractLauncher implements Launcher {

	public void launch(String serverHost,
			int serverPort,
			String contextPath) {
		Properties properties = new Properties();
		properties.setProperty(AbstractHttpTransferrer.HOST_ADDRESS_KEY, serverHost);
		properties.setProperty(AbstractHttpTransferrer.HOST_PORT_KEY, String.valueOf(serverPort));
		properties.setProperty(AbstractHttpTransferrer.CONTEXT_PATH_KEY, contextPath);
		launch(properties);
	}

	protected void launch(Properties properties) {
		Client.init(properties);
	}

}
