package com.googlecode.struts4rcp.util.validator;

/**
 * 数据验证器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Validator {

	/**
	 * 验证数据
	 * @param object 数据
	 * @throws ValidationException 数据验证失败时抛出
	 */
	void validate(Object object) throws ValidationException;

}
