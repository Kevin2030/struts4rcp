package com.googlecode.struts4rcp.client.view.swing;

import java.awt.EventQueue;

import com.googlecode.struts4rcp.client.event.ExceptionEvent;
import com.googlecode.struts4rcp.client.event.ExceptionListener;

/**
 * 连接事件监听器UI线程执行委托，如果UI线程非空闲，则等待。
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ExceptionAsyncDelegate implements ExceptionListener {

	private final ExceptionListener listener;

	private final boolean runOnUI;

	private final boolean runOnNonUI;

	public ExceptionAsyncDelegate(ExceptionListener listener) {
		this(listener, true, true);
	}

	public ExceptionAsyncDelegate(ExceptionListener listener, boolean runOnUI, boolean runOnNonUI) {
		this.listener = listener;
		this.runOnUI = runOnUI;
		this.runOnNonUI = runOnNonUI;
	}

	public void onCatched(final ExceptionEvent event) {
		try {
			if (EventQueue.isDispatchThread()) {
				if (runOnUI) {
					listener.onCatched(event);
				}
			} else {
				if (runOnNonUI) {
					EventQueue.invokeLater(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onCatched(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onBackCatched(final ExceptionEvent event) {
		try {
			if (EventQueue.isDispatchThread()) {
				if (runOnUI) {
					listener.onBackCatched(event);
				}
			} else {
				if (runOnNonUI) {
					EventQueue.invokeLater(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onBackCatched(event);
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
