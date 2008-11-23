package com.googlecode.struts4rcp.server.resolver;

/**
 * 方法分割决策器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ActionMethodResolver {

	public static final char DEFAULT_METHOD_SEPARATOR = '.';

	private static char methodSeparator = DEFAULT_METHOD_SEPARATOR;

	public static char getMethodSeparator() {
		return methodSeparator;
	}

	public static void setMethodSeparator(char methodSeparator) {
		ActionMethodResolver.methodSeparator = methodSeparator;
	}

	public static String getActionName(String actionName) {
		int i = actionName.lastIndexOf(methodSeparator);
		if (i == -1 || i == actionName.length())
			throw new NullPointerException("unresolve methodName from actionName:" + actionName);
		return actionName.substring(0, i);
	}

	public static String getMethodName(String actionName) {
		int i = actionName.lastIndexOf(methodSeparator);
		if (i == -1 || i == actionName.length())
			throw new NullPointerException("unresolve methodName from actionName:" + actionName);
		return actionName.substring(i + 1);
	}

}
