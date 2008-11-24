package com.googlecode.struts4rcp.server.view.extjs.ext.form;

public class NumberField extends TextField {
	private Boolean allowDecimals;
	private Boolean allowNegative;
	private String baseChars;
	private Integer decimalPrecision;
	private String decimalSeparator;
	private String maxText;
	private Integer maxValue;
	private String minText;
	private Integer minValue;
	private String nanText;

	public Boolean isAllowDecimals() {
		return allowDecimals;
	}

	public void setAllowDecimals(Boolean allowDecimals) {
		this.allowDecimals = allowDecimals;
	}

	public Boolean isAllowNegative() {
		return allowNegative;
	}

	public void setAllowNegative(Boolean allowNegative) {
		this.allowNegative = allowNegative;
	}

	public String getBaseChars() {
		return baseChars;
	}

	public void setBaseChars(String baseChars) {
		this.baseChars = baseChars;
	}

	public Integer getDecimalPrecision() {
		return decimalPrecision;
	}

	public void setDecimalPrecision(Integer decimalPrecision) {
		this.decimalPrecision = decimalPrecision;
	}

	public String getDecimalSeparator() {
		return decimalSeparator;
	}

	public void setDecimalSeparator(String decimalSeparator) {
		this.decimalSeparator = decimalSeparator;
	}

	public String getMaxText() {
		return maxText;
	}

	public void setMaxText(String maxText) {
		this.maxText = maxText;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinText() {
		return minText;
	}

	public void setMinText(String minText) {
		this.minText = minText;
	}

	public Integer getMinValue() {
		return minValue;
	}

	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}

	public String getNanText() {
		return nanText;
	}

	public void setNanText(String nanText) {
		this.nanText = nanText;
	}

}
