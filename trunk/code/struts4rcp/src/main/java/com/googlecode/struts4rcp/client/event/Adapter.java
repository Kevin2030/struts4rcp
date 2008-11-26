package com.googlecode.struts4rcp.client.event;

/**
 * 事件监听器空方法适配
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class Adapter implements Listener {

	public boolean isAsync() {
		return true;
	}

}
