package com.googlecode.struts4rcp.util.logger;

public class Slf4jLoggerProvider implements LoggerProvider {

	public Logger getLogger(String key) {
		return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(key));
	}

}
