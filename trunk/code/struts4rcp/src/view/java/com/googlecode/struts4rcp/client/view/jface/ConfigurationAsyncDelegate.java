package com.googlecode.struts4rcp.client.view.jface;

import com.googlecode.struts4rcp.client.event.ConfigurationEvent;
import com.googlecode.struts4rcp.client.event.ConfigurationListener;

/**
 * 连接事件监听器UI线程执行委托，如果UI线程非空闲，则等待。
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ConfigurationAsyncDelegate implements ConfigurationListener {

	private final ConfigurationListener listener;

	private final boolean runOnUI;

	private final boolean runOnNonUI;

	public ConfigurationAsyncDelegate(ConfigurationListener listener) {
		this(listener, true, true);
	}

	public ConfigurationAsyncDelegate(ConfigurationListener listener, boolean runOnUI, boolean runOnNonUI) {
		this.listener = listener;
		this.runOnUI = runOnUI;
		this.runOnNonUI = runOnNonUI;
	}

	public void onConfigurationChanged(final ConfigurationEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onConfigurationChanged(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onConfigurationChanged(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onConfigurationAdded(final ConfigurationEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onConfigurationAdded(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onConfigurationAdded(event);
						}
					});
				}
			}
		} catch (Throwable e) {
			// ignore
		}
	}

	public void onConfigurationRemoved(final ConfigurationEvent event) {
		try {
			if (UIUtils.isUIThread()) {
				if (runOnUI) {
					listener.onConfigurationRemoved(event);
				}
			} else {
				if (runOnNonUI) {
					UIUtils.asyncExecute(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onConfigurationRemoved(event);
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
