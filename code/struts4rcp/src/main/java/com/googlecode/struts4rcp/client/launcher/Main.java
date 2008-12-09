package com.googlecode.struts4rcp.client.launcher;

import com.googlecode.struts4rcp.util.ClassUtils;

/**
 * 启动器入口类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Main {

	public static void main(String[] args) {
		if (args.length < 4)
			throw new IllegalArgumentException("应提供启动类，主机名，端口，上下文路径四个参数.");
		try {
			Class<?> launcherClass = ClassUtils.forName(args[0]);
			Launcher launcher = (Launcher)launcherClass.newInstance();
			launcher.launch(args[1], Integer.parseInt(args[2]), args[3]);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
