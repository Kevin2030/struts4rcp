package com.googlecode.struts4rcp.server.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.ActionInterceptor;
import com.googlecode.struts4rcp.util.ClassUtils;

/**
 * 类名反射Action工厂实现
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ClassActionFactory extends AbstractActionFactory {

	protected final List<ActionInterceptor> actionInterceptors = new ArrayList<ActionInterceptor>();

	protected static final String ACTION_INTERCEPTORS_PARAM_NAME = "actionInterceptors";

	protected void init(ServletContext servletContext, ServletConfig servletConfig) {
		String actionInterceptorsConfig = servletConfig.getInitParameter(ACTION_INTERCEPTORS_PARAM_NAME);
		if (actionInterceptorsConfig != null && actionInterceptorsConfig.trim().length() > 0) {
			String[] actionInterceptorClassNames = actionInterceptorsConfig.split("\\,");
			if (actionInterceptorClassNames.length > 0) {
				for (String actionInterceptorClassName : actionInterceptorClassNames) {
					if (actionInterceptorClassName != null && actionInterceptorClassName.trim().length() > 0) {
						try {
							Class<?> actionInterceptorClass = ClassUtils.forName(actionInterceptorClassName.trim());
							if (ActionInterceptor.class.isAssignableFrom(actionInterceptorClass)) {
								ActionInterceptor actionInterceptor = (ActionInterceptor)actionInterceptorClass.newInstance();
								actionInterceptors.add(actionInterceptor);
							} else {
								logger.error(actionInterceptorClass.getName() + " unimplementet interface " + ActionInterceptor.class.getName());
							}
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
				}
			}
		}
	}

	public void shutdown() {
		actionInterceptors.clear();
	}

	@SuppressWarnings("unchecked")
	protected Action<Serializable, Serializable> findAction(String actionName) throws Exception {
		Class<?> actionClass = ClassUtils.forName(actionName);
		if (! Action.class.isAssignableFrom(actionClass))
			throw new RuntimeException(actionClass.getName() + " unimplementet interface " + Action.class.getName());
		return (Action<Serializable, Serializable>)actionClass.newInstance();
	}

	protected List<ActionInterceptor> findActionInterceptors() throws Exception {
		return actionInterceptors;
	}

}