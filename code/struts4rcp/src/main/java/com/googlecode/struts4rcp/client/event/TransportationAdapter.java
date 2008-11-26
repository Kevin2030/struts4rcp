package com.googlecode.struts4rcp.client.event;

/**
 * 传输事件监听器空方法适配
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransportationAdapter extends Adapter implements TransportationListener {

	public void onTransporting(TransportationEvent event) {}

	public void onTransported(TransportationEvent event) {}

}
