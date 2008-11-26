package com.googlecode.struts4rcp.util.validator;

/**
 * 正则表达式检验器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class PatternValidator extends StringValidator {

	protected final String pattern;

	public PatternValidator(String pattern) {
		this.pattern = pattern;
	}

	@Override
	protected void doValidate(String model) throws ValidationException {
		if (! model.matches(pattern))
			throw new ValidationException();
	}

}
