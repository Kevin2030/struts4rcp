package com.googlecode.struts4rcp.client.event;

public class ConfigurationPublisher extends Publisher<ConfigurationListener, ConfigurationEvent> {

	@Override
	protected void doEvent(ConfigurationListener listener, ConfigurationEvent event) {
		listener.onChanged(event);
	}

}
