package com.googlecode.struts4rcp.util.validator;

public abstract class PatternValidator implements Validator {

	public void validate(Object object) throws ValidationException {
		if (object == null)
			return;
		if (! (object instanceof String))
			return;
		String str = (String)object;
		str = str.trim();
		if (str.trim().length() == 0)
			return;
		if (! str.matches(getPattern()))
			throw new ValidationException();
	}

	protected abstract String getPattern();

}
