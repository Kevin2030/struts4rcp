package com.googlecode.struts4rcp.client.view.swing;

import com.googlecode.struts4rcp.client.event.WorkEvent;
import com.googlecode.struts4rcp.client.event.WorkListener;

/**
 * 阻塞事件监听器UI线程执行委托，如果UI线程非空闲，则异步延后处理。
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class WorkAsyncDelegate implements WorkListener {

	private final WorkListener listener;

	private final boolean runOnUI;

	private final boolean runOnNonUI;

	public WorkAsyncDelegate(WorkListener listener) {
		this(listener, true, true);
	}

	public WorkAsyncDelegate(WorkListener listener, boolean runOnUI, boolean runOnNonUI) {
		this.listener = listener;
		this.runOnUI = runOnUI;
		this.runOnNonUI = runOnNonUI;
	}

	public void onForeWorking(final WorkEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onForeWorking(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onForeWorking(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onBackWorking(final WorkEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onBackWorking(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onBackWorking(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onWorked(final WorkEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onWorked(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onWorked(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public boolean isAsync() {
		return listener.isAsync();
	}

}
