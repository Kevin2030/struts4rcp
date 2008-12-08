package com.googlecode.struts4rcp.server.action;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Restful请求资源路径标注
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Path {

	/**
	 * 资源目录
	 * @return 资源目录
	 */
	String directory() default "";

	/**
	 * 资源路径，如果配了资源目录，将自动作为路径前缀
	 * @return 资源路径
	 */
	String value();

}
