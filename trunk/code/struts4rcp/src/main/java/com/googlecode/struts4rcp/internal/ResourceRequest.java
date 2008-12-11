package com.googlecode.struts4rcp.internal;

import java.io.Serializable;

public class ResourceRequest<R extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int LIMITLESS = -1;

	private final R resource;

	private final int start;

	private final int limit;

	private final boolean reference;

	public ResourceRequest(boolean reference) {
		super();
		this.resource = null;
		this.start = 0;
		this.limit = LIMITLESS;
		this.reference = reference;
	}

	public ResourceRequest(R resource, boolean reference) {
		super();
		this.resource = resource;
		this.start = 0;
		this.limit = LIMITLESS;
		this.reference = reference;
	}

	public ResourceRequest(R resource, int start, int limit, boolean reference) {
		super();
		this.resource = resource;
		this.start = start;
		this.limit = limit;
		this.reference = reference;
	}

	public R getResource() {
		return resource;
	}

	public int getStart() {
		return start;
	}

	public int getLimit() {
		return limit;
	}

	public boolean isReference() {
		return reference;
	}

}
