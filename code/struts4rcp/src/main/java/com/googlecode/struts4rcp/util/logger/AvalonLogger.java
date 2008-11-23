package com.googlecode.struts4rcp.util.logger;

import java.io.Serializable;

/**
 * Avalon适配实现
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class AvalonLogger implements Logger, Serializable {

	private static final long serialVersionUID = 1L;

	private final org.apache.log.Logger logger;

	public AvalonLogger(org.apache.log.Logger logger) {
		this.logger = logger;
	}

	public void debug(String msg) {
		logger.debug(msg);
	}

	public void debug(String msg, Throwable e) {
		logger.debug(msg, e);
	}

	public void info(String msg) {
		logger.info(msg);
	}

	public void info(String msg, Throwable e) {
		logger.info(msg, e);
	}

	public void warn(String msg) {
		logger.warn(msg);
	}

	public void warn(String msg, Throwable e) {
		logger.warn(msg, e);
	}

	public void error(String msg) {
		logger.error(msg);
	}

	public void error(String msg, Throwable e) {
		logger.error(msg, e);
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return logger.isFatalErrorEnabled();
	}

}
