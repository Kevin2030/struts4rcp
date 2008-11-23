package com.googlecode.struts4rcp.server.map;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Application属性Map封装
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ApplicationMap extends EnumerationMap<String, Serializable> {

	private static final long serialVersionUID = 1L;

	private final ServletContext context;

	public ApplicationMap(ServletContext context) {
		if (context == null)
			throw new NullPointerException("ServletContext == null");
		this.context = context;
	}

	public ApplicationMap(HttpServletRequest request) {
		if (request == null)
			throw new NullPointerException("HttpServletRequest == null");
		this.context = request.getSession().getServletContext();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Enumeration<String> getKeys() {
		return context.getAttributeNames();
	}

	@Override
	protected Serializable getValue(String key) {
		return (Serializable)context.getAttribute(key);
	}

	@Override
	protected void putValue(String key, Serializable value) {
		context.setAttribute(key, value);
	}

	@Override
	protected void removeValue(String key) {
		context.removeAttribute(key);
	}

}
