package com.googlecode.struts4rcp.client.event;

/**
 * 传输事件监听器空方法适配
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransmissionAdapter extends Adapter implements TransmissionListener {

	public void onTransmit(TransmissionEvent event) {}

	public void onTransmiting(TransmissionEvent event) {}

	public void onTransmited(TransmissionEvent event) {}

}
