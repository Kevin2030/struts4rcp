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

	public static void fore(String title, Workable workable) {
		Worker.getWorker().fore(title, workable);
	}

	public static void fore(String title, boolean backable, Workable workable) {
		Worker.getWorker().fore(title, backable, workable);
	}

	public static void back(Workable workable) {
		Worker.getWorker().back(workable);
	}

}
