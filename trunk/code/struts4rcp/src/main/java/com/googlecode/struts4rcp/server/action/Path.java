package com.googlecode.struts4rcp.server.action;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Restful请求路径标注
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Path {

	/**
	 * 资源目录路径
	 * @return 资源目录路径
	 */
	String type();

	/**
	 * 资源路径
	 * @return 资源路径
	 */
	String value();

}
