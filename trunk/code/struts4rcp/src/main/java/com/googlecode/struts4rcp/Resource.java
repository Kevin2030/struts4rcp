package com.googlecode.struts4rcp;

import java.io.Serializable;

/**
 * RESTful资源接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <M> 资源类型
 */
public interface Resource<M extends Serializable> {

	/**
	 * 创建资源
	 * @param model 资源信息
	 */
	void create(M model);

	/**
	 * 更新资源
	 * @param model 资源信息
	 */
	void update(M model);

	/**
	 * 移除资源
	 */
	void remove();

	/**
	 * 获取资源
	 * @return 资源
	 */
	M get();

}