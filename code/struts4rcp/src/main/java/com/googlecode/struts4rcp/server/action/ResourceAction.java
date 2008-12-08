package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.server.ActionContext;

/**
 * 资源Action基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public abstract class ResourceAction<R extends Serializable> extends AbstractAction<R, Serializable> {

	public Serializable execute(R model) throws Exception {
		String uri = ActionContext.getContext().getURI();
		String method = ActionContext.getContext().getRequest().getMethod();
		if ("post".equalsIgnoreCase(method)) {
			create(model);
			return null;
		} else if ("put".equalsIgnoreCase(method)) {
			update(model);
			return null;
		} else if ("delete".equalsIgnoreCase(method)) {
			delete(model);
			return null;
		} else if ("get".equalsIgnoreCase(method)) {
			if (uri.equalsIgnoreCase(getDirectory()))
				return index(model);
			else
				return read(model);
		} else if ("head".equalsIgnoreCase(method)) {
			return getDirectory();
		} else {
			throw new UnsupportedOperationException("Unsupported http request method \"" + method + "\"!");
		}
	}

	protected void create(R resource) throws Exception {
		throw new UnsupportedOperationException();
	}

	protected void update(R resource) throws Exception {
		throw new UnsupportedOperationException();
	}

	protected void delete(R resource) throws Exception {
		throw new UnsupportedOperationException();
	}

	protected R read(R resource) throws Exception {
		return null;
	}

	protected R[] index(R resource) throws Exception {
		return null;
	}

}
