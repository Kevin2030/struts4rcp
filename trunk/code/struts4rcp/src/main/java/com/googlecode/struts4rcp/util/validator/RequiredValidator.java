package com.googlecode.struts4rcp.util.validator;

public class RequiredValidator implements Validator {

	public void validate(Object object) throws ValidationException {
		if (object == null)
			throw new ValidationException();
	}

}
