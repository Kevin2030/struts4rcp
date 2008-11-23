package com.googlecode.struts4rcp.server.map;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求参数信息Map封装
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ParameterMap extends EnumerationMap<String, String> {

	private static final long serialVersionUID = 1L;

	private final HttpServletRequest request;

	public ParameterMap(HttpServletRequest request) {
		if (request == null)
			throw new NullPointerException("HttpServletRequest == null");
		this.request = request;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Enumeration<String> getKeys() {
		return request.getParameterNames();
	}

	@Override
	protected String getValue(String key) {
		return request.getParameter(String.valueOf(key));
	}

	@Override
	protected void putValue(String key, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void removeValue(String key) {
		throw new UnsupportedOperationException();
	}

}
