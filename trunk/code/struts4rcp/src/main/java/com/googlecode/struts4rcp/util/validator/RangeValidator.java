package com.googlecode.struts4rcp.util.validator;

public class RangeValidator extends AbstractValidator {

	private int min;

	private int max;

	public RangeValidator() {

	}

	public RangeValidator(int min, int max) {
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

	protected void validateNumber(Number model) throws ValidationException {
		if (model.intValue() < min)
			throw new ValidationException();
		if (model.intValue() > max)
			throw new ValidationException();
	}

}
