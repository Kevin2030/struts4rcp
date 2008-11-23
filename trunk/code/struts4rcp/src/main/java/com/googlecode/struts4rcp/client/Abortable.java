package com.googlecode.struts4rcp.client;

import java.io.IOException;

/**
 * 中止传输接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Abortable {

	/**
	 * 中止传输
	 * @throws IOException 中止传输失败时抛出
	 */
	void abort() throws IOException;

}
