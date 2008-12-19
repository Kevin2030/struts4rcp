package com.googlecode.struts4rcp.client.view.jface;

import com.googlecode.struts4rcp.client.event.TransferEvent;
import com.googlecode.struts4rcp.client.event.TransferListener;

/**
 * 传输事件监听器UI线程执行委托，如果UI线程非空闲，则异步延后处理。
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransferAsyncDelegate implements TransferListener {

	private final TransferListener listener;

	private final boolean runOnUI;

	private final boolean runOnNonUI;

	public TransferAsyncDelegate(TransferListener listener) {
		this(listener, true, true);
	}

	public TransferAsyncDelegate(TransferListener listener, boolean runOnUI, boolean runOnNonUI) {
		this.listener = listener;
		this.runOnUI = runOnUI;
		this.runOnNonUI = runOnNonUI;
	}

	public void onTransfer(final TransferEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onTransfer(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onTransfer(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onTransferring(final TransferEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onTransferring(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onTransferring(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onTransferred(final TransferEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onTransferred(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onTransferred(event);
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
