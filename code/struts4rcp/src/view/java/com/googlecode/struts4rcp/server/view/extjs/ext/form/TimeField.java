package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Provides a time input field with a time dropdown and automatic time validation. ")
public class TimeField extends ComboBox {
	private String altFormats;
	private String format;
	private Integer increment;
	private String maxText;
	private String maxValue;
	private String minText;
	private String minValue;

	public String getAltFormats() {
		return altFormats;
	}

	@Description("altFormats : String "
			+ "Multiple date formats separated by '|' to try when parsing a user input value and it doesn't match the defined format (defaults to 'm/d/Y|m-d-y|m-d-Y|m/d|m-d|d'). ")
	public void setAltFormats(String altFormats) {
		this.altFormats = altFormats;
	}

	public String getFormat() {
		if (format == null)
			format = "H:i";
		return format;
	}

	@Description("format : String "
			+ "The default date format string which can be overriden for localization support. The format must be valid according to Date.parseDate (defaults to 'm/d/y'). ")
	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getIncrement() {
		return increment;
	}

	@Description("increment : Number"
			+ "The number of minutes between each time value in the list (defaults to 15). ")
	public void setIncrement(Integer increment) {
		this.increment = increment;
	}

	public String getMaxText() {
		return maxText;
	}

	@Description("maxLength : Number"
			+ "Maximum input field length allowed (defaults to Number.MAX_VALUE) ")
	public void setMaxText(String maxText) {
		this.maxText = maxText;
	}

	public String getMaxValue() {
		return maxValue;
	}

	@Description("maxValue : Date/String"
			+ "The maximum allowed time. Can be either a Javascript date object or a string date in a valid format (defaults to null). ")
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinText() {
		return minText;
	}

	@Description("minText : String "
			+ "The error text to display when the date in the cell is before minValue (defaults to 'The time in this field must be equal to or after {0}'). ")
	public void setMinText(String minText) {
		this.minText = minText;
	}

	public String getMinValue() {
		return minValue;
	}

	@Description("minValue : Date/String"
			+ "The minimum allowed time. Can be either a Javascript date object or a string date in a valid format (defaults to null). ")
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
}
