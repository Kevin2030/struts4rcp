package com.xxx.demo.action;

import java.io.Serializable;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.ActionInterceptor;

public class ExceptionInterceptor implements ActionInterceptor {

	public Serializable intercept(Action<Serializable, Serializable> action,
			Serializable model) throws Exception {
		try {
			System.out.println("ExceptionInterceptor...");
			return action.execute(model);
		} catch (Exception e) {
			System.out.println("ExceptionInterceptor:" + e.getMessage());
			throw e;
		}
	}

}
