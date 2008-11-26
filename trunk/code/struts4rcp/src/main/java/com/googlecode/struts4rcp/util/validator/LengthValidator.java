package com.googlecode.struts4rcp.util.validator;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class LengthValidator extends AbstractValidator {

	private int min;

	private int max;

	public LengthValidator() {

	}

	public LengthValidator(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void validate(Object model) throws ValidationException {
		if (model == null)
			return;
		if (model instanceof String && ((String)model).trim().length() == 0)
			return;
		if (model instanceof Collection && ((Collection<?>)model).size() == 0)
			return;
		if (model instanceof Map && ((Map<?, ?>)model).size() == 0)
			return;
	}

	protected void validateString(String model) throws ValidationException {
		if (min > 0 && model.length() < min)
			throw new ValidationException();
		if (max > 0 && model.length() > max)
			throw new ValidationException();
	}

	protected void validateCollection(Collection<?> model) throws ValidationException {
		if (min > 0 && model.size() < min)
			throw new ValidationException();
		if (max > 0 && model.size() > max)
			throw new ValidationException();
	}

	protected void validateMap(Map<?, ?> model) throws ValidationException {
		if (min > 0 && model.size() < min)
			throw new ValidationException();
		if (max > 0 && model.size() > max)
			throw new ValidationException();
	}

	protected void validateArray(Object array) throws ValidationException {
		if (min > 0 && Array.getLength(array) < min)
			throw new ValidationException();
		if (max > 0 && Array.getLength(array) > max)
			throw new ValidationException();
	}

}
