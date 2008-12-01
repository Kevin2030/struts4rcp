package com.googlecode.struts4rcp.client.view.swing;

import com.googlecode.struts4rcp.client.event.TransportationEvent;
import com.googlecode.struts4rcp.client.event.TransportationListener;

/**
 * 传输事件监听器UI线程执行委托，如果UI线程非空闲，则异步延后处理。
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransportationAsyncDelegate implements TransportationListener {

	private final TransportationListener listener;

	private final boolean runOnUI;

	private final boolean runOnNonUI;

	public TransportationAsyncDelegate(TransportationListener listener) {
		this(listener, true, true);
	}

	public TransportationAsyncDelegate(TransportationListener listener, boolean runOnUI, boolean runOnNonUI) {
		this.listener = listener;
		this.runOnUI = runOnUI;
		this.runOnNonUI = runOnNonUI;
	}

	public void onTransporting(final TransportationEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onTransporting(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onTransporting(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onTransported(final TransportationEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onTransported(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onTransported(event);
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
