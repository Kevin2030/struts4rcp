package com.googlecode.struts4rcp.client.event;

public class PropertyPublisher extends Publisher<PropertyListener, PropertyEvent> {

	@Override
	protected void doEvent(PropertyListener listener, PropertyEvent event) {
		listener.onPropertyChanged(event);
	}

}
