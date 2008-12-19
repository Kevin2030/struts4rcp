package com.googlecode.struts4rcp.client.event;

/**
 * 传输事件发布器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransferPublisher extends Publisher<TransferListener, TransferEvent> {

	@Override
	protected void doEvent(TransferListener listener, TransferEvent event) {
		if (event.getTransfer().isTransferred())
			listener.onTransferred(event);
		else if (event.getTransfer().isTransferring())
			listener.onTransferring(event);
		else
			listener.onTransfer(event);
	}

}