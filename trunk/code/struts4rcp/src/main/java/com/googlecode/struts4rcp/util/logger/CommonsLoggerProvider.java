package com.googlecode.struts4rcp.util.logger;

import org.apache.commons.logging.LogFactory;

/**
 * CommonsLogging适配供给策略
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class CommonsLoggerProvider implements LoggerProvider {

	public Logger getLogger(String key) {
		return new CommonsLogger(LogFactory.getLog(key));
	}

}
