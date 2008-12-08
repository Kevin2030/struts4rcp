package com.googlecode.struts4rcp.client.event;

/**
 * 传输事件发布器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransmissionPublisher extends Publisher<TransmissionListener, TransmissionEvent> {

	@Override
	protected void doEvent(TransmissionListener listener, TransmissionEvent event) {
		if (event.getExecution().isTransported())
			listener.onTransported(event);
		else
			listener.onTransporting(event);
	}

}