package com.googlecode.struts4rcp.server.map;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Session属性Map封装
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class SessionMap extends EnumerationMap<String, Serializable> {

	private static final long serialVersionUID = 1L;

	private final HttpSession session;

	public SessionMap(HttpSession session) {
		if (session == null)
			throw new NullPointerException("HttpSession == null");
		this.session = session;
	}

	public SessionMap(HttpServletRequest request) {
		if (request == null)
			throw new NullPointerException("HttpServletRequest == null");
		this.session = request.getSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Enumeration<String> getKeys() {
		return session.getAttributeNames();
	}

	protected Serializable getValue(String key) {
		return (Serializable)session.getAttribute(String.valueOf(key));
	}

	@Override
	protected void putValue(String key, Serializable value) {
		session.setAttribute(key, value);
	}

	@Override
	protected void removeValue(String key) {
		session.removeAttribute(key);
	}

}
