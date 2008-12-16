package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.server.ActionContext;
import com.googlecode.struts4rcp.util.KeyValue;

/**
 * 资源处理Action基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <R> 资源类型
 */
public abstract class ResourceAction<R extends Serializable> extends AbstractAction<R, Serializable> {

	/**
	 * 不跳过
	 */
	protected static final int NOSKIP = 0;

	/**
	 * 不限制个数
	 */
	protected static final int LIMITLESS = 0;

	public Serializable execute(R model) throws Exception {
		String method = ActionContext.getContext().getRequest().getMethod();
		String skipHeader = ActionContext.getContext().getRequest().getHeader("skip");
		long skip = NOSKIP;
		if (skipHeader != null && skipHeader.trim().length() > 0)
			skip = Long.parseLong(skipHeader.trim());
		String limitHeader = ActionContext.getContext().getRequest().getHeader("limit");
		long limit = LIMITLESS;
		if (limitHeader != null && limitHeader.trim().length() > 0)
			limit = Long.parseLong(limitHeader.trim());
		String lazyHeader = ActionContext.getContext().getRequest().getHeader("lazy");
		if (lazyHeader != null)
			lazyHeader = lazyHeader.trim();
		boolean lazy = "true".equalsIgnoreCase(lazyHeader)
						|| "1".equalsIgnoreCase(lazyHeader)
						|| "yes".equalsIgnoreCase(lazyHeader);
		if (isResources()) {
			if ("head".equalsIgnoreCase(method)) {
				if (model == null)
					return count();
				return count(model);
			} else if ("get".equalsIgnoreCase(method)) {
				R[] result;
				if (model == null) {
					if (skip == NOSKIP && limit == LIMITLESS) {
						result = list(lazy);
					} else {
						result = list(skip, limit, lazy);
					}
				} else {
					if (skip == NOSKIP && limit == LIMITLESS) {
						result = list(model, lazy);
					} else {
						result = list(model, skip, limit, lazy);
					}
				}
				return convertResources(result, lazy);
			} else if ("post".equalsIgnoreCase(method)) {
				R result = create(model, lazy);
				return convertResource(result, lazy);
			} else {
				throw new UnsupportedOperationException("Unsupported http request method \"" + method + "\"!");
			}
		} else {
			if ("put".equalsIgnoreCase(method)) {
				update(model);
				return null;
			} else if ("delete".equalsIgnoreCase(method)) {
				delete(model);
				return null;
			} else if ("get".equalsIgnoreCase(method)) {
				return read(model);
			} else {
				throw new UnsupportedOperationException("Unsupported http request method \"" + method + "\"!");
			}
		}
	}

	protected boolean isResources() { // TODO 分离对比目录
		return getPath().equalsIgnoreCase(ActionContext.getContext().getURI());
	}

	@SuppressWarnings("unchecked")
	private KeyValue<String, R>[] convertResources(R[] resources, boolean lazy) {
		if (resources == null)
			return null;
		KeyValue<String, R>[] responses = new KeyValue[resources.length];
		for (int i = 0, n = resources.length; i < n; i ++) {
			responses[i] = convertResource(resources[i], lazy);
		}
		return responses;
	}

	private KeyValue<String, R> convertResource(R resource, boolean lazy) {
		if (lazy)
			return new KeyValue<String, R>(convertResourceURI(resource), null);
		else
			return new KeyValue<String, R>(convertResourceURI(resource), resource);
	}

	private String convertResourceURI(R resource) {
		String uri = getPath();
		// TODO 格式化未处理
		return uri;
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
	 * 统计资源个数
	 * @param condition 过滤条件，为null表示统计所有资源
	 * @return 资源个数
	 * @throws Exception 统计失败时抛出
	 */
	protected long count(R condition) throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * 获取资源列表
	 * @param lazy 是否只引用标识，如果是则返回资源标识列表，否则返回完整的资源列表
	 * @return 资源标识列表/资源列表，注：资源标识指的是可以填充URI的非完整属性资源，如：只包含ID属性值的资源
	 * @throws Exception 获取失败时抛出
	 */
	protected R[] list(boolean lazy) throws Exception {
		return list(null, NOSKIP, LIMITLESS, lazy);
	}

	/**
	 * 获取资源列表
	 * @param skip 跳过个数
	 * @param limit 限制个数，如果为<code>LIMITLESS</code>，表示不限制
	 * @param lazy 是否只引用标识，如果是则返回资源标识列表，否则返回完整的资源列表
	 * @return 资源标识列表/资源列表，注：资源标识指的是可以填充URI的非完整属性资源，如：只包含ID属性值的资源
	 * @throws Exception 获取失败时抛出
	 */
	protected R[] list(long skip, long limit, boolean lazy) throws Exception {
		return list(null, skip, limit, lazy);
	}

	/**
	 * 获取资源列表
	 * @param condition 过滤条件，为null表示获取所有资源
	 * @param lazy 是否只引用标识，如果是则返回资源标识列表，否则返回完整的资源列表
	 * @return 资源标识列表/资源列表，注：资源标识指的是可以填充URI的非完整属性资源，如：只包含ID属性值的资源
	 * @throws Exception 获取失败时抛出
	 */
	protected R[] list(R condition, boolean lazy) throws Exception {
		return list(condition, NOSKIP, LIMITLESS, lazy);
	}

	/**
	 * 获取资源列表
	 * @param condition 过滤条件，为null表示获取所有资源
	 * @param skip 跳过个数
	 * @param limit 限制个数，如果为<code>LIMITLESS</code>，表示不限制
	 * @param lazy 是否只引用标识，如果是则返回资源标识列表，否则返回完整的资源列表
	 * @return 资源标识列表/资源列表，注：资源标识指的是可以填充URI的非完整属性资源，如：只包含ID属性值的资源
	 * @throws Exception 获取失败时抛出
	 */
	protected R[] list(R condition, long skip, long limit, boolean lazy) throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * 创建资源
	 * @param resource 资源
	 * @param lazy 是否只引用标识，如果是则返回资源标识，否则返回完整的资源
	 * @return 资源标识/资源，注：资源标识指的是可以填充URI的非完整属性资源，如：只包含ID属性值的资源
	 * @throws Exception
	 */
	protected R create(R resource, boolean lazy) throws Exception {
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
