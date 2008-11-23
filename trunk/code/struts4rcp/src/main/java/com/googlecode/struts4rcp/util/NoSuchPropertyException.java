package com.googlecode.struts4rcp.util;

/**
 * 未找到属性异常
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class NoSuchPropertyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchPropertyException() {
		super();
	}

	public NoSuchPropertyException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchPropertyException(String message) {
		super(message);
	}

	public NoSuchPropertyException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

}
