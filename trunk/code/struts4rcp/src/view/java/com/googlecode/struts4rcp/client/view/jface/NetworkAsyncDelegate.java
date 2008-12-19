package com.googlecode.struts4rcp.client.view.jface;

import com.googlecode.struts4rcp.client.event.NetworkEvent;
import com.googlecode.struts4rcp.client.event.NetworkListener;

/**
 * 连接事件监听器UI线程执行委托，如果UI线程非空闲，则异步延后处理。
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class NetworkAsyncDelegate implements NetworkListener {

	private final NetworkListener listener;

	private final boolean runOnUI;

	private final boolean runOnNonUI;

	public NetworkAsyncDelegate(NetworkListener listener) {
		this(listener, true, true);
	}

	public NetworkAsyncDelegate(NetworkListener listener, boolean runOnUI, boolean runOnNonUI) {
		this.listener = listener;
		this.runOnUI = runOnUI;
		this.runOnNonUI = runOnNonUI;
	}

	public void onConnected(final NetworkEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onConnected(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
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

	public void onDisconnected(final NetworkEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onDisconnected(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
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
