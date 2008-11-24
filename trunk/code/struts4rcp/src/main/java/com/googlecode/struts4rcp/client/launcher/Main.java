package com.googlecode.struts4rcp.client.launcher;

import com.googlecode.struts4rcp.util.ClassUtils;

public class Main {

	public static void main(String[] args) {
		if (args.length < 4)
			throw new IllegalArgumentException("Web Start 应提供 launcher, server.host, server.port, context.path 四个参数.");
		try {
			Class<?> launcherClass = ClassUtils.forName(args[0]);
			Launcher launcher = (Launcher)launcherClass.newInstance();
			launcher.start(args[1], Integer.parseInt(args[2]), args[3]);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
