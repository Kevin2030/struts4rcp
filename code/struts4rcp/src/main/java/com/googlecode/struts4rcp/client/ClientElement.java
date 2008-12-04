package com.googlecode.struts4rcp.client;

import java.util.Properties;

/**
 * 客户端元素
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ClientElement {

	/**
	 * 初始化配置，只能被框架本身调用
	 * @param client 客户端实例
	 * @param properties 配置
	 */
	void init(Client client, Properties properties);

	/**
	 * 获取当前所属客户端实例
	 * @return 客户端实例
	 */
	Client getClient();

	/**
	 * 停止
	 */
	void shutdown();

}
