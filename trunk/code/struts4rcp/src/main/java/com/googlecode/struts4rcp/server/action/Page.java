package com.googlecode.struts4rcp.server.action;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 页面路径标注
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Page {

	/**
	 * 页面路径
	 * @return 页面路径
	 */
	String value();

}