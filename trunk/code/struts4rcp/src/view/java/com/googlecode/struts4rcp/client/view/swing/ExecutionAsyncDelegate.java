package com.googlecode.struts4rcp.client.view.swing;

import java.awt.EventQueue;

import com.googlecode.struts4rcp.client.event.ExecutionEvent;
import com.googlecode.struts4rcp.client.event.ExecutionListener;

/**
 * 阻塞事件监听器UI线程执行委托，如果UI线程非空闲，则异步延后处理。
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ExecutionAsyncDelegate implements ExecutionListener {

	private final ExecutionListener listener;

	private final boolean runOnUI;

	private final boolean runOnNonUI;

	public ExecutionAsyncDelegate(ExecutionListener listener) {
		this(listener, true, true);
	}

	public ExecutionAsyncDelegate(ExecutionListener listener, boolean runOnUI, boolean runOnNonUI) {
		this.listener = listener;
		this.runOnUI = runOnUI;
		this.runOnNonUI = runOnNonUI;
	}

	public void onExecuting(final ExecutionEvent event) {
		try {
			if (EventQueue.isDispatchThread()) {
				if (runOnUI) {
					listener.onExecuting(event);
				}
			} else {
				if (runOnNonUI) {
					EventQueue.invokeLater(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onExecuting(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onBackExecuting(final ExecutionEvent event) {
		try {
			if (EventQueue.isDispatchThread()) {
				if (runOnUI) {
					listener.onBackExecuting(event);
				}
			} else {
				if (runOnNonUI) {
					EventQueue.invokeLater(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onBackExecuting(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onExecuted(final ExecutionEvent event) {
		try {
			if (EventQueue.isDispatchThread()) {
				if (runOnUI) {
					listener.onExecuted(event);
				}
			} else {
				if (runOnNonUI) {
					EventQueue.invokeLater(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onExecuted(event);
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
