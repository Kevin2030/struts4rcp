package com.googlecode.struts4rcp.server;

import java.io.Serializable;
import java.util.Map;
import java.util.Stack;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.map.ApplicationMap;
import com.googlecode.struts4rcp.server.map.CookieMap;
import com.googlecode.struts4rcp.server.map.HeaderMap;
import com.googlecode.struts4rcp.server.map.ParameterMap;
import com.googlecode.struts4rcp.server.map.RequestMap;
import com.googlecode.struts4rcp.server.map.SessionMap;

/**
 * Action上下文
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ActionContext {

	// 上下文线程容器
	private static ThreadLocal<ActionContext> local = new ThreadLocal<ActionContext>();

	/**
	 * 初始化Action上下文
	 * @param request HTTP请求信息
	 * @param response HTTP响应信息
	 */
	public static void init(HttpServletRequest request, HttpServletResponse response, String actionName, Action<Serializable, Serializable> action) {
		local.set(new ActionContext(request, response, actionName, action));
	}

	/**
	 * 销毁当前线程中的上下文信息
	 */
	public static void destroy() {
		ActionContext actionContext = getContext();
		if (actionContext != null)
			actionContext.actionStack.clear();
		local.remove();
	}

	/**
	 * 获取当前Action上下文
	 * @return 当前Action上下文
	 */
	public static ActionContext getContext() {
		return (ActionContext)local.get();
	}

	// 请求信息
	private final HttpServletRequest request;

	// 响应信息
	private final HttpServletResponse response;

	// Action名称
	private final String requestActionName;

	// Action实例
	private final Action<Serializable, Serializable> requestAction;

	// Action栈
	private final Stack<ActionInfo> actionStack = new Stack<ActionInfo>();

	private static final class ActionInfo {

		// Action名称
		private final String actionName;

		// Action实例
		private final Action<Serializable, Serializable> action;

		public ActionInfo(String actionName,
				Action<Serializable, Serializable> action) {
			this.actionName = actionName;
			this.action = action;
		}

		public String getActionName() {
			return actionName;
		}

		public Action<Serializable, Serializable> getAction() {
			return action;
		}

	}

	private ActionContext(HttpServletRequest request, HttpServletResponse response, String actionName, Action<Serializable, Serializable> action) {
		this.request = request;
		this.response = response;
		this.requestActionName = actionName;
		this.requestAction = action;
		pushAction(requestActionName, requestAction);
	}

	/**
	 * 获取请求Action的注册名称
	 * @return Action的注册名称
	 */
	public String getRequestActionName() {
		return requestActionName;
	}

	/**
	 * 获取请求Action实例
	 * @return Action实例
	 */
	public Action<Serializable, Serializable> getRequestAction() {
		return requestAction;
	}

	/**
	 * 注册当前Action的注册名称
	 * @param actionName Action的注册名称
	 *  @param action Action的注册实例
	 */
	public void pushAction(String actionName, Action<Serializable, Serializable> action) {
		actionStack.push(new ActionInfo(actionName, action));
	}

	/**
	 * 弹出当前Action
	 */
	public void popAction() {
		actionStack.pop();
	}

	/**
	 * 获取当前Action的注册名称
	 * @return Action的注册名称
	 */
	public String getActionName() {
		ActionInfo info = actionStack.peek();
		if (info == null)
			return null;
		return info.getActionName();
	}

	/**
	 * 获取当前Action实例
	 * @return Action实例
	 */
	public Action<Serializable, Serializable> getAction() {
		ActionInfo info = actionStack.peek();
		if (info == null)
			return null;
		return info.getAction();
	}

	/**
	 * 获取HTTP请求信息
	 * @return HTTP请求信息
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * 获取HTTP响应信息
	 * @return HTTP响应信息
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * 获取HTTP会话信息
	 * @return HTTP会话信息
	 */
	public HttpSession getSession() {
		return request == null ? null : request.getSession();
	}

	/**
	 * 获取应用上下文
	 * @return 应用上下文
	 */
	public ServletContext getServletContext() {
		HttpSession session = getSession();
		return session == null ? null : session.getServletContext();
	}

	/**
	 * 获取HTTP请求参数
	 * @return HTTP请求参数
	 */
	public Map<String, String> getParameterMap() {
		return new ParameterMap(request);
	}

	/**
	 * 获取HTTP请求头信息
	 * @return HTTP请求头信息
	 */
	public Map<String, String> getHeaderMap() {
		return new HeaderMap(request);
	}

	/**
	 * 获取HTTP请求Cookie
	 * @return HTTP请求Cookie
	 */
	public Map<String, String> getCookieMap() {
		return new CookieMap(request, response);
	}

	/**
	 * 获取HTTP请求属性
	 * @return HTTP请求属性
	 */
	public Map<String, ? extends Serializable> getRequestMap() {
		return new RequestMap(request);
	}

	/**
	 * 获取HTTP会话属性
	 * @return HTTP会话属性
	 */
	public Map<String, ? extends Serializable> getSessionMap() {
		return new SessionMap(request);
	}

	/**
	 * 获取HTTP上下文属性
	 * @return HTTP上下文属性
	 */
	public Map<String, ? extends Serializable> getApplicationMap() {
		return new ApplicationMap(request);
	}

}
