package com.googlecode.struts4rcp.server.map;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * Request属性Map封装
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class RequestMap extends EnumerationMap<String, Serializable> {

	private static final long serialVersionUID = 1L;

	private final HttpServletRequest request;

	public RequestMap(HttpServletRequest request) {
		if (request == null)
			throw new NullPointerException("HttpServletRequest == null");
		this.request = request;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Enumeration<String> getKeys() {
		return request.getAttributeNames();
	}

	@Override
	protected Serializable getValue(String key) {
		return (Serializable)request.getAttribute(String.valueOf(key));
	}

	@Override
	protected void putValue(String key, Serializable value) {
		request.setAttribute(key, value);
	}

	@Override
	protected void removeValue(String key) {
		request.removeAttribute(key);
	}
}
