package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.Action;

/**
 * 页面Action
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <M> 传入模型类型
 * @param <R> 返回值类型
 */
public interface PageAction<M extends Serializable, R extends Serializable> extends Action<M, R> {

	/**
	 * 获取页面路径
	 * @return 页面路径
	 */
	String getPage();

}
