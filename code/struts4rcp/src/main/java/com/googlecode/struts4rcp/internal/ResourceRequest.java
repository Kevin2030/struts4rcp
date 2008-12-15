package com.googlecode.struts4rcp.internal;

import java.io.Serializable;

/**
 * 资源请求信息
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public class ResourceRequest<R extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 不跳过
	 */
	public static final int NOSKIP = 0;

	/**
	 * 不限制个数
	 */
	public static final int LIMITLESS = 0;

	private final R resource;

	private final long skip;

	private final long limit;

	private final boolean lazy;

	public ResourceRequest(R resource, boolean lazy) {
		this(resource, NOSKIP, LIMITLESS, lazy);
	}

	public ResourceRequest(R resource, long skip, long limit, boolean lazy) {
		super();
		if (skip < NOSKIP)
			throw new IllegalArgumentException("skip < " + NOSKIP);
		if (limit < LIMITLESS)
			throw new IllegalArgumentException("limit < " + LIMITLESS);
		this.resource = resource;
		this.skip = skip;
		this.limit = limit;
		this.lazy = lazy;
	}

	/**
	 * 资源
	 * @return 资源
	 */
	public R getResource() {
		return resource;
	}

	/**
	 * 起始行，从0开始
	 * @return 起始行
	 */
	public long getSkip() {
		return skip;
	}

	/**
	 * 个数
	 * @return 个数
	 */
	public long getLimit() {
		return limit;
	}

	/**
	 * 是否为引用
	 * @return 是否为引用
	 */
	public boolean isLazy() {
		return lazy;
	}

}
