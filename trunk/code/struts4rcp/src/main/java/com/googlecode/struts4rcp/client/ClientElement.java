package com.googlecode.struts4rcp.client;

import java.util.Properties;

import com.googlecode.struts4rcp.util.Shutdownable;

/**
 * 客户端元素
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ClientElement extends Shutdownable {

	/**
	 * 初始化配置，只能被框架本身调用
	 * @param client 客户端实例
	 * @param properties 配置
	 */
	public void init(Client client, Properties properties);

	/**
	 * 获取当前所属客户端实例
	 * @return 客户端实例
	 */
	public abstract Client getClient();

}
