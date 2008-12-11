package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.serializer.PageServletSerializer;
import com.googlecode.struts4rcp.server.serializer.PathServletSerializer;
import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;
import com.googlecode.struts4rcp.util.validator.Validator;

/**
 * 抽象Action基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <M> 传入模型类型
 * @param <R> 返回值类型
 */
public abstract class AbstractAction<M extends Serializable, R extends Serializable> implements Pageable, Pathable, Validatable {

	/**
	 * 日志输出接口
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private String page;

	public void setPage(String page) {
		if (page != null)
			page = page.trim();
		this.page = page;
	}

	@SuppressWarnings("unchecked")
	public String getPage() {
		if (page != null && page.length() > 0)
			return page;
		return PageServletSerializer.getPage((Action<Serializable, Serializable>)this);
	}

	private String path;

	public void setPath(String path) {
		if (path != null)
			path = path.trim();
		this.path = path;
	}

	@SuppressWarnings("unchecked")
	public String getPath() {
		if (path != null && path.length() > 0)
			return path;
		return PathServletSerializer.getPath((Action<Serializable, Serializable>)this);
	}

	private Validator validator;

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public Validator getValidator() {
		return validator;
	}

}
