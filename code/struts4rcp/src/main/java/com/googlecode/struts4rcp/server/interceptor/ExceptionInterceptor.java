package com.googlecode.struts4rcp.server.interceptor;

import java.io.Serializable;
import java.util.List;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.ActionInterceptor;
import com.googlecode.struts4rcp.util.ExceptionUtils;

/**
 * 异常转换拦截器(将客户端不识别的异常转换为RuntimeException)
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ExceptionInterceptor implements ActionInterceptor {

	protected List<String> exceptions;

	/**
	 * 注入待转换异常类型前缀集合
	 * @param exceptions 待转换异常类型前缀集合
	 */
	public void setExceptions(List<String> exceptions) {
		this.exceptions = exceptions;
	}

	public Serializable intercept(Action<Serializable, Serializable> action,
			Serializable model) throws Exception {
		try {
			return action.execute(model);
		} catch (Exception e) {
			if (exceptions != null && exceptions.size() > 0) {
				String name = e.getClass().getName();
				for (String exception: exceptions) {
					if (exception != null && name.startsWith(exception)) {
						throw convertException(e);
					}
				}
			}
			throw e;
		}
	}

	/**
	 * 转换客户端识别的异常，子类可覆盖此函数，进行替换
	 * @param e 服务器端异常
	 * @return 客户端识别的异常
	 */
	protected Exception convertException(Exception e) {
		return new RuntimeException(ExceptionUtils.getDetailMessage(e));
	}

}
