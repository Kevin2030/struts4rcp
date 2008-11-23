package com.googlecode.struts4rcp.server.map;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求头信息Map封装
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class HeaderMap extends EnumerationMap<String, String> {

	private static final long serialVersionUID = 1L;

	private final HttpServletRequest request;

	public HeaderMap(HttpServletRequest request) {
		if (request == null)
			throw new NullPointerException("HttpServletRequest == null");
		this.request = request;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Enumeration<String> getKeys() {
		return request.getHeaderNames();
	}

	@Override
	protected String getValue(String key) {
		return request.getHeader(String.valueOf(key));
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
