package com.googlecode.struts4rcp.internal;

import java.io.Serializable;

/**
 * 资源响应信息封装
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public class ResourceResponse<R extends Serializable> implements Serializable {

	private static final long serialVersionUID = 4861275658768285483L;

	private final String uri;

	private final R resource;

	public ResourceResponse(String uri, R resource) {
		if (uri == null)
			throw new IllegalArgumentException("uri == null!");
		this.uri = uri;
		this.resource = resource;
	}

	/**
	 * 资源URI
	 * @return 资源URI
	 */
	public String getURI() {
		return uri;
	}

	/**
	 * 资源
	 * @return 资源
	 */
	public R getResource() {
		return resource;
	}

}
