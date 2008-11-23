package com.googlecode.struts4rcp.util.logger;

public class SimpleLoggerProvider implements LoggerProvider {

	public Logger getLogger(String key) {
		return new SimpleLogger(key);
	}

}
