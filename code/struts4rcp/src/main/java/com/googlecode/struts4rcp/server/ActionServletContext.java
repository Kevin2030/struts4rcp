package com.googlecode.struts4rcp.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * Action工厂上下文
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ActionServletContext {

	// 上下文线程容器
	private static ThreadLocal<ActionServletContext> local = new ThreadLocal<ActionServletContext>();

	/**
	 * 初始化上下文
	 * @param actionServletContext 应用服务上下文
	 */
	public static void init(ActionServletContext actionServletContext) {
		local.set(actionServletContext);
	}

	/**
	 * 销毁上下文
	 */
	public static void destroy() {
		local.remove();
	}

	/**
	 * 获取当前服务器上下文
	 * @return 当前服务器上下文
	 */
	public static ActionServletContext getContext() {
		return local.get();
	}

	private final ServletContext servletContext;

	private final ServletConfig servletConfig;

	public ActionServletContext(ServletContext servletContext, ServletConfig servletConfig) {
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

	private ActionMapper actionMapper;

	public ActionMapper getActionMapper() {
		return actionMapper;
	}

	public void setActionMapper(ActionMapper actionMapper) {
		if (actionMapper == null)
			throw new NullPointerException("ActionMapper == null!");
		this.actionMapper = actionMapper;
	}

}
