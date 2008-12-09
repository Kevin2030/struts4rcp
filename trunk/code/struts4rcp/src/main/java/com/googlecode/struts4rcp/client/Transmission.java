package com.googlecode.struts4rcp.client;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Action执行过程
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Transmission implements Serializable, Cancellable {

	private static final long serialVersionUID = 1L;

	private static final int TRANSMIT_STATUS = 0;

	private static final int TRANSMITING_STATUS = 1;

	private static final int TRANSMITED_STATUS = 2;

	private int status = TRANSMIT_STATUS;

	private Object statusLock = new Object();

	public Transmission(String actionName, Serializable model) {
		super();
		this.id = nextId();
		this.actionName = actionName;
		this.model = model;
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

	private final Date transmitTime = new Date();

	/**
	 * 获取初始化开始时间
	 * @return 初始化开始时间
	 */
	public Date getTransmitTime() {
		return transmitTime;
	}

	/**
	 * 是否开始执行
	 * @return 是否开始执行
	 */
	public boolean isTransmit() {
		synchronized (statusLock) {
			return status == TRANSMIT_STATUS;
		}
	}

	private Cancellable abortor;

	/**
	 * 中止传输
	 */
	public void cancel() throws Exception {
		if (abortor != null) {
			synchronized (abortor) {
				abortor.cancel();
			}
		}
	}

	public boolean isAbortable() {
		return (abortor != null);
	}

	private Date transmitingTime;

	/**
	 * 获取传输开始时间
	 * @return 开始时间
	 */
	public Date getTransmitingTime() {
		return transmitingTime;
	}

	/**
	 * 是否已开始传输
	 * @return 已开始传输
	 */
	public boolean isTransmiting() {
		synchronized (statusLock) {
			return status == TRANSMITING_STATUS;
		}
	}

	/**
	 * 开始传输
	 * @param abortor 中止传输封装体
	 */
	public void transmiting(Cancellable abortor) {
		synchronized (statusLock) {
			if (status != TRANSMIT_STATUS)
				throw new IllegalStateException("This transportation already transported!");
			status = TRANSMITING_STATUS;
		}
		this.abortor = abortor;
		this.transmitingTime = new Date();
	}

	private Serializable result;

	/**
	 * 获取传输完成结果
	 * @return 传输完成结果
	 */
	public Serializable getResult() {
		return result;
	}

	private Date transmitedTime;

	/**
	 * 获取传输完成时间
	 * @return 传输完成时间
	 */
	public Date getTransmitedTime() {
		return transmitedTime;
	}

	/**
	 * 是否已传输完成
	 * @return 已传输完成
	 */
	public boolean isTransmited() {
		synchronized (statusLock) {
			return status == TRANSMITED_STATUS;
		}
	}

	/**
	 * 传输完成
	 * @param result 传输完成结果
	 */
	public void transmited(Serializable result) {
		synchronized (statusLock) {
			if (status != TRANSMITING_STATUS)
				throw new IllegalStateException("This transportation already transported!");
			status = TRANSMITED_STATUS;
		}
		this.result = result;
		this.abortor = null;
		this.transmitedTime = new Date();
	}

	@Override
	public String toString() {
		return "[NO." + getId() + "] [" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(getTransmitTime()) + "] " + getActionName();
	}

}
