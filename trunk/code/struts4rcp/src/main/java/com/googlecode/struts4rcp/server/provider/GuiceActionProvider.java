package com.googlecode.struts4rcp.server.provider;

import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.ActionInterceptor;
import com.googlecode.struts4rcp.server.ExceptionHandler;
import com.googlecode.struts4rcp.server.interceptor.ActionInterceptorStack;
import com.googlecode.struts4rcp.util.ClassUtils;

/**
 * ActionProvider Google Guice Implementation. See: <a
 * href="http://code.google.com/p/google-guice">http://code.google.com/p/google-guice</a>
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class GuiceActionProvider extends AbstractActionProvider {

	private Injector injector;

	protected void init(ServletContext servletContext, ServletConfig servletConfig) {
		try {
			String moduleClassName = servletContext.getInitParameter("moduleClass");
			if (moduleClassName == null)
				throw new NullPointerException("web.xml unconfig param \"moduleClass\", eg: \n<context-param>\n\t<param-name>moduleClass</param-name>\n\t<param-value>com.xxx.XXXModule</param-value>\n</context-param>\n");
			Class<?> moduleClass = ClassUtils.forName(moduleClassName);
			if (Module.class.isAssignableFrom(moduleClass))
				throw new ClassCastException(moduleClass.getClass().getName() + " unimplementet interface " + Module.class.getName());
			Module module = (Module)moduleClass.newInstance();
			this.injector = Guice.createInjector(module);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	protected Action<Serializable, Serializable> findAction(String actionName) throws Exception {
		Class<?> actionClass = ClassUtils.forName(actionName);
		if (! Action.class.isAssignableFrom(actionClass))
			throw new RuntimeException(actionClass.getName() + " unimplementet interface " + Action.class.getName());
		return (Action<Serializable, Serializable>) injector.getInstance(actionClass);
	}

	protected List<ActionInterceptor> findActionInterceptors() throws Exception {
		return injector.getProvider(ActionInterceptorStack.class).get().getActionInterceptors();
	}

	public ExceptionHandler getExceptionHandler(
			Class<? extends Throwable> exceptionClass) {
		return (ExceptionHandler) injector.getInstance(ExceptionHandler.class);
	}

}
