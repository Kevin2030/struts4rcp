package com.googlecode.struts4rcp.server.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.serializer.PageServletSerializer;
import com.googlecode.struts4rcp.server.serializer.PathServletSerializer;
import com.googlecode.struts4rcp.util.ClassUtils;
import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;
import com.googlecode.struts4rcp.util.validator.Validator;

/**
 * 抽象Action基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <M> 传入模型类型
 * @param <R> 返回值类型
 */
public abstract class AbstractAction<M extends Serializable, R extends Serializable> implements PageAction<M, R>, PathAction<M, R>, ValidationAction<M, R> {

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
		String dir = getDirectory();
		if (dir == null)
			dir = "";
		if (path != null && path.length() > 0)
			return dir + path;
		return dir + PathServletSerializer.getPath((Action<Serializable, Serializable>)this);
	}

	private String directory;

	public void setDirectory(String directory) {
		if (directory != null)
			directory = directory.trim();
		this.directory = directory;
	}

	@SuppressWarnings("unchecked")
	public String getDirectory() {
		if (directory != null && directory.length() > 0)
			return directory;
		return PathServletSerializer.getDirectory((Action<Serializable, Serializable>)this);
	}

	private Validator validator;

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public Validator getValidator() {
		return validator;
	}

	@SuppressWarnings("unchecked")
	public Class<M> getModelClass() {
		try {
			return (Class<M>) ClassUtils.getMethod(getClass(), "execute").getParameterTypes()[0];
		} catch (Throwable e) {
			throw new RuntimeException(getClass().getName() + " generic type undefined!");
		}
	}

	@SuppressWarnings("unchecked")
	public Class<R> getReturnClass() {
		try {
			return (Class<R>) ClassUtils.getMethod(getClass(), "execute").getReturnType();
		} catch (Throwable e) {
			throw new RuntimeException(getClass().getName() + " generic type undefined!");
		}
	}
}
