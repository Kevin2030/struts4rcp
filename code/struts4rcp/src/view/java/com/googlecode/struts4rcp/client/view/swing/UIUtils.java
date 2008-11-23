package com.googlecode.struts4rcp.client.view.swing;

import java.awt.EventQueue;

/**
 * UI工具类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class UIUtils {

	private UIUtils() {}

	public static boolean isUIThread() {
		return EventQueue.isDispatchThread();
	}

	public static boolean isUIThread(Thread thread) {
		return "java.awt.EventDispatchThread".equals(thread.getClass().getName());
	}

	public static boolean isNonUIThread() {
		return ! isUIThread();
	}

	public static boolean isNonUIThread(Thread thread) {
		return ! isUIThread(thread);
	}

}
