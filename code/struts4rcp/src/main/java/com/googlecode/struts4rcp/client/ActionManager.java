package com.googlecode.struts4rcp.client;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.ActionFactory;
import com.googlecode.struts4rcp.ActionInterceptor;
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
 * Action代理供给策略接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ActionManager implements ActionFactory, ClientElement {

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

	private ActionInterceptorChain actionInterceptorChain = null;

	public void addActionInterceptor(ActionInterceptor actionInterceptor) {
		ActionInterceptorChain next = new ActionInterceptorChain(actionInterceptor);
		if (actionInterceptorChain == null) {
			actionInterceptorChain = next;
		} else {
			actionInterceptorChain.setNext(next);
		}
	}

	public void removeActionInterceptor(ActionInterceptor actionInterceptor) {
		if (actionInterceptorChain == null || actionInterceptor == null)
			return;
		if (actionInterceptorChain.getActionInterceptor() == actionInterceptor)
			actionInterceptorChain = actionInterceptorChain.getNext();
		ActionInterceptorChain curr = actionInterceptorChain.getNext();
		while (curr != null) {
			if (curr.getActionInterceptor() == actionInterceptor) {
				curr.setNext(curr.getNext());
				break;
			}
			curr = curr.getNext();
		}
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
	 * @return 同步Action代理
	 */
	public <M extends Serializable, R extends Serializable> Action<M, R> getAction(
			String actionName) {
		return getAction(actionName, true, true);
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
	public <M extends Serializable, R extends Serializable> Action<M, R> getAction(String actionName, boolean backable, boolean abortable) {
		return new ActionProxy<M, R>(client.getTransporter(), actionName, false, backable, abortable);
	}

	/**
	 * 获取异步Action代理
	 *
	 * @param transporter 传输器
	 * @param actionName action名称
	 * @param actionCallback 回调接口
	 * @return 异步Action代理
	 */
	public <M extends Serializable, R extends Serializable> Action<M, R> getAsyncAction(String actionName, ActionCallback<R> actionCallback) {
		return getAsyncAction(actionName, actionCallback, true, true);
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
	public <M extends Serializable, R extends Serializable> Action<M, R> getAsyncAction(String actionName, ActionCallback<R> actionCallback, boolean backable, boolean abortable) {
		Action<M, R> action = getAction(actionName, backable, abortable);
		return new AsyncActionProxy<M, R>(action, actionCallback, false);
	}

	/**
	 * 获取后台同步Action代理
	 *
	 * @param transporter 传输器
	 * @param actionName action名称
	 * @return 后台同步Action代理
	 */
	public <M extends Serializable, R extends Serializable> Action<M, R> getBackAction(String actionName) {
		return getBackAction(actionName, true);
	}

	/**
	 * 获取后台同步Action代理
	 *
	 * @param transporter 传输器
	 * @param actionName action名称
	 * @param abortable 是否允许中止
	 * @return 后台同步Action代理
	 */
	public <M extends Serializable, R extends Serializable> Action<M, R> getBackAction(String actionName, boolean abortable) {
		return new ActionProxy<M, R>(client.getTransporter(), actionName, true, false, abortable);
	}

	/**
	 * 获取后台异步Action代理
	 *
	 * @param transporter 传输器
	 * @param actionName action名称
	 * @param actionCallback 回调接口
	 * @return 后台异步Action代理
	 */
	public <M extends Serializable, R extends Serializable> Action<M, R> getBackAsyncAction(String actionName, ActionCallback<R> actionCallback) {
		return getBackAsyncAction(actionName, actionCallback, true);
	}

	/**
	 * 获取后台异步Action代理
	 *
	 * @param transporter 传输器
	 * @param actionName action名称
	 * @param actionCallback 回调接口
	 * @param abortable 是否允许中止
	 * @return 后台异步Action代理
	 */
	public <M extends Serializable, R extends Serializable> Action<M, R> getBackAsyncAction(String actionName,
			ActionCallback<R> actionCallback, boolean abortable) {
		Action<M, R> action = getBackAction(actionName, abortable);
		return new AsyncActionProxy<M, R>(action, actionCallback, true);
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
	 * Action代理
	 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
	 */
	protected final class ActionProxy<M extends Serializable, R extends Serializable> implements Action<M, R> {

		protected final Transporter transporter;

		protected final String actionName;

		protected final boolean back;

		protected final boolean backable;

		protected final boolean abortable;

		ActionProxy(Transporter transporter, String actionName, boolean back, boolean backable, boolean abortable) {
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
			ActionManager.this.addExecution(execution);
			try {
				Serializable result = transporter.transport(execution);
				assertResult(result);
				return (R)result;
			} finally {
				ActionManager.this.removeExecution(execution);
			}
		}
	}

	/**
	 * 异步Action代理
	 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
	 */
	protected final class AsyncActionProxy<M extends Serializable, R extends Serializable> implements Action<M, R> {

		protected final Action<M, R> action;

		protected final ActionCallback<R> actionCallback;

		protected final boolean back;

		AsyncActionProxy(Action<M, R> action, ActionCallback<R> actionCallback, boolean back) {
			this.action = action;
			this.actionCallback = actionCallback;
			this.back = back;
		}

		public R execute(final M model) throws Exception {
			ThreadUtils.execute(new Runnable() {
				public void run() {
					try {
						try {
							R result = action.execute(model);
							if (actionCallback != null)
								actionCallback.callback(result);
						} catch (Exception e) {
							if (actionCallback != null)
								actionCallback.catchException(e);
							else
								throw e;
						}
					} catch (Throwable e) {
						ActionManager.this.publishException(e, back);
						logger.error(e.getMessage(), e);
					}
				}
			});
			return null;
		}
	}

	/**
	 * Action 拦截代理
	 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
	 */
	protected final class InterceptActionProxy<M extends Serializable, R extends Serializable> implements Action<M, R> {

		private final Action<M, R> action;

		public InterceptActionProxy(Action<M, R> action) {
			this.action = action;
		}

		@SuppressWarnings("unchecked")
		public R execute(M model) throws Exception {
			final ActionInterceptorChain chain = actionInterceptorChain;
			if (chain != null)
				return (R)actionInterceptorChain.intercept((Action) action, model);
			return action.execute(model);
		}

	}

	/**
	 * 拦截器链
	 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
	 */
	protected static final class ActionInterceptorChain implements ActionInterceptor {

		// 当前拦截器
		private final ActionInterceptor actionInterceptor;

		/**
		 * 构造拦截器链
		 * @param actionInterceptor 当前拦截器
		 */
		ActionInterceptorChain(ActionInterceptor actionInterceptor) {
			if (actionInterceptor == null)
				throw new NullPointerException("actionInterceptor == null");
			this.actionInterceptor = actionInterceptor;
		}

		ActionInterceptor getActionInterceptor() {
			return actionInterceptor;
		}

		// 下一拦截器
		private ActionInterceptorChain next;

		/**
		 * 设置下载拦截器链节点
		 * @param next 下一拦截器
		 */
		void setNext(ActionInterceptorChain next) {
			this.next = next;
		}

		ActionInterceptorChain getNext() {
			return next;
		}

		void clear() {
			if (next != null) {
				next.clear();
				next = null;
			}
		}

		public Serializable intercept(Action<Serializable, Serializable> action, Serializable model) throws Exception {
			if (next == null)
				return actionInterceptor.intercept(action, model); // 如果没有下一拦载器，传入实际Action实例
			return actionInterceptor.intercept(new ActionDelegate(next, action), model); // 如果有下一拦载器，则代理下一拦截器
		}

	}


	/**
	 * Action拦截委托
	 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
	 */
	protected static final class ActionDelegate implements Action<Serializable, Serializable> {

		private final ActionInterceptor actionInterceptor;

		private final Action<Serializable, Serializable> action;

		ActionDelegate(ActionInterceptor actionInterceptor, Action<Serializable, Serializable> action) {
			if (action == null)
				throw new NullPointerException("action == null!");
			this.actionInterceptor = actionInterceptor;
			this.action = action;
		}

		public Serializable execute(Serializable model) throws Exception {
			if (actionInterceptor != null)
				return actionInterceptor.intercept(action, model); // 如果有拦截器，则执行拦截器
			return action.execute(model); // 否则，直接执行Action实例
		}

	}

}
