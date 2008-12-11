package com.googlecode.struts4rcp.internal;

import java.io.Serializable;

public class ResourceResponse<R extends Serializable> implements Serializable {

	private static final long serialVersionUID = 4861275658768285483L;

	private final String uri;

	private final R resource;

	public ResourceResponse(String uri, R resource) {
		this.uri = uri;
		this.resource = resource;
	}

	public String getUri() {
		return uri;
	}

	public R getResource() {
		return resource;
	}

}
