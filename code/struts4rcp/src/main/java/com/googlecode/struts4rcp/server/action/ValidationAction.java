package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.util.validator.Validator;

public interface ValidationAction<M extends Serializable, R extends Serializable> extends Action<M, R> {

	/**
	 * 获取页面路径
	 * @return 页面路径
	 */
	Validator getValidator();

}
