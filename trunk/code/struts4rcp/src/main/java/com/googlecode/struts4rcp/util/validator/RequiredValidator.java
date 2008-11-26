package com.googlecode.struts4rcp.util.validator;

import java.util.Collection;
import java.util.Map;

public class RequiredValidator implements Validator {

	public void validate(Object model) throws ValidationException {
		if (model == null)
			throw new ValidationException();
		if (model instanceof String && ((String)model).trim().length() == 0)
			throw new ValidationException();
		if (model instanceof Collection && ((Collection<?>)model).size() == 0)
			throw new ValidationException();
		if (model instanceof Map && ((Map<?, ?>)model).size() == 0)
			throw new ValidationException();
	}

}
