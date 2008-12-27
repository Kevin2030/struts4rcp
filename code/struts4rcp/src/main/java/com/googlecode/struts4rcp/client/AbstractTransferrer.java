package com.googlecode.struts4rcp.client;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.googlecode.struts4rcp.client.event.NetworkEvent;
import com.googlecode.struts4rcp.client.event.NetworkListener;
import com.googlecode.struts4rcp.client.event.NetworkPublisher;
import com.googlecode.struts4rcp.client.event.TransferEvent;
import com.googlecode.struts4rcp.client.event.TransferListener;
import com.googlecode.struts4rcp.client.event.TransferPublisher;
import com.googlecode.struts4rcp.util.PropertiesUtils;
import com.googlecode.struts4rcp.util.ServiceUtils;
import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;
import com.googlecode.struts4rcp.util.serializer.stream.JavaStreamSerializer;
import com.googlecode.struts4rcp.util.serializer.stream.StreamSerializer;

/**
 * HTTP传输器基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class AbstractTransferrer<T> implements Transferrer {

	/**
	 * 日志输出接口
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 序列化器配置参数名
	 */
	public final static String SERIALIZER_KEY = "serializer";

	/**
	 * 序列化器
	 */
	protected StreamSerializer serializer;

	/**
	 * 服务器主机配置参数名
	 */
	public final static String HOST_ADDRESS_KEY = "host.address";

	/**
	 * 服务器主机
	 */
	protected String hostAddress;

	/**
	 * 服务器端口配置参数名
	 */
	public final static String HOST_PORT_KEY = "host.port";

	/**
	 * 未知服务器端口
	 */
	protected final static int UNKNOWN_HOST_PORT = -1;

	/**
	 * 服务器端口
	 */
	protected int hostPort = UNKNOWN_HOST_PORT;

	/**
	 * 应用上下文路径配置参数名
	 */
	public final static String CONTEXT_PATH_KEY = "context.path";

	/**
	 * 应用上下文路径
	 */
	protected String contextPath;

	/**
	 * 应用上下文路径配置参数名
	 */
	public final static String MAPPING_PATH_KEY = "mapping.path";

	/**
	 * 应用上下文路径
	 */
	protected String mappingPath;

	/**
	 * Action请求后缀配置参数名
	 */
	public final static String MAPPING_EXTENSION_KEY = "mapping.extension";

	/**
	 * 缺省Action后缀
	 */
	protected final static String DEFAULT_MAPPING_EXTENSION = "data";

	/**
	 * Action请求后缀
	 */
	protected String mappingExtension;

	/**
	 * 连接超时时间配置参数名
	 */
	public final static String CONNECTION_TIMEOUT_KEY = "connection.timeout";

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
	public final static String SOCKET_TIMEOUT_KEY = "socket.timeout";

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
	public final static String CONNECTION_CHECK_INTERVAL_KEY = "connection.check.interval";

	/**
	 * 缺省连接监控时间间隔
	 */
	protected final static int DEFAULT_CONNECTION_CHECK_INTERVAL = 5000;

	/**
	 * 连接监控时间间隔
	 */
	protected int connectionCheckInterval = DEFAULT_CONNECTION_CHECK_INTERVAL;

	/**
	 * 最大连接数配置参数名
	 */
	public final static String MAX_CONNECTION_SIZE_KEY = "max.connection.size";

	/**
	 * 缺省最大连接数
	 */
	protected final static int DEFAULT_MAX_CONNECTION_SIZE = 20;

	/**
	 * 最大连接数
	 */
	protected int maxConnectionSize = DEFAULT_MAX_CONNECTION_SIZE;

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
		serializer = PropertiesUtils.getInstanceProperty(config, SERIALIZER_KEY, StreamSerializer.class, JavaStreamSerializer.class);
		client.addPropertyDescription(SERIALIZER_KEY, "序列化策略", "暂未实现动态切换序列化策略，修改后不会生效!",
				JavaStreamSerializer.class.getName(), ServiceUtils.getServiceClassNames(StreamSerializer.class).toArray(new String[0]));

		// 读取服务器主机名
		hostAddress = PropertiesUtils.getStringProperty(config, HOST_ADDRESS_KEY, null);
		if (hostAddress == null)
			throw new NullPointerException("server.host == null, 服务器主机名不能为空!");
		client.addPropertyDescription(HOST_ADDRESS_KEY, "服务器主机名", "暂未实现动态切换服务器名，修改后不会生效!", "");

		// 读取服务器端口
		hostPort = PropertiesUtils.getIntProperty(config, HOST_PORT_KEY, UNKNOWN_HOST_PORT);
		client.addPropertyDescription(HOST_PORT_KEY,  "服务器端口", "暂未实现动态切换服务器端口，修改后不会生效!", "80");

		// 读取上下文路径
		contextPath = PropertiesUtils.getStringProperty(config, CONTEXT_PATH_KEY, "");
		client.addPropertyDescription(CONTEXT_PATH_KEY,  "应用上下文路径", "暂未实现动态切换应用上下文路径，修改后不会生效!", "/");

		// 读取Action后缀
		mappingExtension = PropertiesUtils.getStringProperty(config, MAPPING_EXTENSION_KEY, DEFAULT_MAPPING_EXTENSION);
		client.addPropertyDescription(MAPPING_EXTENSION_KEY,  "Action后缀", "暂未实现动态修改Action后缀，修改后不会生效!", "data");

		// 读取连接超时时间
		connectionTimeout = PropertiesUtils.getIntProperty(config, CONNECTION_TIMEOUT_KEY, UNKNOWN_CONNECTION_TIMEOUT);
		client.addPropertyDescription(CONNECTION_TIMEOUT_KEY,  "HTTP请求连接超时时间(ms)", "暂未实现动态修改HTTP请求连接超时时间，修改后不会生效!", "30000");

		// 读取套接字读取超时时间
		socketTimeout = PropertiesUtils.getIntProperty(config, SOCKET_TIMEOUT_KEY, UNKNOWN_SOCKET_TIMEOUT);
		client.addPropertyDescription(SOCKET_TIMEOUT_KEY,  "套接字读取超时时间(ms)", "暂未实现动态修改套接字读取超时时间，修改后不会生效!", "0");

		// 读取监控时间间隔
		connectionCheckInterval = PropertiesUtils.getIntProperty(config, CONNECTION_CHECK_INTERVAL_KEY, DEFAULT_CONNECTION_CHECK_INTERVAL);
		client.addPropertyDescription(CONNECTION_CHECK_INTERVAL_KEY,  "网络连接状态检查时间间隔(ms)", "暂未实现动态修改网络连接状态检查时间间隔，修改后不会生效!", "0");
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
			connectionPublisher.publishEvent(new NetworkEvent(AbstractTransferrer.this, isConnected()));

		// ---- 组装前缀 ----
		urlPrefix = HTTP_PROTOCAL + hostAddress;
		if (hostPort != UNKNOWN_HOST_PORT && hostPort != HTTP_DEFAULT_PORT)
			urlPrefix += ":" + hostPort;
		if (contextPath.length() > 0)
			urlPrefix += "/" + contextPath;
		urlPrefix = urlPrefix + "/";
		// ---- 组装后缀 ----
		urlSuffix = "." + mappingExtension;
	}

	public void close() throws IOException {
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

	public Serializable transfer(Transfer transmission) throws Exception {
		String uri = transmission.getActionName();
		Serializable model = transmission.getModel();
		String method = transmission.getMethod();
		Map<String, String> headers = transmission.getHeaders();
		uri = urlPrefix + uri + urlSuffix;
		Serializable result = null;
		T request = getRequest(method, uri);
		if (headers != null && headers.size() > 0) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				setHeader(request, entry.getKey(), entry.getValue());
			}
		}
		Abortor abortor = new Abortor(request);
		addTransfer(transmission, abortor);
		try {
			result = transfer(request, uri, model);
			if (result instanceof Exception) { // 抛出异常
				Exception e = (Exception)result;
				logger.error(e.getMessage(), new RuntimeException());
				throw e;
			}
			if (result instanceof Error) { // 抛出错误
				Error e = (Error)result;
				logger.error(e.getMessage(), new RuntimeException());
				throw e;
			}
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
			removeTransfer(transmission, result);
		}
	}

	private final Collection<Transfer> executions = new HashSet<Transfer>();

	public boolean isTransferring() {
		synchronized (executions) {
			return ! executions.isEmpty();
		}
	}

	public Collection<Transfer> getTransfers() {
		Collection<Transfer> copies = new HashSet<Transfer>();
		synchronized (executions) {
			copies.addAll(executions);
		}
		return Collections.unmodifiableCollection(copies);
	}

	protected void addTransfer(Transfer execution, Abortable abortor) {
		transportationPublisher.publishEvent(new TransferEvent(AbstractTransferrer.this, execution));
		synchronized (executions) {
			if (executions.size() >= maxConnectionSize) {
				try {
					executions.wait();
				} catch (InterruptedException e) {
					// ignore
				}
			}
			executions.add(execution);
		}
		execution.transferring(abortor);
		transportationPublisher.publishEvent(new TransferEvent(AbstractTransferrer.this, execution));
	}

	protected void removeTransfer(Transfer execution, Serializable result) {
		synchronized (executions) {
			executions.remove(execution);
			executions.notify();
		}
		execution.transferred(result);
		transportationPublisher.publishEvent(new TransferEvent(AbstractTransferrer.this, execution));
	}

	/**
	 * 获取请求信息
	 * @param url Action名称
	 * @return 请求信息
	 * @throws IOException
	 */
	protected abstract T getRequest(String method, String url) throws IOException;

	/**
	 * 设置头信息
	 * @param key 名称
	 * @param value 值
	 * @throws IOException
	 */
	protected abstract void setHeader(T request, String key, String value) throws IOException;

	/**
	 * 传输对象
	 * @param request 请求信息
	 * @param url 请求路径
	 * @param model 传入对象
	 * @return 传回对象
	 * @throws IOException 传输出错时抛出
	 */
	protected abstract Serializable transfer(T request, String url, Serializable model) throws IOException;

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

	private TransferPublisher transportationPublisher = new TransferPublisher();

	public void addTransferListener(TransferListener listener) {
		transportationPublisher.addListener(listener);
	}

	public void removeTransferListener(TransferListener listener) {
		transportationPublisher.removeListener(listener);
	}

	private final class Abortor implements Abortable {

		private final T request;

		Abortor(T request) {
			this.request = request;
		}

		public void abort() throws IOException {
			AbstractTransferrer.this.abort(request);
		}
	}

	public boolean ping() {
		try {
			new Socket(hostAddress, hostPort).close();
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
					connectionPublisher.publishEvent(new NetworkEvent(AbstractTransferrer.this, connected));
					return true;
				}
			} catch (Throwable e) {
				logger.warn(e.getMessage(), e);
			}
			return false;
		}
	}

	private NetworkPublisher connectionPublisher = new NetworkPublisher();

	public void addNetworkListener(NetworkListener listener) {
		connectionPublisher.addListener(listener);
		connectionPublisher.publishEvent(listener, new NetworkEvent(AbstractTransferrer.this, isConnected()));
	}

	public void removeNetworkListener(NetworkListener listener) {
		connectionPublisher.removeListener(listener);
	}

}
