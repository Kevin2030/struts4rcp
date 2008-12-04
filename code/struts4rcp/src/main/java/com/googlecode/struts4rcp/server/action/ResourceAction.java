package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.ActionContext;
import com.googlecode.struts4rcp.server.serializer.PathSerializer;

/**
 * 资源Action基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public abstract class ResourceAction<R extends Serializable> extends AbstractAction<R, R> implements PathAction<R, R> {

	private String path;

	public void setPath(String path) {
		if (path != null)
			path = path.trim();
		this.path = path;
	}

	@SuppressWarnings("unchecked")
	public String getPath() {
		if (path != null && path.length() > 0)
			return path;
		return PathSerializer.getPath((Action<Serializable, Serializable>)this);
	}

	public R execute(R model) throws Exception {
		String method = ActionContext.getContext().getRequest().getMethod();
		if ("post".equalsIgnoreCase(method)) {
			create(model);
			return null;
		} else if ("put".equalsIgnoreCase(method)) {
			update(model);
			return null;
		} else if ("delete".equalsIgnoreCase(method)) {
			remove(model);
			return null;
		} else if ("get".equalsIgnoreCase(method)) {
			return get(model);
		} else {
			throw new UnsupportedOperationException("Unsupported http request method \"" + method + "\"!");
		}
	}

	public void create(R resource) throws Exception {}

	public void update(R resource) throws Exception {}

	public void remove(R resource) throws Exception {}

	public R get(R resource) throws Exception {
		return resource;
	}

}
