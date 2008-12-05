package com.googlecode.struts4rcp.server;

import java.io.Serializable;

/**
 * Servlet跳转异常信息
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ServletForwardException extends ForwardException {

	private static final long serialVersionUID = 1L;

	public ServletForwardException(String target, Serializable model) {
		super(target, model);
	}

}