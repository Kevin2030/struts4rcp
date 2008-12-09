package com.googlecode.struts4rcp.client;

/**
 * 工作接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface WorkRunnable {

	/**
	 * 工作
	 * @param context 上下文
	 * @throws Exception 工作出错时抛出
	 */
	void run(WorkContext context) throws Exception;

}
