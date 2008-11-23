package com.googlecode.struts4rcp.server;

import java.io.Serializable;

import com.googlecode.struts4rcp.Action;

/**
 * Action拦截器接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ActionInterceptor {

    /**
     * 拦截Action执行过程
     * @param action 被拦截的Action类或下一拦截器的Action代理
     * @param model 传入的参数
     * @return 传回返回值
     * @throws Exception 拦截过程中任意异常均向上抛出，由框架统一处理
     */
	Serializable intercept(Action<Serializable, Serializable> action, Serializable model) throws Exception;

}
