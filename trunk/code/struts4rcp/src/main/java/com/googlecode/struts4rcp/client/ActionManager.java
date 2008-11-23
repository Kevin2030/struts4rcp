package com.googlecode.struts4rcp.client;

import java.io.Serializable;
import java.util.Collection;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.client.event.ExecutionListener;

/**
 * Action代理供给策略接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ActionManager extends ClientElement {

	/**
	 * 获取同步Action代理
	 *
	 * @param transporter 传输器
	 * @param actionName action名称
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 同步Action代理
	 */
	public abstract <M extends Serializable, R extends Serializable> Action<M, R> getAction(Transporter transporter, String actionName, boolean backable, boolean abortable);

	/**
	 * 获取异步Action代理
	 *
	 * @param transporter 传输器
	 * @param actionName action名称
	 * @param actionCallback 回调接口
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 异步Action代理
	 */
	public abstract <M extends Serializable, R extends Serializable> Action<M, R> getAsyncAction(Transporter transporter, String actionName, ActionCallback<R> actionCallback, boolean backable, boolean abortable);

	/**
	 * 获取后台同步Action代理
	 *
	 * @param transporter 传输器
	 * @param actionName action名称
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 后台同步Action代理
	 */
	public abstract <M extends Serializable, R extends Serializable> Action<M, R> getBackAction(Transporter transporter, String actionName, boolean abortable);

	/**
	 * 获取后台异步Action代理
	 *
	 * @param transporter 传输器
	 * @param actionName action名称
	 * @param actionCallback 回调接口
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 后台异步Action代理
	 */
	public abstract <M extends Serializable, R extends Serializable> Action<M, R> getBackAsyncAction(Transporter transporter, String actionName, ActionCallback<R> actionCallback, boolean abortable);

	/**
	 * 获取所有正在执行的执行项
	 * @return 正在前台执行的执行项
	 */
	public abstract Collection<Execution> getExecutions();

	/**
	 * 是否正在执行
	 * @return 是否正在执行
	 */
	public abstract boolean isExecuting();

	/**
	 * 获取正在前台执行的执行项
	 * @return 正在前台执行的执行项
	 */
	public abstract Collection<Execution> getForeExecutions();

	/**
	 * 是否正在前台执行
	 * @return 是否正在前台执行
	 */
	public abstract boolean isForeExecuting();

	/**
	 * 获取正在后台执行的执行项
	 * @return 正在后台执行的执行项
	 */
	public abstract Collection<Execution> getBackExecutions();

	/**
	 * 是否正在后台执行
	 * @return 是否正在后台执行
	 */
	public abstract boolean isBackExecuting();

	/**
	 * 添加前台执行监听器
	 * @param listener 前台执行监听器
	 */
	public abstract void addExecutionListener(ExecutionListener listener);

	/**
	 * 移除前台执行监听器
	 * @param listener 前台执行监听器
	 */
	public abstract void removeExecutionListener(ExecutionListener listener);

	/**
	 * 添加异常事件监听器
	 * @param listener 异常事件监听器
	 */
	public abstract void addExceptionListener(ExceptionListener listener);

	/**
	 * 移除异常事件监听器
	 * @param listener 异常事件监听器
	 */
	public abstract void removeExceptionListener(ExceptionListener listener);

}
