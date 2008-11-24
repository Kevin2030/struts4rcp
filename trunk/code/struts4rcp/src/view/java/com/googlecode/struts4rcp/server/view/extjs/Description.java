package com.googlecode.struts4rcp.server.view.extjs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述信息标注
 * 
 * @author Achievo: 梁飞
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })
public @interface Description {

	String value();

}
