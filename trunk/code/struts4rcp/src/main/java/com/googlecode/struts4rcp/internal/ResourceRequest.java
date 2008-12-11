package com.googlecode.struts4rcp.internal;

import java.io.Serializable;

/**
 * 资源请求信息
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
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

	/**
	 * 资源
	 * @return 资源
	 */
	public R getResource() {
		return resource;
	}

	/**
	 * 起始行
	 * @return 起始行
	 */
	public int getStart() {
		return start;
	}

	/**
	 * 个数
	 * @return 个数
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * 是否为引用
	 * @return 是否为引用
	 */
	public boolean isReference() {
		return reference;
	}

}
