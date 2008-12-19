package com.googlecode.struts4rcp.client;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 传输项
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Transfer implements Serializable, Abortable {

	private static final long serialVersionUID = 1L;

	public static final String POST_METHOD = "post";

	public static final String PUT_METHOD = "put";

	public static final String GET_METHOD = "get";

	public static final String DELETE_METHOD = "delete";

	public static final String HEAD_METHOD = "head";

	private static final int INITIALIZING_STATUS = 0;

	private static final int TRANSFERRING_STATUS = 1;

	private static final int TRANSFERRED_STATUS = 2;

	private int status = INITIALIZING_STATUS;

	private Object statusLock = new Object();

	public Transfer(String uri, Serializable model) {
		this(uri, model, POST_METHOD, null);
	}

	public Transfer(String uri, Serializable model, String method) {
		this(uri, model, method, null);
	}

	public Transfer(String uri, Serializable model, String method, Map<String, String> headers) {
		this.id = nextId();
		this.actionName = uri;
		this.model = model;
		this.method = method;
		this.headers = headers;
	}

	// -------- init --------

	private static int i = 0;

	private static final Object idLock = new Object();

	// 产生执行序号
	private static int nextId() {
		synchronized (idLock) {
			i = i + 1;
			return i;
		}
	}

	private final long id;

	/**
	 * 获取执行序号
	 * @return 执行序号
	 */
	public long getId() {
		return id;
	}

	private final String actionName;

	/**
	 * 获取执行Action的名称
	 * @return 执行Action的名称
	 */
	public String getActionName() {
		return actionName;
	}

	private final Serializable model;

	/**
	 * 获取传输数据模型
	 * @return 数据模型
	 */
	public Serializable getModel() {
		return model;
	}

	private final String method;

	public String getMethod() {
		return method;
	}

	private final Map<String, String> headers;

	public Map<String, String> getHeaders() {
		return headers;
	}

	private final Date initializeTime = new Date();

	/**
	 * 获取初始化开始时间
	 * @return 初始化开始时间
	 */
	public Date getInitializeTime() {
		return initializeTime;
	}

	/**
	 * 是否开始执行
	 * @return 是否开始执行
	 */
	public boolean isInitializing() {
		synchronized (statusLock) {
			return status == INITIALIZING_STATUS;
		}
	}

	private Abortable abortor;

	/**
	 * 中止传输
	 */
	public void abort() throws Exception {
		if (abortor != null) {
			synchronized (abortor) {
				abortor.abort();
			}
		}
	}

	public boolean isAbortable() {
		return (abortor != null);
	}

	private Date transferringTime;

	/**
	 * 获取传输开始时间
	 * @return 开始时间
	 */
	public Date getTransferringTime() {
		return transferringTime;
	}

	/**
	 * 是否已开始传输
	 * @return 已开始传输
	 */
	public boolean isTransferring() {
		synchronized (statusLock) {
			return status == TRANSFERRING_STATUS;
		}
	}

	/**
	 * 开始传输
	 * @param abortor 中止传输封装体
	 */
	public void transferring(Abortable abortor) {
		synchronized (statusLock) {
			if (status != INITIALIZING_STATUS)
				throw new IllegalStateException("This transportation already transported!");
			status = TRANSFERRING_STATUS;
		}
		this.abortor = abortor;
		this.transferringTime = new Date();
	}

	private Serializable result;

	/**
	 * 获取传输完成结果
	 * @return 传输完成结果
	 */
	public Serializable getResult() {
		return result;
	}

	private Date transferredTime;

	/**
	 * 获取传输完成时间
	 * @return 传输完成时间
	 */
	public Date getTransferredTime() {
		return transferredTime;
	}

	/**
	 * 是否已传输完成
	 * @return 已传输完成
	 */
	public boolean isTransferred() {
		synchronized (statusLock) {
			return status == TRANSFERRED_STATUS;
		}
	}

	/**
	 * 传输完成
	 * @param result 传输完成结果
	 */
	public void transferred(Serializable result) {
		synchronized (statusLock) {
			if (status != TRANSFERRING_STATUS)
				throw new IllegalStateException("This transportation already transported!");
			status = TRANSFERRED_STATUS;
		}
		this.result = result;
		this.abortor = null;
		this.transferredTime = new Date();
	}

	@Override
	public String toString() {
		return "[NO." + getId() + "] [" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(getInitializeTime()) + "] " + getActionName();
	}

}
