package com.googlecode.struts4rcp.server;

import java.io.Serializable;

public class ForwardException extends RuntimeException {

	private static final long serialVersionUID = -5359206733444184632L;

	// 转向目标
	private final String target;

	// 转向传递数据模型
	private final Serializable model;

	/**
	 * 构建转向异常
	 * @param target 转向目标，不能为空
	 * @param model 转向传递数据模型
	 */
	public ForwardException(String target, Serializable model) {
		if (target == null)
			throw new NullPointerException("ForwardException: target == null!");
		this.target = target;
		this.model = model;
	}

	/**
	 * 获取转向目标
	 * @return 转向目标
	 */
	public String getTarget() {
		return target;
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
		return "forward to:" + target;
	}

}
