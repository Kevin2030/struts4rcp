package com.googlecode.struts4rcp.client.event;

import java.util.EventListener;

/**
 * 事件监听器基接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Listener extends EventListener {

	/**
	 * 是否异步
	 * @return
	 */
	boolean isAsync();

}
