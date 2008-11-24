package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

@Description("Provides a date input field with a Ext.DatePicker dropdown and automatic date validation. ")
public class DateField extends TriggerField {
	private String altFormats;
	private String disabledDates;
	private String disabledDatesText;
	private String disabledDays;
	private String disabledDaysText;
	private String format;
	private String maxText;
	private String maxValue;
	private String minText;
	private String minValue;

	public String getAltFormats() {
		return altFormats;
	}

	@Description("altFormats : String"
			+ "Multiple date formats separated by '|' to try when parsing a user input value and it doesn't match the defined format (defaults to 'm/d/Y|m-d-y|m-d-Y|m/d|m-d|d'). ")
	public void setAltFormats(String altFormats) {
		this.altFormats = altFormats;
	}

	@Variable
	public String getDisabledDates() {
		return disabledDates;
	}

	@Description("disabledDates : Array "
			+ "An array of 'dates' to disable, as strings. These strings will be used to build a dynamic regular expression so they are very powerful. Some examples:"
			+ "['03/08/2003', '09/16/2003'] would disable those exact dates "
			+ "['03/08', '09/16'] would disable those days for every year"
			+ "['^03/08'] would only match the beginning (useful if you are using short years)"
			+ "['03/../2006'] would disable every day in March 2006"
			+ "[^03'] would disable every day in every March"
			+ "In order to support regular expressions, if you are using a date format that has '.' in it, you will have to escape the dot when restricting dates. For example: ['03\\.08\\.03']. ")
	public void setDisabledDates(String disabledDates) {
		this.disabledDates = disabledDates;
	}

	public String getDisabledDatesText() {
		return disabledDatesText;
	}

	@Description("disabledDatesText : String"
			+ "The tooltip text to display when the date falls on a disabled date (defaults to 'Disabled')")
	public void setDisabledDatesText(String disabledDatesText) {
		this.disabledDatesText = disabledDatesText;
	}

	@Variable
	public String getDisabledDays() {
		return disabledDays;
	}

	@Description("disabledDays : Array"
			+ "An array of days to disable, 0 based. For example, [0, 6] disables Sunday and Saturday (defaults to null). ")
	public void setDisabledDays(String disabledDays) {
		this.disabledDays = disabledDays;
	}

	public String getDisabledDaysText() {
		return disabledDaysText;
	}

	@Description("disabledDaysText : String"
			+ "The tooltip to display when the date falls on a disabled day (defaults to 'Disabled') ")
	public void setDisabledDaysText(String disabledDaysText) {
		this.disabledDaysText = disabledDaysText;
	}

	public String getFormat() {
		if (format == null)
			format = "Y-m-d";
		return format;
	}

	@Description("format : String "
			+ "The default date format string which can be overriden for localization support. The format must be valid according to Date.parseDate (defaults to 'm/d/y'). ")
	public void setFormat(String format) {
		this.format = format;
	}

	public String getMaxText() {
		return maxText;
	}

	@Description("maxText : String "
			+ "The error text to display when the date in the cell is after maxValue (defaults to 'The date in this field must be before {maxValue}'). ")
	public void setMaxText(String maxText) {
		this.maxText = maxText;
	}

	public String getMaxValue() {
		return maxValue;
	}

	@Description("maxValue : Date/String"
			+ "The maximum allowed date. Can be either a Javascript date object or a string date in a valid format (defaults to null). ")
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinText() {
		return minText;
	}

	@Description("minText : String "
			+ "The error text to display when the date in the cell is before minValue (defaults to 'The date in this field must be after {minValue}'). ")
	public void setMinText(String minText) {
		this.minText = minText;
	}

	public String getMinValue() {
		return minValue;
	}

	@Description("minValue : Date/String"
			+ "The minimum allowed date. Can be either a Javascript date object or a string date in a valid format (defaults to null). ")
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

}
