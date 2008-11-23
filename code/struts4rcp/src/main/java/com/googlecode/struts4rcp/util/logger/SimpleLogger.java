package com.googlecode.struts4rcp.util.logger;

import java.io.Serializable;

/**
 * 简单Logger实现
 *
 * @author liangfei0201@163.com
 *
 */
public class SimpleLogger implements Logger, Serializable {

	private static final long serialVersionUID = 1L;

	private String prefix;

	public SimpleLogger(String key) {
		if (key != null) {
			int i = key.lastIndexOf('.');
			if (i > -1)
				key = key.substring(i + 1);
			this.prefix = "[" + key + "]";
		}
	}

	private String getMessage(String msg) {
		if (prefix == null)
			return msg;
		return prefix + msg;
	}

	public void debug(String msg) {
		System.out.println(getMessage(msg));
	}

	public void debug(String msg, Throwable e) {
		System.out.println(getMessage(msg));
		if (e != null)
			e.printStackTrace();
	}

	public void info(String msg) {
		System.out.println(getMessage(msg));
	}

	public void info(String msg, Throwable e) {
		System.out.println(getMessage(msg));
		if (e != null)
			e.printStackTrace();
	}

	public void warn(String msg) {
		System.err.println(getMessage(msg));
	}

	public void warn(String msg, Throwable e) {
		System.err.println(getMessage(msg));
		if (e != null)
			e.printStackTrace();
	}

	public void error(String msg) {
		System.err.println(getMessage(msg));
	}

	public void error(String msg, Throwable e) {
		System.err.println(getMessage(msg));
		if (e != null)
			e.printStackTrace();
	}

	public boolean isDebugEnabled() {
		return true;
	}

	public boolean isInfoEnabled() {
		return true;
	}

	public boolean isWarnEnabled() {
		return true;
	}

	public boolean isErrorEnabled() {
		return true;
	}

	public boolean isFatalEnabled() {
		return true;
	}

};
