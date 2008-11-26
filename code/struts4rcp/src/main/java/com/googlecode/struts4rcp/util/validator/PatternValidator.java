package com.googlecode.struts4rcp.util.validator;

/**
 * 正则表达式检验器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class PatternValidator extends StringValidator {

	protected String pattern;

	public PatternValidator() {

	}

	public PatternValidator(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	protected void doValidate(String model) throws ValidationException {
		if (! model.matches(pattern))
			throw new ValidationException();
	}

}
