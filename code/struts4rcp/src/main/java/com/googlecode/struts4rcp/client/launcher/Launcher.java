package com.googlecode.struts4rcp.client.launcher;

/**
 * 启动器接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Launcher {

	/**
	 * 启动
	 * @param hostAddress 主机
	 * @param hostPort 端口
	 * @param contextPath
	 */
	void launch(String hostAddress, int hostPort, String contextPath);

}