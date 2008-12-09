package com.googlecode.struts4rcp.client.launcher;

/**
 * 启动器接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Launcher {

	/**
	 * 启动
	 * @param host
	 * @param port
	 * @param contextPath
	 */
	void launch(String host, int port, String contextPath);

}
