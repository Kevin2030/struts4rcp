package com.googlecode.struts4rcp.util.validator.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface Range {

	String field() default "";

	int max() default Integer.MAX_VALUE;

	int min() default Integer.MIN_VALUE;

}
