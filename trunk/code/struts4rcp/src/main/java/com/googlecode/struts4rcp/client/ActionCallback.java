package com.googlecode.struts4rcp.client;

import java.io.Serializable;

import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;

/**
 * Action异步执行回调接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 执行结果类型
 */
public abstract class ActionCallback<R extends Serializable> {

	// 日志输出接口
	protected static final Logger logger = LoggerFactory.getLogger(ActionCallback.class);

	/**
	 * Action异步执行成功回调接口函数
	 * @param result 执行结果
	 * @throws Exception 回调出错时抛出，该异常同样会回调给<code>catchException()</code>
	 */
	public abstract void callback(R result) throws Exception;

	/**
	 * 捕获异常
	 * @param e 执行异常
	 */
	public void catchException(Exception exception) throws Exception {
		throw exception;
	}

}
