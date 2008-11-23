package com.googlecode.struts4rcp.client.event;

public class ConfigurationPublisher extends Publisher<ConfigurationListener, ConfigurationEvent> {

	@Override
	protected void doEvent(ConfigurationListener listener, ConfigurationEvent event) {
		if (event.isRemoved())
			listener.onConfigurationRemoved(event);
		else
			if (event.getOldValue() == null)
				listener.onConfigurationAdded(event);
			else
				listener.onConfigurationChanged(event);
	}

}
