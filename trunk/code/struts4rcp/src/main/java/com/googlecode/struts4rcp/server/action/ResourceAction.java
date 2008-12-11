package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.internal.ResourceRequest;
import com.googlecode.struts4rcp.server.ActionContext;

/**
 * 资源处理Action基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public abstract class ResourceAction<R extends Serializable> extends AbstractAction<Serializable, Serializable> {

	@SuppressWarnings("unchecked")
	public Serializable execute(Serializable model) throws Exception {
		String method = ActionContext.getContext().getRequest().getMethod();
		if ("post".equalsIgnoreCase(method)) {
			if (model instanceof ResourceRequest) {
				ResourceRequest<R> request = (ResourceRequest<R>)model;
				create(request.getResource(), request.isReference());
			} else {
				create((R)model, false);
			}
			return null;
		} else if ("put".equalsIgnoreCase(method)) {
			update((R)model);
			return null;
		} else if ("delete".equalsIgnoreCase(method)) {
			delete((R)model);
			return null;
		} else if ("get".equalsIgnoreCase(method)) {
			if (model instanceof ResourceRequest) {
				ResourceRequest<R> request = (ResourceRequest<R>)model;
				return index(request.getResource(), request.getStart(), request.getLimit(), request.isReference());
			} else {
				return read((R)model);
			}
		} else if ("head".equalsIgnoreCase(method)) {
			return count((R)model);
		} else {
			throw new UnsupportedOperationException("Unsupported http request method \"" + method + "\"!");
		}
	}

	/**
	 * 统计资源个数
	 * @param condition 过滤条件，为null表示统计所有资源
	 * @return 资源个数
	 * @throws Exception 统计失败时抛出
	 */
	protected long count(R condition) throws Exception {
		throw new UnsupportedOperationException();
	}

	protected static final int LIMITLESS = ResourceRequest.LIMITLESS;

	/**
	 * 获取资源列表
	 * @param condition 过滤条件，为null表示获取所有资源
	 * @param start 起始
	 * @param limit 个数，如果为<code>LIMITLESS</code>，表示不限制
	 * @param isReference 是否只引用标识，如果是则返回资源标识列表，否则返回完整的资源列表
	 * @return 资源标识列表/资源列表，注：资源标识指的是可以填充URI的非完整属性资源，如：只包含ID属性值的资源
	 * @throws Exception 获取失败时抛出
	 */
	protected R[] index(R condition, long start, long limit, boolean isReference) throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * 创建资源
	 * @param resource 资源
	 * @param isReference 是否只引用标识，如果是则返回资源标识，否则返回完整的资源
	 * @return 资源标识/资源，注：资源标识指的是可以填充URI的非完整属性资源，如：只包含ID属性值的资源
	 * @throws Exception
	 */
	protected R create(R resource, boolean isReference) throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * 读取资源
	 * @param resource 资源标识，注：资源标识指的是可以填充URI的非完整属性资源，如：只包含ID属性值的资源
	 * @return 资源
	 * @throws Exception 读取失败时抛出
	 */
	protected R read(R resource) throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * 更新资源
	 * @param resource 资源
	 * @throws Exception 更新失败时抛出
	 */
	protected void update(R resource) throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * 删除资源
	 * @param resource 资源标识，注：资源标识指的是可以填充URI的非完整属性资源，如：只包含ID属性值的资源
	 * @throws Exception 删除失败时抛出
	 */
	protected void delete(R resource) throws Exception {
		throw new UnsupportedOperationException();
	}

}
