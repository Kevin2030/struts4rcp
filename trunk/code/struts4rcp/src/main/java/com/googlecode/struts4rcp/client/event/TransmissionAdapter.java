package com.googlecode.struts4rcp.client.event;

/**
 * 传输事件监听器空方法适配
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransmissionAdapter extends Adapter implements TransmissionListener {

	public void onTransporting(TransmissionEvent event) {}

	public void onTransported(TransmissionEvent event) {}

}
