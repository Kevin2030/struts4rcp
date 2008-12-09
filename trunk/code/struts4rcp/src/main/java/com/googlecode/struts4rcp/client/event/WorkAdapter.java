package com.googlecode.struts4rcp.client.event;

/**
 * 执行事件监听器空方法适配
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class WorkAdapter extends Adapter implements WorkListener {

	public void onForeWorking(WorkEvent event) {}

	public void onBackWorking(WorkEvent event) {}

	public void onWorked(WorkEvent event) {}

}
