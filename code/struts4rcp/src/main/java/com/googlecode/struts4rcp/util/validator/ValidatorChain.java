package com.googlecode.struts4rcp.util.validator;

import java.util.Collection;

/**
 * 验证器链
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ValidatorChain implements Validator {

	private Collection<Validator> validators;

	public ValidatorChain(Collection<Validator> validators) {
		this.validators = validators;
	}

	public void validate(Object model) throws ValidationException {
		if (validators != null && validators.size() > 0) {
			for (Validator validator : validators) {
				validator.validate(model);
			}
		}
	}

}
