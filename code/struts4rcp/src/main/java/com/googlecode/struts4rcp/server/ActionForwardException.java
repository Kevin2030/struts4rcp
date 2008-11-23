package com.googlecode.struts4rcp.server;

import java.io.Serializable;

/**
 * 请求转向异常
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ActionForwardException extends RuntimeException {

	private static final long serialVersionUID = -5359206733444184632L;

	// 转向Action名称
	private final String actionName;

	// 转向传递数据模型
	private final Serializable model;

	/**
	 * 构建转向异常
	 * @param actionName 转向Action名称，不能为空
	 * @param model 转向传递数据模型
	 */
	public ActionForwardException(String actionName, Serializable model) {
		if (actionName == null)
			throw new NullPointerException("ForwardException: actionName == null!");
		this.actionName = actionName;
		this.model = model;
	}

	/**
	 * 获取转向Action名称
	 * @return 转向Action名称，不为空
	 */
	public String getActionName() {
		return actionName;
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
		return "forward to:" + actionName;
	}

}
