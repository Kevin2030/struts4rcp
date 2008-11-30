package com.googlecode.struts4rcp.server.interceptor;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.ActionContext;
import com.googlecode.struts4rcp.server.ActionInterceptor;
import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;

public class ProfileInterceptor implements ActionInterceptor {

	/**
	 * 日志输出接口
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public Serializable intercept(Action<Serializable, Serializable> action,
			Serializable model) throws Exception {
		long start = System.nanoTime();
		try {
			return action.execute(model);
		} finally {
			logger.info(format(ActionContext.getContext().getActionName(), System.nanoTime() - start));
		}
	}

	protected String format(String actionName, long nanoTime) {
		return "[Profile] " + actionName + " execute time: " + new DecimalFormat("###,###0.###").format(nanoTime) + " ns.";
	}

}
