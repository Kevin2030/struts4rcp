package com.googlecode.struts4rcp;

import java.io.Serializable;

/**
 * 远程执行Action接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <M> 传入模型类型
 * @param <R> 返回值类型
 */
public interface Action<M extends Serializable, R extends Serializable> {

	/**
	 * 执行Action
	 * @param model 传入参数
	 * @return 传回返回值
	 * @throws Exception 执行失败或网络连接出错时抛出
	 */
	R execute(M model) throws Exception;

}