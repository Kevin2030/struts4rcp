package com.googlecode.struts4rcp.util.validator;

/**
 * 正则表达式检验器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class PatternValidator implements Validator {

	protected final String pattern;

	public PatternValidator(String pattern) {
		this.pattern = pattern;
	}

	public void validate(Object object) throws ValidationException {
		if (object == null)
			return;
		if (! (object instanceof String))
			return;
		String str = (String)object;
		str = str.trim();
		if (str.trim().length() == 0)
			return;
		if (! str.matches(pattern))
			throw new ValidationException();
	}

}
