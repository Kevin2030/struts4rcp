package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.ActionContext;
import com.googlecode.struts4rcp.server.ServletSerializer;
import com.googlecode.struts4rcp.server.action.PageAction;
import com.googlecode.struts4rcp.server.action.Path;
import com.googlecode.struts4rcp.server.action.PathAction;

public class PathServletSerializer implements ServletSerializer {

	private ServletSerializer serializer;

	public PathServletSerializer(ServletSerializer serializer) {
		super();
		if (serializer == null)
			throw new NullPointerException("Serializer == null!");
		this.serializer = serializer;
	}

	public Serializable deserialize(Class<? extends Serializable> baseClass, HttpServletRequest request)
			throws IOException {
		Serializable result = serializer.deserialize(Serializable.class, request);
		String path;
		Action<Serializable, Serializable> action = ActionContext.getContext().getAction();
		if (action instanceof PageAction) {
			PathAction<Serializable, Serializable> pathAction = (PathAction<Serializable, Serializable>)action;
			path = pathAction.getPath();
		} else {
			path = getPath(action);
		}
		return deserializePath(request, result, path);
	}

	/**
	 * Action缺省页面名查找方式
	 * @param action Action实例
	 * @return 页面路径(不包含后缀)
	 */
	public static String getPath(Action<Serializable, Serializable> action) {
		if (action.getClass().isAnnotationPresent(Path.class))
			return action.getClass().getAnnotation(Path.class).value();
		return null;
	}

	protected Serializable deserializePath(HttpServletRequest request, Serializable result, String path) {
		if (path == null || path.length() == 0)
			return result;
		return result;
	}

	public void serialize(Serializable result, HttpServletResponse response)
			throws IOException {
		serializer.serialize(result, response);
	}

	public String getContentType() {
		return serializer.getContentType();
	}

}