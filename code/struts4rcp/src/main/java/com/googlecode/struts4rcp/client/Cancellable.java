package com.googlecode.struts4rcp.client;

/**
 * 中止传输接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Cancellable {

	/**
	 * 中止传输
	 * @throws Exception 中止出错时抛出
	 */
	void cancel() throws Exception;

}
