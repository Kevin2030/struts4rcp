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
		return getResource(Client.getClient(), resourceName, args);
	}

	/**
	 * 从默认客户端实例中，获取同步Resource代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @return 同步Resource代理
	 */
	public static <R extends Serializable> Directory<R> getDirectory(String resourceName, Object... args) {
		return getDirectory(Client.getClient(), resourceName, args);
	}

	/**
	 * 从默认客户端实例中，获取同步Resource代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @return 同步Resource代理
	 */
	public static <R extends Serializable> Resource<R> getResource(Client cleint, String resourceName, Object... args) {
		return cleint.getResourceFactory().getResource(resourceName, args);
	}

	/**
	 * 从默认客户端实例中，获取同步Resource代理
	 *
	 * @param resourceName
	 *            resource名称
	 * @return 同步Resource代理
	 */
	public static <R extends Serializable> Directory<R> getDirectory(Client cleint, String resourceName, Object... args) {
		return cleint.getResourceFactory().getDirectory(resourceName, args);
	}

}
