package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.server.ActionContext;

/**
 * RESTful请求Action
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <M> RESTful请求数据模型类型
 */
public abstract class RestfulAction<M extends Serializable> extends DispatchAction<M> {

	protected String getMethodName() {
		return ActionContext.getContext().getRequest().getMethod();
	}

	public Serializable post(M model) throws Exception {
		return model;
	}

	public Serializable put(M model) throws Exception {
		return model;
	}

	public Serializable get(M model) throws Exception {
		return model;
	}

	public Serializable delete(M model) throws Exception {
		return model;
	}

	public Serializable trace(M model) throws Exception {
		return model;
	}

	public Serializable options(M model) throws Exception {
		return model;
	}

	public Serializable head(M model) throws Exception {
		return model;
	}

}
