package com.googlecode.struts4rcp.util.validator;

public class RequiredValidator implements Validator {

	public void validate(Object object) throws ValidationException {
		if (object == null)
			throw new ValidationException();
		if (object instanceof String && ((String)object).trim().length() == 0)
			throw new ValidationException();
	}

}
