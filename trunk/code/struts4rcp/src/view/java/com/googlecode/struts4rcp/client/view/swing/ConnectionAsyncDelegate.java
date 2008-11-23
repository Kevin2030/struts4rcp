package com.googlecode.struts4rcp.client.view.swing;

import java.awt.EventQueue;

import com.googlecode.struts4rcp.client.event.ConnectionEvent;
import com.googlecode.struts4rcp.client.event.ConnectionListener;

/**
 * 连接事件监听器UI线程执行委托，如果UI线程非空闲，则异步延后处理。
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ConnectionAsyncDelegate implements ConnectionListener {

	private final ConnectionListener listener;

	private final boolean runOnUI;

	private final boolean runOnNonUI;

	public ConnectionAsyncDelegate(ConnectionListener listener) {
		this(listener, true, true);
	}

	public ConnectionAsyncDelegate(ConnectionListener listener, boolean runOnUI, boolean runOnNonUI) {
		this.listener = listener;
		this.runOnUI = runOnUI;
		this.runOnNonUI = runOnNonUI;
	}

	public void onConnected(final ConnectionEvent event) {
		try {
			if (EventQueue.isDispatchThread()) {
				if (runOnUI) {
					listener.onConnected(event);
				}
			} else {
				if (runOnNonUI) {
					EventQueue.invokeLater(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onConnected(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onDisconnected(final ConnectionEvent event) {
		try {
			if (EventQueue.isDispatchThread()) {
				if (runOnUI) {
					listener.onDisconnected(event);
				}
			} else {
				if (runOnNonUI) {
					EventQueue.invokeLater(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onDisconnected(event);
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
