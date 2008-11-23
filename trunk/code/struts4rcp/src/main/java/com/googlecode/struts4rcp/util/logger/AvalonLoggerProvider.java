package com.googlecode.struts4rcp.util.logger;

/**
 * Avalon供给策略
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class AvalonLoggerProvider implements LoggerProvider {

	public Logger getLogger(String key) {
		return new AvalonLogger(org.apache.log.Hierarchy.getDefaultHierarchy().getLoggerFor(key));
	}

}
