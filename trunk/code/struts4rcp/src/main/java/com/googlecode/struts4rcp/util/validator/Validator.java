package com.googlecode.struts4rcp.util.validator;

/**
 * 数据检验器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Validator {

	/**
	 * 检验数据
	 * @param object 数据
	 * @throws ValidationException 数据检验失败时抛出
	 */
	void validate(Object object) throws ValidationException;

}
