package com.googlecode.struts4rcp.client.event;

/**
 * 执行事件监听器空方法适配
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ExecutionAdapter extends Adapter implements ExecutionListener {

	public void onExecuting(ExecutionEvent event) {}

	public void onBackExecuting(ExecutionEvent event) {}

	public void onExecuted(ExecutionEvent event) {}

}
