package com.googlecode.struts4rcp.client.event;

/**
 * 执行事件监听器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface WorkListener extends Listener {

	/**
	 * 当开始前台执行时，触发此事件
	 * @param event 执行事件
	 */
	void onForeWorking(WorkEvent event);

	/**
	 * 当开始后台执行时，触发此事件
	 * @param event 执行事件
	 */
	void onBackWorking(WorkEvent event);

	/**
	 * 当执行结束时，触发此事件
	 * @param event 执行事件
	 */
	void onWorked(WorkEvent event);

}
