package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.server.ActionContext;

/**
 * 资源Action基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public abstract class ResourceAction<R extends Serializable> extends AbstractAction<R, Serializable> {

	public R execute(R model) throws Exception {
		String method = ActionContext.getContext().getRequest().getMethod();
		if ("post".equalsIgnoreCase(method)) {
			create(model, false);
			return null;
		} else if ("put".equalsIgnoreCase(method)) {
			update(model);
			return null;
		} else if ("delete".equalsIgnoreCase(method)) {
			//delete(model);
			return null;
		} else if ("get".equalsIgnoreCase(method)) {
			//String uri = ActionContext.getContext().getURI();
			//if (uri.equalsIgnoreCase(getDirectory()))
			//	return index(model);
			//else
			//	return read(model);
			return null;
		} else {
			throw new UnsupportedOperationException("Unsupported http request method \"" + method + "\"!");
		}
	}

	/**
	 * 统计资源个数
	 * @return 资源个数
	 * @throws Exception 统计失败时抛出
	 */
	protected long count() throws Exception {
		return count(null);
	}

	/**
	 * 统计过滤资源个数
	 * @param condition 过滤条件
	 * @return 资源个数
	 * @throws Exception 统计失败时抛出
	 */
	protected long count(R condition) throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * 获取资源列表
	 * @param isReference 是否只引用标识，如果是则返回资源标识列表，否则返回完整的资源列表
	 * @return 资源标识(ID)列表/资源列表
	 * @throws Exception 获取失败时抛出
	 */
	protected R[] index(boolean isReference) throws Exception {
		return index(null, 0, Integer.MAX_VALUE, isReference);
	}

	/**
	 * 获取资源列表
	 * @param start 起始
	 * @param limit 个数
	 * @param isReference 是否只引用标识，如果是则返回资源标识列表，否则返回完整的资源列表
	 * @return 资源标识(ID)列表/资源列表
	 * @throws Exception 获取失败时抛出
	 */
	protected R[] index(long start, long limit, boolean isReference) throws Exception {
		return index(null, start, start, isReference);
	}

	/**
	 * 获取资源列表
	 * @param condition 过滤条件
	 * @param isReference 是否只引用标识，如果是则返回资源标识列表，否则返回完整的资源列表
	 * @return 资源标识(ID)列表/资源列表
	 * @throws Exception 获取失败时抛出
	 */
	protected R[] index(R condition, boolean isReference) throws Exception {
		return index(condition, 0, Integer.MAX_VALUE, isReference);
	}

	/**
	 * 获取资源列表
	 * @param condition 过滤条件
	 * @param start 起始
	 * @param limit 个数
	 * @param isReference 是否只引用标识，如果是则返回资源标识列表，否则返回完整的资源列表
	 * @return 资源标识(ID)列表/资源列表
	 * @throws Exception 获取失败时抛出
	 */
	protected R[] index(R condition, long start, long limit, boolean isReference) throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * 创建资源
	 * @param resource 资源
	 * @param isReference 是否只引用标识，如果是则返回资源标识，否则返回完整的资源
	 * @return 资源标识(ID)/资源
	 * @throws Exception
	 */
	protected R create(R resource, boolean isReference) throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * 读取资源
	 * @param resource 资源标识(ID)
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
	 * @param resource 资源标识(ID)
	 * @throws Exception 删除失败时抛出
	 */
	protected void delete(R resource) throws Exception {
		throw new UnsupportedOperationException();
	}

}
