package com.googlecode.struts4rcp.client;

import java.io.Serializable;

/**
 * 资源静态门面
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public final class Resources {

	private Resources() {}

	/**
	 * 从默认客户端实例中，获取同步Resource代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @return 同步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getResource(String resourceName, Object... args) {
		return Client.getClient().getResource(resourceName, args);
	}

	/**
	 * 从默认客户端实例中，获取同步Resource代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @return 同步Resource代理
	 */
	public static <R extends Serializable> Directory<R> getDirectory(String resourceName, Object... args) {
		return Client.getClient().getDirectory(resourceName, args);
	}

	/**
	 * 从默认客户端实例中，获取同步Resource代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @return 同步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getResource(Client cleint, String resourceName, Object... args) {
		return cleint.getResource(resourceName, args);
	}

	/**
	 * 从默认客户端实例中，获取同步Resource代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @return 同步Resource代理
	 */
	public static <R extends Serializable> Directory<R> getDirectory(Client cleint, String resourceName, Object... args) {
		return cleint.getDirectory(resourceName, args);
	}

}
