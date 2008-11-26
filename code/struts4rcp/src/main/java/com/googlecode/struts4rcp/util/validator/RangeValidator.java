package com.googlecode.struts4rcp.util.validator;

public class RangeValidator extends AbstractValidator {

	private final int min;

	private final int max;

	public RangeValidator(int min, int max) {
		this.min = min;
		this.max = max;
	}

	protected void validateNumber(Number model) throws ValidationException {
		if (model.intValue() < min)
			throw new ValidationException();
		if (model.intValue() > max)
			throw new ValidationException();
	}

}
