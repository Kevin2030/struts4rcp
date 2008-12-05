package com.googlecode.struts4rcp.server;

import java.io.Serializable;

/**
 * 请求转向异常
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ActionForwardException extends ForwardException {

	private static final long serialVersionUID = 1L;

	public ActionForwardException(String target, Serializable model) {
		super(target, model);
	}

}
