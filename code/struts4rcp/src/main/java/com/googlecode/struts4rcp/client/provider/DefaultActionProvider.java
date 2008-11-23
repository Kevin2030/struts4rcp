package com.googlecode.struts4rcp.client.provider;

import java.io.Serializable;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.client.ActionCallback;
import com.googlecode.struts4rcp.client.Execution;
import com.googlecode.struts4rcp.client.Transporter;
import com.googlecode.struts4rcp.util.ThreadUtils;

/**
 * Action代理供给策略缺省实现
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class DefaultActionProvider extends AbstractActionProvider {

	public <M extends Serializable, R extends Serializable> Action<M, R> getAction(
			Transporter transporter, String actionName, boolean backable, boolean abortable) {
		return new SyncActionProxy<M, R>(transporter, actionName, false, backable, abortable);
	}

	public <M extends Serializable, R extends Serializable> Action<M, R> getAsyncAction(
			Transporter transporter, String actionName, ActionCallback<R> actionCallback, boolean backable, boolean abortable) {
		return new AsyncActionProxy<M, R>(transporter, actionName, actionCallback, false, backable, abortable);
	}

	@Override
	public <M extends Serializable, R extends Serializable> Action<M, R> getBackAction(
			Transporter transporter, String actionName, boolean abortable) {
		return new SyncActionProxy<M, R>(transporter, actionName, true, false, abortable);
	}

	@Override
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
			DefaultActionProvider.this.addExecution(execution);
			try {
				Serializable result = transporter.transport(execution);
				assertResult(result);
				return (R)result;
			} finally {
				DefaultActionProvider.this.removeExecution(execution);
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
					DefaultActionProvider.this.addExecution(execution);
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
						DefaultActionProvider.this.publishException(e, back);
						logger.error(e.getMessage(), e);
					} finally {
						DefaultActionProvider.this.removeExecution(execution);
					}
				}
			});
			return null;
		}
	}

}
