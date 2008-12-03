package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.Action;

/**
 * 根据请求路径序列化数据的Action接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <M> 传入模型类型
 * @param <R> 返回值类型
 */
public interface PathAction<M extends Serializable, R extends Serializable> extends Action<M, R> {

	/**
	 * 获取请求路径
	 * @return 请求路径
	 */
	String getPath();

}
