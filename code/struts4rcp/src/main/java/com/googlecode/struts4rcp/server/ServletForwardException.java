package com.googlecode.struts4rcp.server;

import java.io.Serializable;

/**
 * Servlet跳转异常信息
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ServletForwardException extends RuntimeException {

	private static final long serialVersionUID = -5359206733444184632L;

	// 转向Servlet的URI
	private final String uri;

	// 转向传递数据模型
	private final Serializable model;

	/**
	 * 构建转向异常
	 * @param actionName 转向Action名称，不能为空
	 * @param model 转向传递数据模型
	 */
	public ServletForwardException(String uri, Serializable model) {
		if (uri == null)
			throw new NullPointerException("ServletForwardException: uri == null!");
		this.uri = uri;
		this.model = model;
	}

	/**
	 * 获取转向uri
	 * @return 转向uri，不为空
	 */
	public String getURI() {
		return uri;
	}

	/**
	 * 获取转向传递数据模型
	 * @return 转向传递数据模型
	 */
	public Serializable getModel() {
		return model;
	}

	@Override
	public String toString() {
		return "forward to:" + uri;
	}

}