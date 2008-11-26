package com.googlecode.struts4rcp.util.validator;

/**
 * 字符串验证基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class StringValidator implements Validator {

	public void validate(Object model) throws ValidationException {
		if (model == null)
			return;
		if (! (model instanceof String))
			return;
		String str = (String)model;
		str = str.trim();
		if (str.trim().length() == 0)
			return;
		doValidate(str);
	}

	protected abstract void doValidate(String model) throws ValidationException;

}