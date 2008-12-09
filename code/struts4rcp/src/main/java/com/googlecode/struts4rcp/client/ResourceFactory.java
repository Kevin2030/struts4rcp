package com.googlecode.struts4rcp.client;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

/**
 * 资源代理工厂
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ResourceFactory {

	private Client client;

	public Client getClient() {
		return client;
	}

	public ResourceFactory(Client client, Properties properties) {
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
		return new ResourceProxy<R>(format(uri, args));
	}

	/**
	 * 获取资源目录代理
	 * @param <R> 资源类型
	 * @param uri 资源位置
	 * @return 资源
	 */
	public <R extends Serializable> Directory<R> getDirectory(String uri, Object... args) {
		return new DirectoryProxy<R>(format(uri, args));
	}

	private String format(String uri, Object... args) {
		if (uri != null && uri.length() > 0
				&& args != null && args.length > 0) {
			return new MessageFormat(uri, Locale.getDefault()).format(args);
		}
		return uri;
	}

	private class ResourceProxy<R extends Serializable> implements Resource<R> {

		private final String uri;

		ResourceProxy(String uri) {
			if (uri == null)
				throw new NullPointerException("uri == null!");
			this.uri = uri;
		}

		public void create(R resource) throws Exception {
			client.getTransmitter().transmit(Transmitter.POST_METHOD, uri, resource);
		}

		@SuppressWarnings("unchecked")
		public R read() throws Exception {
			return (R)client.getTransmitter().transmit(Transmitter.GET_METHOD, uri, null);
		}

		public void update(R resource) throws Exception {
			client.getTransmitter().transmit(Transmitter.PUT_METHOD, uri, resource);
		}

		public void delete() throws Exception {
			client.getTransmitter().transmit(Transmitter.DELETE_METHOD, uri, null);
		}

		public Directory<R> getDirectory() throws Exception {
			String dir = (String)client.getTransmitter().transmit(Transmitter.HEAD_METHOD, uri, null);
			if (dir == null)
				return null;
			return new DirectoryProxy<R>(dir);
		}

	}

	private class DirectoryProxy<R extends Serializable> implements Directory<R> {

		private final String uri;

		DirectoryProxy(String uri) {
			if (uri == null)
				throw new NullPointerException("uri == null!");
			this.uri = uri;
		}

		public R[] index() throws Exception {
			return index(null);
		}

		public R[] index(R resource) throws Exception {
			return (R[])client.getTransmitter().transmit(Transmitter.GET_METHOD, uri, resource);
		}

	}

}
