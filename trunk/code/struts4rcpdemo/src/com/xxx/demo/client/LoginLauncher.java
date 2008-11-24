package com.xxx.demo.client;

import java.util.Properties;

import com.googlecode.struts4rcp.client.launcher.AbstractLauncher;

public class LoginLauncher extends AbstractLauncher {

	@Override
	protected void start(Properties properties) {
		super.start(properties);
		new LoginFrame();
	}

}
