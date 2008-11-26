package com.googlecode.struts4rcp.util.validator;

import java.util.Collection;
import java.util.Map;

public class AbstractValidator implements Validator {

	public void validate(Object model) throws ValidationException {
		if (model == null)
			return;
		if (model instanceof Number) {
			validateNumber(((Number)model));
		} else if (model instanceof String) {
			validateString(((String)model).trim());
		} else if (model.getClass().isArray()) {
			validateArray(model);
		} else if (model instanceof Collection) {
			validateCollection((Collection<?>)model);
		} else if (model instanceof Map){
			validateMap((Map<?, ?>)model);
		} else {
			validateBean(model);
		}
	}

	protected void validateNumber(Number model) throws ValidationException {

	}

	protected void validateString(String model) throws ValidationException {

	}

	protected void validateArray(Object array) throws ValidationException {

	}

	protected void validateCollection(Collection<?> model) throws ValidationException {

	}

	protected void validateMap(Map<?, ?> model) throws ValidationException {

	}

	protected void validateBean(Object model) throws ValidationException {

	}

}
