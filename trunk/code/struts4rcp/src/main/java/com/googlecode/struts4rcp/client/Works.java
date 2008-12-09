package com.googlecode.struts4rcp.client;

/**
 * 执行工作静态门面
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Works {

	private Works() {}

	public static void fore(Workable workable) {
		Worker.getWorker().fore(workable);
	}

	public static void fore(String message, Workable workable) {
		Worker.getWorker().fore(message, workable);
	}

	public static void fore(String message, boolean backable, boolean abortable, Workable workable) {
		Worker.getWorker().fore(message, backable, abortable, workable);
	}

	public static void back(Workable workable) {
		Worker.getWorker().back(workable);
	}

}
