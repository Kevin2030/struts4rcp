package com.googlecode.struts4rcp.client.transferrer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.Transfer;
import com.googlecode.struts4rcp.util.PropertiesUtils;
import com.googlecode.struts4rcp.util.serializer.stream.StreamSerializer;

/**
 * 基于Commons-HttpClient的传输器实现
 * @see org.apache.commons.httpclient.HttpClient
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class CommonsHttpClientTransferrer extends AbstractTransferrer<HttpMethod> {

	/**
	 * 空闲连接超时时间配置参数名
	 */
	public final static String IDLE_CONNECTION_TIMEOUT_KEY = "idle.connection.timeout";

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
	public final static String IDLE_CONNECTION_CHECK_INTERVAL_KEY = "idle.connection.check.interval";

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
		idleConnectionTimeout = PropertiesUtils.getIntProperty(config, IDLE_CONNECTION_TIMEOUT_KEY, UNKNOWN_IDLE_CONNECTION_TIMEOUT);
		client.addPropertyInfo(IDLE_CONNECTION_TIMEOUT_KEY,  "空闲连接超时时间(ms)", "暂未实现动态修改空闲连接超时时间，修改后不会生效!", "3000");
		// 读取空闲连接检查时间间隔
		idleConnectionCheckInterval = PropertiesUtils.getIntProperty(config, IDLE_CONNECTION_CHECK_INTERVAL_KEY, UNKNOWN_IDLE_CONNECTION_CHECK_INTERVAL);
		client.addPropertyInfo(IDLE_CONNECTION_CHECK_INTERVAL_KEY,  "空闲连接检查时间间隔(ms)", "暂未实现动态修改空闲连接检查时间间隔，修改后不会生效!", "1000");
		// 设置
		httpClient = createHttpClient();
		if (httpClient == null)
			throw new NullPointerException("HttpClient == null!");
		idleConnectionTimeoutThread = new IdleConnectionTimeoutThread();
		idleConnectionTimeoutThread.addConnectionManager(httpClient.getHttpConnectionManager());
		httpClient.getHostConfiguration().setHost(hostAddress, hostPort);
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
	public void close() throws IOException {
		try {
			super.close();
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
	protected HttpMethod getRequest(String method, String url) throws IOException {
		if (Transfer.POST_METHOD.equalsIgnoreCase(method))
			return new PostMethod(url);
		if (Transfer.PUT_METHOD.equalsIgnoreCase(method))
			return new PutMethod(url);
		if (Transfer.GET_METHOD.equalsIgnoreCase(method))
			return new GetMethod(url);
		if (Transfer.DELETE_METHOD.equalsIgnoreCase(method))
			return new DeleteMethod(url);
		if (Transfer.HEAD_METHOD.equalsIgnoreCase(method))
			return new HeadMethod(url);
		throw new IllegalArgumentException("un supported http method: " + method);
	}

	@Override
	protected void setHeader(HttpMethod request, String key, String value) throws IOException {
		request.setRequestHeader(key, value);
	}

	@Override
	protected Serializable transfer(HttpMethod request, String url, Serializable model)
			throws IOException {
		try {
			if (request instanceof EntityEnclosingMethod)
				((EntityEnclosingMethod)request).setRequestEntity(new SerializeEntity(serializer, model));
			int code = httpClient.executeMethod(request);
			assertSuccessStatusCode(code, request.getStatusText());
			return serializer.deserialize(Serializable.class, request.getResponseBodyAsStream());
		} finally {
			request.releaseConnection();
		}
	}

	@Override
	protected void abort(HttpMethod request) throws IOException {
		if(request instanceof EntityEnclosingMethod
				&& ((EntityEnclosingMethod)request).isAborted())
			return;
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
