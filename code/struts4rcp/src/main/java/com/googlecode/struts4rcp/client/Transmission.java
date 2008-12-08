package com.googlecode.struts4rcp.client;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Action执行过程
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Transmission implements Serializable, Backable, Abortable {

	private static final long serialVersionUID = 1L;

	private static final int INITIALIZING_STATUS = 0;

	private static final int EXECUTING_STATUS = 1;

	private static final int TRANSPORTING_STATUS = 2;

	private static final int TRANSPORTED_STATUS = 3;

	private static final int EXECUTED_STATUS = 4;

	private int status = INITIALIZING_STATUS;

	private Object statusLock = new Object();

	public Transmission(String actionName, Serializable model, boolean back, boolean backable, boolean abortable) {
		super();
		this.id = nextId();
		this.actionName = actionName;
		this.model = model;
		this.back = back;
		this.backable = backable;
		this.abortable = abortable;
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

	private final Date initializingTime = new Date();

	/**
	 * 获取初始化开始时间
	 * @return 初始化开始时间
	 */
	public Date getInitializingTime() {
		return initializingTime;
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

	// -------- execute --------

	private boolean back;

	/**
	 * 是否为后台运行
	 * @return 是否为后台运行
	 */
	public boolean isBack() {
		return back;
	}

	private final boolean backable;

	/**
	 * 是否允许转为后台运行
	 * @return 是否允许转为后台运行
	 */
	public boolean isBackable() {
		return backable;
	}

	private Backable backer;

	/**
	 * 转为后台运行
	 */
	public void back() {
		if (isBackable() && ! back && backer != null) {
			synchronized (backer) {
				back = true;
				backer.back();
			}
		}
	}

	private Date executingTime;

	/**
	 * 获取开始执行时间
	 * @return 开始执行时间
	 */
	public Date getExecutingTime() {
		return executingTime;
	}

	/**
	 * 是否开始执行
	 * @return 是否开始执行
	 */
	public boolean isExecuting() {
		synchronized (statusLock) {
			return status == EXECUTING_STATUS;
		}
	}

	/**
	 * 执行
	 * @param backer 转为后台执行的封装体
	 */
	public void executing(Backable backer) {
		synchronized (statusLock) {
			if (status != INITIALIZING_STATUS)
				throw new IllegalStateException("This execution already executed!");
			status = EXECUTING_STATUS;
		}
		this.backer = backer;
		this.executingTime = new Date();
	}

	private Date executedTime;

	/**
	 * 获取执行结束时间
	 * @return 执行结束时间
	 */
	public Date getExecutedTime() {
		return executedTime;
	}

	/**
	 * 是否已执行结束
	 * @return 是否已执行结束
	 */
	public boolean isExecuted() {
		synchronized (statusLock) {
			return status == EXECUTED_STATUS;
		}
	}

	/**
	 * 执行结束
	 */
	public void executed() {
		synchronized (statusLock) {
			if (status == EXECUTED_STATUS)
				throw new IllegalStateException("This execution already executed!");
			status = EXECUTED_STATUS;
		}
		backer = null;
		executedTime = new Date();
	}

	// -------- transport --------

	private final boolean abortable;

	/**
	 * 是否允许中止传输
	 * @return 是否允许中止传输
	 */
	public boolean isAbortable() {
		return abortable;
	}

	private Abortable abortor;

	/**
	 * 中止传输
	 */
	public void abort() throws IOException {
		if (isAbortable() && abortor != null) {
			synchronized (abortor) {
				abortor.abort();
			}
		}
	}

	private Date transportingTime;

	/**
	 * 获取传输开始时间
	 * @return 开始时间
	 */
	public Date getTransportingTime() {
		return transportingTime;
	}

	/**
	 * 是否已开始传输
	 * @return 已开始传输
	 */
	public boolean isTransporting() {
		synchronized (statusLock) {
			return status == TRANSPORTING_STATUS;
		}
	}

	/**
	 * 开始传输
	 * @param abortor 中止传输封装体
	 */
	public void transporting(Abortable abortor) {
		synchronized (statusLock) {
			if (status != EXECUTING_STATUS)
				throw new IllegalStateException("This transportation already transported!");
			status = TRANSPORTING_STATUS;
		}
		this.abortor = abortor;
		this.transportingTime = new Date();
	}

	private Serializable result;

	/**
	 * 获取传输完成结果
	 * @return 传输完成结果
	 */
	public Serializable getResult() {
		return result;
	}

	private Date transportedTime;

	/**
	 * 获取传输完成时间
	 * @return 传输完成时间
	 */
	public Date getTransportedTime() {
		return transportedTime;
	}

	/**
	 * 是否已传输完成
	 * @return 已传输完成
	 */
	public boolean isTransported() {
		synchronized (statusLock) {
			return status == TRANSPORTED_STATUS || status == EXECUTED_STATUS;
		}
	}

	/**
	 * 传输完成
	 * @param result 传输完成结果
	 */
	public void transported(Serializable result) {
		synchronized (statusLock) {
			if (status == TRANSPORTED_STATUS || status == EXECUTED_STATUS)
				throw new IllegalStateException("This transportation already transported!");
			status = TRANSPORTED_STATUS;
		}
		this.result = result;
		this.abortor = null;
		this.transportedTime = new Date();
	}

	@Override
	public String toString() {
		return "[NO." + getId() + "] [" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(getExecutingTime()) + "] " + getActionName();
	}

}
