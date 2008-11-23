package com.googlecode.struts4rcp.client.provider;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.client.ActionCallback;
import com.googlecode.struts4rcp.client.ActionManager;
import com.googlecode.struts4rcp.client.Backable;
import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.Execution;
import com.googlecode.struts4rcp.client.Transporter;
import com.googlecode.struts4rcp.client.event.ExceptionEvent;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.client.event.ExceptionPublisher;
import com.googlecode.struts4rcp.client.event.ExecutionEvent;
import com.googlecode.struts4rcp.client.event.ExecutionListener;
import com.googlecode.struts4rcp.client.event.ExecutionPublisher;
import com.googlecode.struts4rcp.util.ThreadUtils;
import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;

/**
 * Action代理供给策略缺省实现
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class DefaultActionManager implements ActionManager {

	protected Logger logger = LoggerFactory.getLogger(getClass());

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
			throw new IllegalStateException("ActionProvider already initialized!");
		this.client = client;
	}

	private Collection<Execution> foreExecutions = new HashSet<Execution>();

	private Collection<Execution> backExecutions = new HashSet<Execution>();

	/**
	 * 获取所有正在执行的执行项
	 * @return 正在前台执行的执行项
	 */
	public Collection<Execution> getExecutions() {
		Collection<Execution> copies = new HashSet<Execution>();
		synchronized (foreExecutions) {
			copies.addAll(foreExecutions);
		}
		synchronized (backExecutions) {
			copies.addAll(backExecutions);
		}
		return Collections.unmodifiableCollection(copies);
	}

	/**
	 * 是否正在执行
	 * @return 是否正在执行
	 */
	public boolean isExecuting() {
		synchronized (foreExecutions) {
			if (! foreExecutions.isEmpty())
				return true;
		}
		synchronized (backExecutions) {
			if (! backExecutions.isEmpty())
				return true;
		}
		return false;
	}

	/**
	 * 获取正在前台执行的执行项
	 * @return 正在前台执行的执行项
	 */
	public Collection<Execution> getForeExecutions() {
		Collection<Execution> copies = new HashSet<Execution>();
		synchronized (foreExecutions) {
			copies.addAll(foreExecutions);
		}
		return Collections.unmodifiableCollection(copies);
	}

	/**
	 * 是否正在前台执行
	 * @return 是否正在前台执行
	 */
	public boolean isForeExecuting() {
		synchronized (foreExecutions) {
			return ! foreExecutions.isEmpty();
		}
	}

	/**
	 * 获取正在后台执行的执行项
	 * @return 正在后台执行的执行项
	 */
	public Collection<Execution> getBackExecutions() {
		Collection<Execution> copies = new HashSet<Execution>();
		synchronized (backExecutions) {
			copies.addAll(backExecutions);
		}
		return Collections.unmodifiableCollection(copies);
	}

	/**
	 * 是否正在后台执行
	 * @return 是否正在后台执行
	 */
	public boolean isBackExecuting() {
		synchronized (backExecutions) {
			return ! backExecutions.isEmpty();
		}
	}

	protected void addExecution(Execution execution) {
		if (execution == null)
			throw new NullPointerException("Execution == null!");
		if (execution.isBack()) {
			synchronized (backExecutions) {
				backExecutions.add(execution);
			}
		} else {
			synchronized (foreExecutions) {
				foreExecutions.add(execution);
			}
		}
		execution.executing(new Backer(execution));
		executionPublisher.publishEvent(new ExecutionEvent(this, execution));
	}

	protected void removeExecution(Execution execution) {
		if (execution == null)
			throw new NullPointerException("Execution == null!");
		synchronized (foreExecutions) {
			foreExecutions.remove(execution);
		}
		synchronized (backExecutions) {
			backExecutions.remove(execution);
		}
		execution.executed();
		executionPublisher.publishEvent(new ExecutionEvent(this, execution));
	}

	private class Backer implements Backable {

		private Execution execution;

		public Backer(Execution execution) {
			if (execution == null)
				throw new NullPointerException("Execution == null!");
			this.execution = execution;
		}

		public void back() {
			synchronized (foreExecutions) {
				foreExecutions.remove(execution);
			}
			synchronized (backExecutions) {
				backExecutions.add(execution);
			}
			executionPublisher.publishEvent(new ExecutionEvent(this, execution));
		}

	}

	private ExecutionPublisher executionPublisher = new ExecutionPublisher();

	/**
	 * 添加前台执行监听器
	 * @param listener 前台执行监听器
	 */
	public void addExecutionListener(ExecutionListener listener) {
		executionPublisher.addListener(listener);
	}

	/**
	 * 移除前台执行监听器
	 * @param listener 前台执行监听器
	 */
	public void removeExecutionListener(ExecutionListener listener) {
		executionPublisher.removeListener(listener);
	}

	private ExceptionPublisher exceptionPublisher = new ExceptionPublisher();

	/**
	 * 添加异常事件监听器
	 * @param listener 异常事件监听器
	 */
	public void addExceptionListener(ExceptionListener listener) {
		exceptionPublisher.addListener(listener);
	}

	/**
	 * 移除异常事件监听器
	 * @param listener 异常事件监听器
	 */
	public void removeExceptionListener(ExceptionListener listener) {
		exceptionPublisher.removeListener(listener);
	}

	protected void publishException(Throwable exception, boolean back) {
		exceptionPublisher.publishEvent(new ExceptionEvent(this, exception, back));
	}

	public void shutdown() {
		try {
			executionPublisher.clearListeners();
		} finally {
			exceptionPublisher.clearListeners();
		}
	}

	/**
	 * 获取同步Action代理
	 *
	 * @param transporter 传输器
	 * @param actionName action名称
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 同步Action代理
	 */
	public <M extends Serializable, R extends Serializable> Action<M, R> getAction(
			Transporter transporter, String actionName, boolean backable, boolean abortable) {
		return new SyncActionProxy<M, R>(transporter, actionName, false, backable, abortable);
	}

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
	public <M extends Serializable, R extends Serializable> Action<M, R> getAsyncAction(
			Transporter transporter, String actionName, ActionCallback<R> actionCallback, boolean backable, boolean abortable) {
		return new AsyncActionProxy<M, R>(transporter, actionName, actionCallback, false, backable, abortable);
	}

	/**
	 * 获取后台同步Action代理
	 *
	 * @param transporter 传输器
	 * @param actionName action名称
	 * @param backable 是否允许转为后台运行
	 * @param abortable 是否允许中止
	 * @return 后台同步Action代理
	 */
	public <M extends Serializable, R extends Serializable> Action<M, R> getBackAction(
			Transporter transporter, String actionName, boolean abortable) {
		return new SyncActionProxy<M, R>(transporter, actionName, true, false, abortable);
	}

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
	public <M extends Serializable, R extends Serializable> Action<M, R> getBackAsyncAction(
			Transporter transporter, String actionName,
			ActionCallback<R> actionCallback, boolean abortable) {
		return new AsyncActionProxy<M, R>(transporter, actionName, actionCallback, true, false, abortable);
	}

	protected void assertResult(Serializable result) throws Exception {
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
	}

	/**
	 * 同步Action代理
	 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
	 */
	protected final class SyncActionProxy<M extends Serializable, R extends Serializable> implements Action<M, R> {

		protected final Transporter transporter;

		protected final String actionName;

		protected final boolean back;

		protected final boolean backable;

		protected final boolean abortable;

		SyncActionProxy(Transporter transporter, String actionName, boolean back, boolean backable, boolean abortable) {
			if (transporter == null)
				throw new NullPointerException("transporter == null!");
			if (actionName == null)
				throw new NullPointerException("actionName == null!");
			this.transporter = transporter;
			this.actionName = actionName;
			this.back = back;
			this.backable = backable;
			this.abortable = abortable;
		}

		@SuppressWarnings("unchecked")
		public R execute(final M model) throws Exception {
			final Execution execution = new Execution(actionName, model, back, backable, abortable);
			DefaultActionManager.this.addExecution(execution);
			try {
				Serializable result = transporter.transport(execution);
				assertResult(result);
				return (R)result;
			} finally {
				DefaultActionManager.this.removeExecution(execution);
			}
		}
	}

	/**
	 * 异步Action代理
	 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
	 */
	protected final class AsyncActionProxy<M extends Serializable, R extends Serializable> implements Action<M, R> {

		protected final Transporter transporter;

		protected final String actionName;

		protected final ActionCallback<R> actionCallback;

		protected final boolean back;

		protected final boolean backable;

		protected final boolean abortable;

		AsyncActionProxy(Transporter transporter, String actionName, ActionCallback<R> actionCallback,
				boolean back, boolean backable, boolean abortable) {
			if (transporter == null)
				throw new NullPointerException("transporter == null!");
			if (actionName == null)
				throw new NullPointerException("actionName == null!");
			this.transporter = transporter;
			this.actionName = actionName;
			this.actionCallback = actionCallback;
			this.back = back;
			this.backable = backable;
			this.abortable = abortable;
		}

		public R execute(final M model) throws Exception {
			ThreadUtils.execute(new Runnable() {
				@SuppressWarnings("unchecked")
				public void run() {
					Execution execution = new Execution(actionName, model, back, backable, abortable);
					DefaultActionManager.this.addExecution(execution);
					try {
						try {
							Serializable obj = transporter.transport(execution);
							assertResult(obj);
							R result = (R)obj;
							if (actionCallback != null)
								actionCallback.callback(result);
						} catch (Exception e) {
							if (actionCallback != null)
								actionCallback.catchException(e);
							else
								throw e;
						}
					} catch (Throwable e) {
						DefaultActionManager.this.publishException(e, back);
						logger.error(e.getMessage(), e);
					} finally {
						DefaultActionManager.this.removeExecution(execution);
					}
				}
			});
			return null;
		}
	}

}
