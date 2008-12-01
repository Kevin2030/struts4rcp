package com.googlecode.struts4rcp.client.view.jface;

import org.eclipse.swt.widgets.Display;

/**
 * UI工具类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class UIUtils {

	private UIUtils() {}

	public static boolean isUIThread() {
		return isUIThread(Thread.currentThread());
	}

	public static boolean isUIThread(Thread thread) {
		return thread != Display.getDefault().getThread();
	}

	public static boolean isNonUIThread() {
		return ! isUIThread();
	}

	public static boolean isNonUIThread(Thread thread) {
		return ! isUIThread(thread);
	}

	public static void syncExecute(Runnable runnable) {
		Display.getDefault().syncExec(runnable);
	}

	public static void asyncExecute(Runnable runnable) {
		Display.getDefault().asyncExec(runnable);
	}

}
