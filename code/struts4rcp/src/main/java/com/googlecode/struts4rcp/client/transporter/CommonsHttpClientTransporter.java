package com.googlecode.struts4rcp.client.transporter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.util.PropertiesUtils;
import com.googlecode.struts4rcp.util.serializer.StreamSerializer;

/**
 * 基于Commons-HttpClient的传输器实现
 * @see org.apache.commons.httpclient.HttpClient
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class CommonsHttpClientTransporter extends AbstractHttpTransporter<PostMethod> {

	/**
	 * 空闲连接超时时间配置参数名
	 */
	public final static String IDLE_CONNECTION_TIMEOUT_PARAM_NAME = "idle.connection.timeout";

	/**
	 * 未知空闲连接超时时间
	 */
	protected final static int UNKNOWN_IDLE_CONNECTION_TIMEOUT = -1;

	/**
	 * 空闲连接超时时间配置参数名
	 */
	protected int idleConnectionTimeout = UNKNOWN_IDLE_CONNECTION_TIMEOUT;

	/**
	 * 空闲连接检查时间间隔配置参数名
	 */
	public final static String IDLE_CONNECTION_CHECK_INTERVAL_PARAM_NAME = "idle.connection.check.interval";

	/**
	 * 未知空闲连接检查时间间隔
	 */
	protected final static int UNKNOWN_IDLE_CONNECTION_CHECK_INTERVAL = 5000;

	/**
	 * 空闲连接检查时间间隔
	 */
	protected int idleConnectionCheckInterval = UNKNOWN_IDLE_CONNECTION_CHECK_INTERVAL;

	/**
	 * HttpClient实例
	 */
	private HttpClient httpClient;

	// 空闲连接检查线程
	private IdleConnectionTimeoutThread idleConnectionTimeoutThread;

	/**
	 * 创建HttpClient实例，子类可以通过覆写此方法，替换实现类
	 * @return HttpClient实例
	 */
	protected HttpClient createHttpClient() {
		return new HttpClient(new MultiThreadedHttpConnectionManager());
	}

	@Override
	public void init(Client client, Properties config) {
		super.init(client, config);
		// 读取空闲连接超时时间
		idleConnectionTimeout = PropertiesUtils.getIntProperty(config, IDLE_CONNECTION_TIMEOUT_PARAM_NAME, UNKNOWN_IDLE_CONNECTION_TIMEOUT);
		client.getConfigurationManager().register(IDLE_CONNECTION_TIMEOUT_PARAM_NAME,  "空闲连接超时时间(ms)", "暂未实现动态修改空闲连接超时时间，修改后不会生效!", "3000");
		// 读取空闲连接检查时间间隔
		idleConnectionCheckInterval = PropertiesUtils.getIntProperty(config, IDLE_CONNECTION_CHECK_INTERVAL_PARAM_NAME, UNKNOWN_IDLE_CONNECTION_CHECK_INTERVAL);
		client.getConfigurationManager().register(IDLE_CONNECTION_CHECK_INTERVAL_PARAM_NAME,  "空闲连接检查时间间隔(ms)", "暂未实现动态修改空闲连接检查时间间隔，修改后不会生效!", "1000");
		// 设置
		httpClient = createHttpClient();
		if (httpClient == null)
			throw new NullPointerException("HttpClient == null!");
		idleConnectionTimeoutThread = new IdleConnectionTimeoutThread();
		idleConnectionTimeoutThread.addConnectionManager(httpClient.getHttpConnectionManager());
		httpClient.getHostConfiguration().setHost(serverHost, serverPort);
		if (connectionTimeout != UNKNOWN_CONNECTION_TIMEOUT)
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
		if (socketTimeout != UNKNOWN_SOCKET_TIMEOUT)
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(socketTimeout);
		if (idleConnectionTimeout != UNKNOWN_IDLE_CONNECTION_TIMEOUT)
			idleConnectionTimeoutThread.setConnectionTimeout(idleConnectionTimeout);
		if (idleConnectionCheckInterval != UNKNOWN_IDLE_CONNECTION_CHECK_INTERVAL)
			idleConnectionTimeoutThread.setTimeoutInterval(idleConnectionCheckInterval);
		idleConnectionTimeoutThread.start();
	}

	@Override
	public void shutdown() {
		try {
			super.shutdown();
		} finally {
			try {
				HttpConnectionManager connectionManager = httpClient.getHttpConnectionManager();
				if (connectionManager instanceof MultiThreadedHttpConnectionManager)
					((MultiThreadedHttpConnectionManager)connectionManager).shutdown();
				else if (connectionManager instanceof SimpleHttpConnectionManager)
					((SimpleHttpConnectionManager)connectionManager).shutdown();
			} finally {
				idleConnectionTimeoutThread.shutdown();
			}
		}
	}

	@Override
	protected PostMethod getRequest(String url) throws IOException {
		return new PostMethod(url);
	}

	@Override
	protected Serializable transport(PostMethod request, String url, Serializable model)
			throws IOException {
		try {
			request.setRequestEntity(new SerializeEntity(serializer, model));
			int code = httpClient.executeMethod(request);
			assertSuccessStatusCode(code, request.getStatusText());
			return serializer.deserialize(Serializable.class, request.getResponseBodyAsStream());
		} finally {
			request.releaseConnection();
		}
	}

	@Override
	protected void abort(PostMethod request) throws IOException {
		if(! request.isAborted())
			request.abort();
	}

	private static class SerializeEntity implements RequestEntity {

		private final StreamSerializer serializer;

		private final Serializable object;

		public SerializeEntity(StreamSerializer serializer, Serializable object) {
			super();
			this.serializer = serializer;
			this.object = object;
		}

		public long getContentLength() {
			return -1;
		}

		public String getContentType() {
			return serializer.getContentType();
		}

		public boolean isRepeatable() {
			return true;
		}

		public void writeRequest(OutputStream out) throws IOException {
			serializer.serialize(object, out);
		}

	}

}
