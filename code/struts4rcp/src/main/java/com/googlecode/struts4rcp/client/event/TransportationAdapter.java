package com.googlecode.struts4rcp.client.event;

/**
 * 处理工作事件监听适配器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransportationAdapter extends Adapter implements TransportationListener {

	public void onTransporting(TransportationEvent event) {}

	public void onTransported(TransportationEvent event) {}

}
