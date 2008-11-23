package com.googlecode.struts4rcp.util.logger;

import java.io.Serializable;

public class NoneLogger implements Logger, Serializable {

	private static final long serialVersionUID = 1L;

	private NoneLogger(){}

	private static NoneLogger instance = new NoneLogger();

	public static NoneLogger getInstance() {
		return instance;
	}

	public void debug(String msg) {

	}

	public void debug(String msg, Throwable e) {

	}

	public void error(String msg) {

	}

	public void error(String msg, Throwable e) {

	}

	public void info(String msg) {

	}

	public void info(String msg, Throwable e) {

	}

	public boolean isDebugEnabled() {
		return false;
	}

	public boolean isErrorEnabled() {
		return false;
	}

	public boolean isFatalEnabled() {
		return false;
	}

	public boolean isInfoEnabled() {
		return false;
	}

	public boolean isWarnEnabled() {
		return false;
	}

	public void warn(String msg) {
	}

	public void warn(String msg, Throwable e) {
	}

}
