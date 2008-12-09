package com.googlecode.struts4rcp.client;

import java.io.Serializable;

import com.googlecode.struts4rcp.Action;

/**
 * 客户端Action客户端静态门面
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public final class Actions {

	private Actions() {}

	/**
	 * 从默认客户端实例中，获取同步Action代理
	 *
	 * @param actionName
	 *            action名称
	 * @return 同步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getAction(String actionName) {
		return Client.getClient().getAction(actionName);
	}

	/**
	 * 从默认客户端实例中，获取同步Action代理
	 *
	 * @param actionName
	 *            action名称
	 * @return 同步Action代理
	 */
	public static <M extends Serializable, R extends Serializable> Action<M, R> getAction(Client client, String actionName) {
		return client.getActionFactory().getAction(actionName);
	}

}
