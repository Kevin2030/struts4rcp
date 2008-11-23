package com.googlecode.struts4rcp.client.transporter;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.googlecode.struts4rcp.client.Abortable;
import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.Execution;
import com.googlecode.struts4rcp.client.Transporter;
import com.googlecode.struts4rcp.client.event.ConnectionEvent;
import com.googlecode.struts4rcp.client.event.ConnectionListener;
import com.googlecode.struts4rcp.client.event.ConnectionPublisher;
import com.googlecode.struts4rcp.client.event.TransportationEvent;
import com.googlecode.struts4rcp.client.event.TransportationListener;
import com.googlecode.struts4rcp.client.event.TransportationPublisher;
import com.googlecode.struts4rcp.util.PropertiesUtils;
import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;
import com.googlecode.struts4rcp.util.serializer.JavaSerializer;
import com.googlecode.struts4rcp.util.serializer.Serializer;

/**
 * HTTP传输器基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class AbstractHttpTransporter<T> implements Transporter {

	/**
	 * 日志输出接口
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 序列化器配置参数名
	 */
	public final static String SERIALIZER_PARAM_NAME = "serializer";

	/**
	 * 序列化器
	 */
	protected Serializer serializer;

	/**
	 * 服务器主机配置参数名
	 */
	public final static String SERVER_HOST_PARAM_NAME = "server.host";

	/**
	 * 服务器主机
	 */
	protected String serverHost;

	/**
	 * 服务器端口配置参数名
	 */
	public final static String SERVER_PORT_PARAM_NAME = "server.port";

	/**
	 * 未知服务器端口
	 */
	protected final static int UNKNOWN_SERVER_PORT = -1;

	/**
	 * 服务器端口
	 */
	protected int serverPort = UNKNOWN_SERVER_PORT;

	/**
	 * 应用上下文路径配置参数名
	 */
	public final static String CONTEXT_PATH_PARAM_NAME = "context.path";

	/**
	 * 应用上下文路径
	 */
	protected String contextPath;

	/**
	 * Action请求后缀配置参数名
	 */
	public final static String ACTION_SUFFIX_PARAM_NAME = "action.suffix";

	/**
	 * 缺省Action后缀
	 */
	protected final static String DEFAULT_ACTION_SUFFIX = "data";

	/**
	 * Action请求后缀
	 */
	protected String actionSuffix;

	/**
	 * 连接超时时间配置参数名
	 */
	public final static String CONNECTION_TIMEOUT_PARAM_NAME = "connection.timeout";

	/**
	 * 未知连接超时时间
	 */
	protected final static int UNKNOWN_CONNECTION_TIMEOUT = -1;

	/**
	 * 连接超时时间
	 */
	protected int connectionTimeout = UNKNOWN_CONNECTION_TIMEOUT;

	/**
	 * 套接字读取超时时间配置参数名
	 */
	public final static String SOCKET_TIMEOUT_PARAM_NAME = "socket.timeout";

	/**
	 * 未知套接字读取时间
	 */
	protected final static int UNKNOWN_SOCKET_TIMEOUT = -1;

	/**
	 * 套接字读取超时时间
	 */
	protected int socketTimeout = UNKNOWN_SOCKET_TIMEOUT;

	/**
	 * 连接监控时间间隔配置参数名
	 */
	public final static String CONNECTION_CHECK_INTERVAL_PARAM_NAME = "connection.check.interval";

	/**
	 * 缺省连接监控时间间隔
	 */
	protected final static int DEFAULT_CONNECTION_CHECK_INTERVAL = 5000;

	/**
	 * 连接监控时间间隔
	 */
	protected int connectionCheckInterval = DEFAULT_CONNECTION_CHECK_INTERVAL;

	// HTTP协议前缀
	private final static String HTTP_PROTOCAL = "http://";

	// HTTP缺省端口
	private final static int HTTP_DEFAULT_PORT = 80;

	// Action请求URL前缀
	private String urlPrefix;

	// Action请求URL后缀
	private String urlSuffix;

	private Client client;

	public Client getClient() {
		return client;
	}

	public void init(Client client, Properties config) {
		if (client == null)
			throw new NullPointerException("Client == null!");
		if (config == null)
			throw new NullPointerException("Properties == null!");
		if (this.client != null)
			throw new IllegalStateException("Transporter already initialized!");
		this.client = client;

		// 读取序列化器
		serializer = PropertiesUtils.getInstanceProperty(config, SERIALIZER_PARAM_NAME, Serializer.class, JavaSerializer.class);

		// 读取服务器主机名
		serverHost = PropertiesUtils.getStringProperty(config, SERVER_HOST_PARAM_NAME, null);
		if (serverHost == null)
			throw new NullPointerException("server.host == null, 服务器主机名不能为空!");
		client.getConfigurationManager().register(SERVER_HOST_PARAM_NAME, "服务器主机名", "此配置项用于传输实现，修改此配置需重启应用!");

		// 读取服务器端口
		serverPort = PropertiesUtils.getIntProperty(config, SERVER_PORT_PARAM_NAME, UNKNOWN_SERVER_PORT);
		client.getConfigurationManager().register(SERVER_PORT_PARAM_NAME,  "服务器端口", "此配置项用于传输实现，修改此配置需重启应用!", "80");

		// 读取上下文路径
		contextPath = PropertiesUtils.getStringProperty(config, CONTEXT_PATH_PARAM_NAME, "");

		// 读取Action后缀
		actionSuffix = PropertiesUtils.getStringProperty(config, ACTION_SUFFIX_PARAM_NAME, DEFAULT_ACTION_SUFFIX);

		// 读取连接超时时间
		connectionTimeout = PropertiesUtils.getIntProperty(config, CONNECTION_TIMEOUT_PARAM_NAME, UNKNOWN_CONNECTION_TIMEOUT);

		// 读取套接字读取超时时间
		socketTimeout = PropertiesUtils.getIntProperty(config, SOCKET_TIMEOUT_PARAM_NAME, UNKNOWN_SOCKET_TIMEOUT);

		// 读取监控时间间隔
		connectionCheckInterval = PropertiesUtils.getIntProperty(config, CONNECTION_CHECK_INTERVAL_PARAM_NAME, DEFAULT_CONNECTION_CHECK_INTERVAL);
		if (connectionCheckInterval > 0) {
			scheduler = Executors.newScheduledThreadPool(1);
			future = scheduler.scheduleWithFixedDelay(new Runnable() {
				public void run() {
					refresh();
				}
			}, connectionCheckInterval, connectionCheckInterval, TimeUnit.MILLISECONDS);
		} else {
			scheduler = null;
			future = null;
		}
		// 初始化连接状态事件
		boolean changed = refresh();
		if (! changed) // 如果未改变，也触发
			connectionPublisher.publishEvent(new ConnectionEvent(AbstractHttpTransporter.this, isConnected()));

		// ---- 组装前缀 ----
		urlPrefix = HTTP_PROTOCAL + serverHost;
		if (serverPort != UNKNOWN_SERVER_PORT && serverPort != HTTP_DEFAULT_PORT)
			urlPrefix += ":" + serverPort;
		if (contextPath.length() > 0)
			urlPrefix += "/" + contextPath;
		urlPrefix = urlPrefix + "/";
		// ---- 组装后缀 ----
		urlSuffix = "." + actionSuffix;
	}

	public void shutdown() {
		try {
			transportationPublisher.clearListeners();
		} finally {
			try {
				connectionPublisher.clearListeners();
			} finally {
				try {
					if (future != null && ! future.isCancelled())
						future.cancel(true);
				} finally {
					if (scheduler != null && ! scheduler.isShutdown())
						scheduler.shutdown();
				}
			}
		}
	}

	public Serializable transport(Execution execution) throws IOException {
		String actionName = execution.getActionName();
		Serializable model = execution.getModel();
		actionName = urlPrefix + actionName + urlSuffix;
		Serializable result = null;
		T request = getRequest(actionName);
		Abortor abortor = new Abortor(request);
		addExecution(execution, abortor);
		try {
			result = transport(request, actionName, model);
			return result;
		} catch (Throwable e) {
			result = e;
			if (e instanceof Error)
				throw (Error)e;
			if (e instanceof RuntimeException)
				throw (RuntimeException)e;
			if (e instanceof IOException)
				throw (IOException)e;
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			removeExecution(execution, result);
		}
	}

	private final Collection<Execution> executions = new HashSet<Execution>();

	public boolean isTransporting() {
		synchronized (executions) {
			return ! executions.isEmpty();
		}
	}

	public Collection<Execution> getExecutions() {
		Collection<Execution> copies = new HashSet<Execution>();
		synchronized (executions) {
			copies.addAll(executions);
		}
		return Collections.unmodifiableCollection(copies);
	}

	protected void addExecution(Execution execution, Abortable abortor) {
		synchronized (executions) {
			executions.add(execution);
		}
		execution.transporting(abortor);
		transportationPublisher.publishEvent(new TransportationEvent(AbstractHttpTransporter.this, execution));
	}

	protected void removeExecution(Execution execution, Serializable result) {
		synchronized (executions) {
			executions.remove(execution);
		}
		execution.transported(result);
		transportationPublisher.publishEvent(new TransportationEvent(AbstractHttpTransporter.this, execution));
	}

	/**
	 * 获取请求信息
	 * @param url Action名称
	 * @return 请求信息
	 * @throws IOException
	 */
	protected abstract T getRequest(String url) throws IOException;

	/**
	 * 传输对象
	 * @param request 请求信息
	 * @param url 请求路径
	 * @param model 传入对象
	 * @return 传回对象
	 * @throws IOException 传输出错时抛出
	 */
	protected abstract Serializable transport(T request, String url, Serializable model) throws IOException;

	/**
	 * 打断连接
	 * @param request 请求信息
	 * @throws IOException
	 */
	protected abstract void abort(T request) throws IOException;

	/**
	 * 断言响应信息状态码为成功状态
	 * @param code 状态码
	 * @param msg 状态信息
	 * @throws IOException 如果不是成功状态码时抛出
	 */
	protected void assertSuccessStatusCode(int code, String msg) throws IOException {
		if (code < 200 || code > 299) // HTTP协议，200-299表示(向后兼容)成功状态码
			throw new IOException("ERROR[" + code + "] Reason: " + msg);
	}

	private TransportationPublisher transportationPublisher = new TransportationPublisher();

	public void addTransportationListener(TransportationListener listener) {
		transportationPublisher.addListener(listener);
	}

	public void removeTransportationListener(TransportationListener listener) {
		transportationPublisher.removeListener(listener);
	}

	private final class Abortor implements Abortable {

		private final T request;

		Abortor(T request) {
			this.request = request;
		}

		public void abort() throws IOException {
			AbstractHttpTransporter.this.abort(request);
		}
	}

	public boolean ping() {
		try {
			new Socket(serverHost, serverPort).close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private ScheduledExecutorService scheduler;

	private ScheduledFuture<?> future;

	private boolean connected;

	private final Object connectionLock = new Object();

	public boolean isConnected() {
		synchronized (connectionLock) {
			return connected;
		}
	}

	public boolean refresh() {
		synchronized (connectionLock) {
			try {
				boolean currentConnection = ping();
				if (connected != currentConnection) {
					connected = currentConnection;
					connectionPublisher.publishEvent(new ConnectionEvent(AbstractHttpTransporter.this, connected));
					return true;
				}
			} catch (Throwable e) {
				logger.warn(e.getMessage(), e);
			}
			return false;
		}
	}

	private ConnectionPublisher connectionPublisher = new ConnectionPublisher();

	public void addConnectionListener(ConnectionListener listener) {
		connectionPublisher.addListener(listener);
		connectionPublisher.publishEvent(listener, new ConnectionEvent(AbstractHttpTransporter.this, isConnected()));
	}

	public void removeConnectionListener(ConnectionListener listener) {
		connectionPublisher.removeListener(listener);
	}

}
