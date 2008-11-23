package com.googlecode.struts4rcp.client.event;

/**
 * 传输事件发布器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransportationPublisher extends Publisher<TransportationListener, TransportationEvent> {

	@Override
	protected void doEvent(TransportationListener listener, TransportationEvent event) {
		if (event.getExecution().isTransported())
			listener.onTransported(event);
		else
			listener.onTransporting(event);
	}

}