package com.googlecode.struts4rcp.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * Action工厂上下文
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ActionServletContext {

	private static ActionServletContext CONTEXT;

	/**
	 * 初始化上下文
	 * @param servletContext 应用服务上下文
	 * @param servletConfig 应用服务配置
	 */
	public static void init(ServletContext servletContext, ServletConfig servletConfig) {
		CONTEXT = new ActionServletContext(servletContext, servletConfig);
	}

	/**
	 * 销毁上下文
	 */
	public static void destroy() {
		if (CONTEXT != null) {
			try {
				if (CONTEXT.actionProvider != null) {
					CONTEXT.actionProvider.shutdown();
					CONTEXT.actionProvider = null;
				}
			} finally {
				if (CONTEXT.actionReceiver != null) {
					CONTEXT.actionReceiver.shutdown();
					CONTEXT.actionReceiver = null;
				}
			}
			CONTEXT = null;
		}
	}

	/**
	 * 获取当前服务器上下文
	 * @return 当前服务器上下文
	 */
	public static ActionServletContext getContext() {
		return CONTEXT;
	}

	private final ServletContext servletContext;

	private final ServletConfig servletConfig;

	private ActionServletContext(ServletContext servletContext, ServletConfig servletConfig) {
		this.servletContext = servletContext;
		this.servletConfig = servletConfig;
	}

	/**
	 * 获取应用服务上下文
	 * @return 应用服务上下文
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * 获取应用服务配置
	 * @return 应用服务配置
	 */
	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	// Action工厂
	private ActionProvider actionProvider;

	/**
	 * 设置Action工厂
	 * @param actionProvider Action工厂
	 */
	public void setActionProvider(ActionProvider actionProvider) {
		if (actionProvider == null)
			throw new NullPointerException("ActionProvider == null!");
		this.actionProvider = actionProvider;
	}

	/**
	 * 获取Action工厂
	 * @return Action工厂
	 */
	public ActionProvider getActionProvider() {
		return actionProvider;
	}

	private ActionReceiver actionReceiver;

	public ActionReceiver getActionReceiver() {
		return actionReceiver;
	}

	public void setActionReceiver(ActionReceiver actionReceiver) {
		this.actionReceiver = actionReceiver;
	}

}
