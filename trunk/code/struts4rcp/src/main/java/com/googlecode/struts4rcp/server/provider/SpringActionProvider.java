package com.googlecode.struts4rcp.server.provider;

import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.ActionInterceptor;
import com.googlecode.struts4rcp.server.interceptor.ActionInterceptorStack;

/**
 * ActionProvider Spring Implementation. See:
 * <a href="http://www.springframework.org">http://www.springframework.org</a>
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class SpringActionProvider extends AbstractActionProvider {

	private ApplicationContext applicationContext;

	protected void init(ServletContext servletContext, ServletConfig servletConfig) {
		this.applicationContext = ContextLoader.getCurrentWebApplicationContext();
		if (this.applicationContext == null) { // 如果未配置Spring上下文，启用缺省配置
			throw new IllegalStateException("Spring framework non initialized! Please config web.xml: \n<listener><listener-class>org.springframework.web.context.ContextLoaderListener</listener-class></listener>" );
		}
	}

	@SuppressWarnings("unchecked")
	protected Action<Serializable, Serializable> findAction(String actionName) throws Exception {
		return (Action<Serializable, Serializable>)applicationContext.getBean(actionName);
	}

	/**
	 * 拦截器栈Bean引用ID，该Bean应该为<code>ActionInterceptorStack</code>的实例
	 * @see com.googlecode.struts4rcp.server.interceptor.ActionInterceptorStack
	 */
	public static final String ACTION_INTERCEPTOR_STACK_BEAN_ID = "actionInterceptorStack";

	protected List<ActionInterceptor> findActionInterceptors() throws Exception {
		try {
			ActionInterceptorStack actionInterceptorStack = (ActionInterceptorStack)applicationContext.getBean(ACTION_INTERCEPTOR_STACK_BEAN_ID);
			if (actionInterceptorStack == null)
				return null;
			return actionInterceptorStack.getActionInterceptors();
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}

}
