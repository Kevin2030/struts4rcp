package com.googlecode.struts4rcp.server.interceptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.googlecode.struts4rcp.server.ActionInterceptor;

/**
 * Action拦截器栈，用于排序拦截器。
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ActionInterceptorStack {

	private final List<ActionInterceptor> actionInterceptors = new ArrayList<ActionInterceptor>();

	public void setActionInterceptors(List<Object> interceptors) {
		this.actionInterceptors.clear();
		if (interceptors != null) {
			for (Iterator<Object> iterator = interceptors.iterator(); iterator.hasNext();) {
				Object interceptor = iterator.next();
				if (interceptor != null) {
					if (interceptor instanceof ActionInterceptor) {
						this.actionInterceptors.add((ActionInterceptor)interceptor);
					} else if (interceptor instanceof ActionInterceptorStack) {
						this.actionInterceptors.addAll(((ActionInterceptorStack)interceptor).getActionInterceptors());
					} else {
						throw new ClassCastException(interceptor.getClass().getName() + " unimplement interface " + ActionInterceptor.class.getName());
					}
				}
			}
		}
	}

	public List<ActionInterceptor> getActionInterceptors() {
		return actionInterceptors;
	}

}
