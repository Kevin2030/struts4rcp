package com.googlecode.struts4rcp.client.event;

/**
 * 传输事件监听器空方法适配
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransferAdapter extends Adapter implements TransferListener {

	public void onTransfer(TransferEvent event) {}

	public void onTransferring(TransferEvent event) {}

	public void onTransferred(TransferEvent event) {}

}
