package com.googlecode.struts4rcp.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import com.googlecode.struts4rcp.client.event.TransmissionListener;

/**
 * 传输器策略接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Transmitter extends ConnectionMonitor {

	public static final String POST_METHOD = "post";

	public static final String PUT_METHOD = "put";

	public static final String GET_METHOD = "get";

	public static final String DELETE_METHOD = "delete";

	public static final String HEAD_METHOD = "head";

	/**
	 * 传输对象
	 * @param uri 服务器端URI
	 * @param model 传输数据
	 * @return 传回对象
	 * @throws IOException 传输出错时抛出
	 */
	public abstract Serializable transmit(String uri, Serializable model) throws Exception;

	/**
	 * 传输对象
	 * @param method 传输方法
	 * @param uri 服务器端URI
	 * @param model 传输数据
	 * @return 传回对象
	 * @throws IOException 传输出错时抛出
	 */
	public abstract Serializable transmit(String method, String uri, Serializable model) throws Exception;

	/**
	 * 传输对象
	 * @param method 传输方法
	 * @param uri 服务器端URI
	 * @param model 传输数据
	 * @return 传回对象
	 * @throws IOException 传输出错时抛出
	 */
	public abstract Serializable transmit(String method, String uri, Serializable model, Map<String, String> headers) throws Exception;

	/**
	 * 获取正在前台运行的传输项列表
	 * @return 正在运行的传输项列表
	 */
	public abstract Collection<Transmission> getTransmissions();

	/**
	 * 是否正在前台传输
	 * @return 是否正在前台传输
	 */
	public abstract boolean isTransmiting();

	/**
	 * 添加传输事件监听器
	 * @param listener 传输事件监听器
	 */
	public abstract void addTransmissionListener(TransmissionListener listener);

	/**
	 * 移除传输事件监听器
	 * @param listener 传输事件监听器
	 */
	public abstract void removeTransmissionListener(TransmissionListener listener);

}
