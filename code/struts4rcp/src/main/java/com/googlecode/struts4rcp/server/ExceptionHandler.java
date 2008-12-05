package com.googlecode.struts4rcp.server;

import java.io.Serializable;

public interface ExceptionHandler {

    /**
     * 处理异常
     * @param exception 待处理的异常
     * @param model 原始传入的参数
     * @return 传回返回值
     * @throws Exception 拦截过程中任意异常均向上抛出，由框架统一处理
     */
	Serializable handle(Throwable exception, Serializable model) throws Exception;

}
