package com.googlecode.struts4rcp.client;

import java.io.Serializable;
import java.util.Properties;

/**
 * 资源代理工厂
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ResourceFactory implements ClientElement {

	private Client client;

	public Client getClient() {
		return client;
	}

	public void init(Client client, Properties properties) {
		this.client = client;
	}

	public void shutdown() {
	}

	/**
	 * 获取资源代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @param args 资源位置占位参数
	 * @return 资源
	 */
	public <R extends Serializable> Resource<R> getResource(String uri, Object... args) {
		return null;
	}

	/**
	 * 获取资源目录代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @return 资源
	 */
	public <R extends Serializable> Directory<R> getDirectory(String uri, Object... args) {
		return null;
	}

}
