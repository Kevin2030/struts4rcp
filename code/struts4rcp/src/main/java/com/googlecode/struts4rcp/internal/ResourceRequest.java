package com.googlecode.struts4rcp.internal;

import java.io.Serializable;

public class ResourceRequest<R extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int LIMITLESS = 0;

	private final R resource;

	private final int start;

	private final int limit;

	private final boolean reference;

	public ResourceRequest(boolean reference) {
		this(null, 0, LIMITLESS, reference);
	}

	public ResourceRequest(R resource, boolean reference) {
		this(resource, 0, LIMITLESS, reference);
	}

	public ResourceRequest(R resource, int start, int limit, boolean reference) {
		super();
		if (start < 0)
			throw new IllegalArgumentException("start < 0");
		if (limit < 0)
			throw new IllegalArgumentException("limit < 0");
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
