package com.googlecode.struts4rcp.server.interceptor;

import java.io.Serializable;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.ActionContext;
import com.googlecode.struts4rcp.server.ActionInterceptor;
import com.googlecode.struts4rcp.server.action.Validatable;
import com.googlecode.struts4rcp.util.validator.Validator;

/**
 * 验证拦截器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ValidationInterceptor implements ActionInterceptor {

	public Serializable intercept(Action<Serializable, Serializable> action,
			Serializable model) throws Exception {
		Action<Serializable, Serializable> srcAction = ActionContext.getContext().getAction();
		if (srcAction instanceof Validatable) {
			Validatable validationAction = (Validatable)srcAction;
			Validator validator = validationAction.getValidator();
			if (validator != null)
				validator.validate(model);
		}
		return action.execute(model);
	}

}
