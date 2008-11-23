package com.googlecode.struts4rcp.util.logger;

public class NoneLoggerProvider implements LoggerProvider {

	public Logger getLogger(String key) {
		return NoneLogger.getInstance();
	}

}
