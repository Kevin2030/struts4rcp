package com.googlecode.struts4rcp.util.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4jLoggerProvider implements LoggerProvider {

	public void setReloadableConfig(String config) {
		if (config != null && config.length() > 0) {
			if (config.endsWith(".xml"))
				DOMConfigurator.configureAndWatch(config);
			else
				PropertyConfigurator.configureAndWatch(config);
		}
	}

	public void setConfig(String config) {
		if (config != null && config.length() > 0) {
			if (config.endsWith(".xml"))
				DOMConfigurator.configure(config);
			else
				PropertyConfigurator.configure(config);
		}
	}

	public Logger getLogger(String key) {
		return new Log4jLogger(LogManager.getLogger(key));
	}

}
