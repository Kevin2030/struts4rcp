package com.googlecode.struts4rcp.client.view.swing;

import java.awt.EventQueue;

import com.googlecode.struts4rcp.client.event.ConfigurationEvent;
import com.googlecode.struts4rcp.client.event.ConfigurationListener;

/**
 * 连接事件监听器UI线程执行委托，如果UI线程非空闲，则等待。
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ConfigurationDelegate implements ConfigurationListener {

	private final ConfigurationListener listener;

	private final boolean runOnUI;

	private final boolean runOnNonUI;

	public ConfigurationDelegate(ConfigurationListener listener) {
		this(listener, true, true);
	}

	public ConfigurationDelegate(ConfigurationListener listener, boolean runOnUI, boolean runOnNonUI) {
		this.listener = listener;
		this.runOnUI = runOnUI;
		this.runOnNonUI = runOnNonUI;
	}

	public void onChanged(final ConfigurationEvent event) {
		try {
			if (EventQueue.isDispatchThread()) {
				if (runOnUI) {
					listener.onChanged(event);
				}
			} else {
				if (runOnNonUI) {
					EventQueue.invokeAndWait(new Runnable() { // 在UI线程内执行
						public void run() {
							listener.onChanged(event);
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
