package com.googlecode.struts4rcp.server.map;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie属性Map封装
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class CookieMap extends EnumerationMap<String, String> {

	private static final long serialVersionUID = 1L;

	private final HttpServletRequest request;

	private final HttpServletResponse response;

	public CookieMap(HttpServletRequest request, HttpServletResponse response) {
		if (request == null)
			throw new NullPointerException("HttpServletRequest == null");
		if (response == null)
			throw new NullPointerException("HttpServletResponse == null");
		this.request = request;
		this.response = response;
	}

	@Override
	protected Enumeration<String> getKeys() {
		Cookie[] cookies = request.getCookies();
		return new CookieEnumerator(cookies == null ? new Cookie[0] : cookies);
	}

	private static final class CookieEnumerator implements Enumeration<String> {

		private int i = 0;

		private final Cookie[] cookies;

		CookieEnumerator(Cookie[] cookies) {
			this.cookies = cookies;
		}

		public boolean hasMoreElements() {
			return cookies != null && cookies.length > i;
		}

		public String nextElement() {
			if (cookies == null)
				return null;
			Cookie cookie = cookies[i];
			i++;
			return cookie.getName();
		}
	}

	@Override
	protected String getValue(String key) {
		if (key == null)
			throw new NullPointerException("key == null");
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (key.equals(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	@Override
	protected void putValue(String key, String value) {
		if (key == null)
			throw new NullPointerException("key == null");
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(30 * 24 * 60 * 60); // one month
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	@Override
	protected void removeValue(String key) {
		if (key == null)
			throw new NullPointerException("key == null");
		Cookie cookie = new Cookie(key, "");
		cookie.setMaxAge(-1); // expiry
		response.addCookie(cookie);
	}

}
