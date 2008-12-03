package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.googlecode.struts4rcp.server.ActionContext;
import com.googlecode.struts4rcp.util.ClassUtils;

/**
 * RESTful请求Action
 * TODO 未实现RESTful风格的URL数据转换
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <M> RESTful请求数据模型类型
 */
public abstract class RestfulAction<M extends Serializable> extends DispatchAction<M> implements PathAction<M, Serializable> {

	protected String getMethodName() {
		return ActionContext.getContext().getRequest().getMethod();
	}

	private String path;

	public void setPath(String path) {
		if (path != null)
			path = path.trim();
		this.path = path;
	}

	public String getPath() {
		try {
			String methodName = getMethodName();
			Method method = ClassUtils.getMethod(getClass(), methodName);
			if (method.isAnnotationPresent(Path.class))
				return method.getAnnotation(Path.class).value();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		if (getClass().isAnnotationPresent(Path.class))
			return getClass().getAnnotation(Path.class).value();
		return path;
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
