package com.googlecode.struts4rcp.util.validator;

public class EmailValidator extends PatternValidator {

	protected String getPattern() {
		return "^[\\-|_|0-9|a-z|A-Z]+\\@[\\-|_|0-9|a-z|A-Z]+\\.[a-z|A-Z][\\.|a-z|A-Z]*$";
	}

}