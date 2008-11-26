package com.googlecode.struts4rcp.util.validator;

import java.util.Map;

import com.googlecode.struts4rcp.util.BeanUtils;

/**
 * 字段验证器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class FieldValidator implements Validator {

	protected final String filedName;

	protected final Validator validator;

	public FieldValidator(String filedName, Validator validator) {
		this.filedName = filedName;
		this.validator = validator;
	}

	@SuppressWarnings("unchecked")
	public void validate(Object model) throws ValidationException {
		if (model == null)
			validator.validate(null);
		else if (model instanceof Map)
			validator.validate(((Map)model).get(filedName));
		else
			validator.validate(BeanUtils.getProperty(model, filedName));
	}

}
