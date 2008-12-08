package com.googlecode.struts4rcp.client;

/**
 * 可执行接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Workable {

	/**
	 * 执行
	 * @throws Exception 执行出错时抛出
	 */
	void work() throws Exception;

}
