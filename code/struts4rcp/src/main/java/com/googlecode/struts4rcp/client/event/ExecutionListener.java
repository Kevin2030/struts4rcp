package com.googlecode.struts4rcp.client.event;

/**
 * 执行事件监听器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ExecutionListener extends Listener {

	/**
	 * 当开始前台执行时，触发此事件
	 * @param event 执行事件
	 */
	void onExecuting(ExecutionEvent event);

	/**
	 * 当开始后台执行时，触发此事件
	 * @param event 执行事件
	 */
	void onBackExecuting(ExecutionEvent event);

	/**
	 * 当执行结束时，触发此事件
	 * @param event 执行事件
	 */
	void onExecuted(ExecutionEvent event);

}
