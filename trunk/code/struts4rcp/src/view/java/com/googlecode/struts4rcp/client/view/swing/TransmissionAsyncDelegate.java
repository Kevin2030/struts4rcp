package com.googlecode.struts4rcp.client.view.swing;

import com.googlecode.struts4rcp.client.event.TransmissionEvent;
import com.googlecode.struts4rcp.client.event.TransmissionListener;

/**
 * 传输事件监听器UI线程执行委托，如果UI线程非空闲，则异步延后处理。
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransmissionAsyncDelegate implements TransmissionListener {

	private final TransmissionListener listener;

	private final boolean runOnUI;

	private final boolean runOnNonUI;

	public TransmissionAsyncDelegate(TransmissionListener listener) {
		this(listener, true, true);
	}

	public TransmissionAsyncDelegate(TransmissionListener listener, boolean runOnUI, boolean runOnNonUI) {
		this.listener = listener;
		this.runOnUI = runOnUI;
		this.runOnNonUI = runOnNonUI;
	}

	public void onTransmit(final TransmissionEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onTransmit(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onTransmit(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onTransmiting(final TransmissionEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onTransmiting(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onTransmiting(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onTransmited(final TransmissionEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onTransmited(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onTransmited(event);
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
