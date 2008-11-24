package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

@Description("Basic text field. Can be used as a direct replacement for traditional text inputs, or as the base class for more sophisticated input controls (like Ext.form.TextArea and Ext.form.ComboBox). ")
public class TextField extends Field {
	private Boolean allowBlank;
	private String blankText;
	private Boolean disableKeyFilter;
	private Boolean emptyClass;
	private String emptyText;
	private Boolean grow;
	private Integer growMax;
	private Integer growMin;
	private String maskRe;
	private Integer maxLength;
	private String maxLengthText;
	private Integer minLength;
	private String minLengthText;
	private String regex;
	private String regexText;
	private Boolean selectOnFocus;
	private String validator;
	private String vtype;
	private String vtypeText;

	public Boolean isAllowBlank() {
		return allowBlank;
	}

	@Description("allowBlank : Boolean"
			+ "False to validate that the value length > 0 (defaults to true) ")
	public void setAllowBlank(Boolean allowBlank) {
		this.allowBlank = allowBlank;
	}

	public String getBlankText() {
		return blankText;
	}

	@Description("blankText : String"
			+ "Error text to display if the allow blank validation fails (defaults to 'This field is required')")
	public void setBlankText(String blankText) {
		this.blankText = blankText;
	}

	public Boolean isDisableKeyFilter() {
		return disableKeyFilter;
	}

	@Description("disableKeyFilter : Boolean"
			+ "True to disable input keystroke filtering (defaults to false) ")
	public void setDisableKeyFilter(Boolean disableKeyFilter) {
		this.disableKeyFilter = disableKeyFilter;
	}

	public Boolean isEmptyClass() {
		return emptyClass;
	}

	@Description("emptyClass : String"
			+ "The CSS class to apply to an empty field to style the emptyText (defaults to 'x-form-empty-field'). This class is automatically added and removed as needed depending on the current field value. ")
	public void setEmptyClass(Boolean emptyClass) {
		this.emptyClass = emptyClass;
	}

	public String getEmptyText() {
		return emptyText;
	}

	@Description("emptyText : String"
			+ "The default text to display in an empty field (defaults to null). ")
	public void setEmptyText(String emptyText) {
		this.emptyText = emptyText;
	}

	public Boolean isGrow() {
		return grow;
	}

	@Description("grow : Boolean"
			+ "True if this field should automatically grow and shrink to its content ")
	public void setGrow(Boolean grow) {
		this.grow = grow;
	}

	public Integer getGrowMax() {
		return growMax;
	}

	@Description("growMax : Number"
			+ "The maximum width to allow when grow = true (defaults to 800) ")
	public void setGrowMax(Integer growMax) {
		this.growMax = growMax;
	}

	public Integer getGrowMin() {
		return growMin;
	}

	@Description("growMin : Number"
			+ "The minimum width to allow when grow = true (defaults to 30) ")
	public void setGrowMin(Integer growMin) {
		this.growMin = growMin;
	}

	@Variable
	public String getMaskRe() {
		return maskRe;
	}

	@Description("maskRe : RegExp"
			+ "An input mask regular expression that will be used to filter keystrokes that don't match (defaults to null) ")
	public void setMaskRe(String maskRe) {
		this.maskRe = maskRe;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	@Description("maxLength : Number"
			+ "Maximum input field length allowed (defaults to Number.MAX_VALUE) ")
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public String getMaxLengthText() {
		return maxLengthText;
	}

	@Description("maxLengthText : String"
			+ "Error text to display if the maximum length validation fails (defaults to 'The maximum length for this field is {maxLength}') ")
	public void setMaxLengthText(String maxLengthText) {
		this.maxLengthText = maxLengthText;
	}

	public Integer getMinLength() {
		return minLength;
	}

	@Description("minLength : Number"
			+ "Minimum input field length required (defaults to 0) ")
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public String getMinLengthText() {
		return minLengthText;
	}

	@Description("minLengthText : String "
			+ "Error text to display if the minimum length validation fails (defaults to 'The minimum length for this field is {minLength}') ")
	public void setMinLengthText(String minLengthText) {
		this.minLengthText = minLengthText;
	}

	@Variable
	public String getRegex() {
		return regex;
	}

	@Description("regex : RegExp "
			+ "A JavaScript RegExp object to be tested against the field value during validation (defaults to null). If available, this regex will be evaluated only after the basic validators all return true, and will be passed the current field value. If the test fails, the field will be marked invalid using regexText. ")
	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getRegexText() {
		return regexText;
	}

	@Description("regexText : String"
			+ "The error text to display if regex is used and the test fails during validation (defaults to '') ")
	public void setRegexText(String regexText) {
		this.regexText = regexText;
	}

	public Boolean isSelectOnFocus() {
		return selectOnFocus;
	}

	@Description("selectOnFocus : Boolean"
			+ "True to automatically select any existing field text when the field receives input focus (defaults to false) ")
	public void setSelectOnFocus(Boolean selectOnFocus) {
		this.selectOnFocus = selectOnFocus;
	}

	@Variable
	public String getValidator() {
		return validator;
	}

	@Description("validator : Function "
			+ "A custom validation function to be called during field validation (defaults to null). If available, this function will be called only after the basic validators all return true, and will be passed the current field value and expected to return boolean true if the value is valid or a string error message if invalid. ")
	public void setValidator(String validator) {
		this.validator = validator;
	}

	public String getVtype() {
		return vtype;
	}

	@Description("vtype : String"
			+ "A validation type name as defined in Ext.form.VTypes (defaults to null) ")
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getVtypeText() {
		return vtypeText;
	}

	@Description("vtypeText : String "
			+ "A custom error message to display in place of the default message provided for the vtype currently set for this field (defaults to ''). Only applies if vtype is set, else ignored. ")
	public void setVtypeText(String vtypeText) {
		this.vtypeText = vtypeText;
	}

}
