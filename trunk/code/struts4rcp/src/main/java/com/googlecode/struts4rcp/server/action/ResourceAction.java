package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.server.ActionContext;

/**
 * 资源Action基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public abstract class ResourceAction<R extends Serializable> extends AbstractAction<R, Serializable> {

	public R execute(R model) throws Exception {
		String method = ActionContext.getContext().getRequest().getMethod();
		if ("post".equalsIgnoreCase(method)) {
			create(model);
			return null;
		} else if ("put".equalsIgnoreCase(method)) {
			update(model);
			return null;
		} else if ("delete".equalsIgnoreCase(method)) {
			//delete(model);
			return null;
		} else if ("get".equalsIgnoreCase(method)) {
			//String uri = ActionContext.getContext().getURI();
			//if (uri.equalsIgnoreCase(getDirectory()))
			//	return index(model);
			//else
			//	return read(model);
			return null;
		} else {
			throw new UnsupportedOperationException("Unsupported http request method \"" + method + "\"!");
		}
	}

	protected long count() throws Exception {
		return count(null);
	}

	protected long count(R condition) throws Exception {
		throw new UnsupportedOperationException();
	}

	protected R[] index() throws Exception {
		return index(null, 0, Integer.MAX_VALUE);
	}

	protected R[] index(long start, long limit) throws Exception {
		return index(null, start, start);
	}

	protected R[] index(R condition) throws Exception {
		return index(condition, 0, Integer.MAX_VALUE);
	}

	protected R[] index(R condition, long start, long limit) throws Exception {
		throw new UnsupportedOperationException();
	}

	protected R create(R resource) throws Exception {
		throw new UnsupportedOperationException();
	}

	protected R read(R resource) throws Exception {
		throw new UnsupportedOperationException();
	}

	protected void update(R resource) throws Exception {
		throw new UnsupportedOperationException();
	}

	protected void delete(R resource) throws Exception {
		throw new UnsupportedOperationException();
	}

}
