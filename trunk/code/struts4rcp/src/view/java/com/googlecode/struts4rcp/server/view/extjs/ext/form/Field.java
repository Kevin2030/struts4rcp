package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.BoxComponent;

/**
 *
 * @author oscar.xie
 *
 */
@Description("Base class for form fields that provides default event handling, sizing, value handling and other functionality. ")
public class Field extends BoxComponent {
	private String autoCreate;
	private String clearCls;
	private Boolean disabled;
	private String fieldClass;
	private String fieldLabel;
	private String focusClass;
	private Boolean hideLabel;
	private String inputType;
	private String invalidClass;
	private String invalidText;
	private String itemCls;
	private String labelSeparator;
	private String labelStyle;
	private String msgFx;
	private String msgTarget;
	private String name;
	private Boolean readOnly;
	private Integer tabIndex;
	private Boolean validateOnBlur;
	private Integer validationDelay;
	private String validationEvent;
	private String value;

	@Override
	protected String getDefaultKey() {
		return "editor";
	}

	public String getAutoCreate() {
		return autoCreate;
	}

	@Description("autoCreate : String/Object "
			+ "A DomHelper element spec, or true for a default element spec (defaults to {tag: 'input', type: 'text', size: '20', autocomplete: 'off'}) ")
	public void setAutoCreate(String autoCreate) {
		this.autoCreate = autoCreate;
	}

	public String getClearCls() {
		return clearCls;
	}

	@Description("clearCls : String"
			+ "The CSS class used to provide field clearing (defaults to 'x-form-clear-left') ")
	public void setClearCls(String clearCls) {
		this.clearCls = clearCls;
	}

	public Boolean isDisabled() {
		return disabled;
	}

	@Description("disabled : Boolean"
			+ "True to disable the field (defaults to false). ")
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getFieldClass() {
		return fieldClass;
	}

	@Description("fieldClass : String"
			+ "The default CSS class for the field (defaults to 'x-form-field') ")
	public void setFieldClass(String fieldClass) {
		this.fieldClass = fieldClass;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	@Description("fieldLabel : String "
			+ "The label text to display next to this field (defaults to '') ")
	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public String getFocusClass() {
		return focusClass;
	}

	@Description("focusClass : String"
			+ "The CSS class to use when the field receives focus (defaults to 'x-form-focus') ")
	public void setFocusClass(String focusClass) {
		this.focusClass = focusClass;
	}

	public Boolean isHideLabel() {
		return hideLabel;
	}

	@Description("hideLabel : Boolean"
			+ "True to completely hide the label element (defaults to false) ")
	public void setHideLabel(Boolean hideLabel) {
		this.hideLabel = hideLabel;
	}

	public String getInputType() {
		return inputType;
	}

	@Description("inputType : String"
			+ "The type attribute for input fields -- e.g. radio, text, password (defaults to 'text'). ")
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getInvalidClass() {
		return invalidClass;
	}

	@Description("invalidClass : String"
			+ "The CSS class to use when marking a field invalid (defaults to 'x-form-invalid') ")
	public void setInvalidClass(String invalidClass) {
		this.invalidClass = invalidClass;
	}

	public String getInvalidText() {
		return invalidText;
	}

	@Description("invalidText : String"
			+ "The error text to use when marking a field invalid and no message is provided (defaults to 'The value in this field is invalid') ")
	public void setInvalidText(String invalidText) {
		this.invalidText = invalidText;
	}

	public String getItemCls() {
		return itemCls;
	}

	@Description("itemCls : String "
			+ "An additional CSS class to apply to this field (defaults to the container's itemCls value if set, or '') ")
	public void setItemCls(String itemCls) {
		this.itemCls = itemCls;
	}

	public String getLabelSeparator() {
		return labelSeparator;
	}

	@Description("labelSeparator : String"
			+ "The standard separator to display after the text of each form label (defaults to the value of Ext.layout.FormLayout.labelSeparator, which is a colon ':' by default). To display no separator for this field's label specify empty string ''. ")
	public void setLabelSeparator(String labelSeparator) {
		this.labelSeparator = labelSeparator;
	}

	public String getLabelStyle() {
		return labelStyle;
	}

	@Description("labelStyle : String"
			+ "A CSS style specification to apply directly to this field's label (defaults to the container's labelStyle value if set, or ''). For example, labelStyle: 'font-weight:bold;'. ")
	public void setLabelStyle(String labelStyle) {
		this.labelStyle = labelStyle;
	}

	public String getMsgFx() {
		return msgFx;
	}

	@Description("msgFx : String"
			+ "Experimental The effect used when displaying a validation message under the field (defaults to 'normal'). ")
	public void setMsgFx(String msgFx) {
		this.msgFx = msgFx;
	}

	public String getMsgTarget() {
		return msgTarget;
	}

	@Description("msgTarget : String"
			+ "The location where error text should display. Should be one of the following values (defaults to 'qtip'):"
			+ "Value         Description"
			+ "-----------   ----------------------------------------------------------------------"
			+ "qtip          Display a quick tip when the user hovers over the field"
			+ "title         Display a default browser title attribute popup"
			+ "under         Add a block div beneath the field containing the error text"
			+ "side          Add an error icon to the right of the field with a popup on hover"
			+ "[element id]  Add the error text directly to the innerHTML of the specified element")
	public void setMsgTarget(String msgTarget) {
		this.msgTarget = msgTarget;
	}

	public String getName() {
		return name;
	}

	@Description("name : String" + "The field's HTML name attribute. ")
	public void setName(String name) {
		this.name = name;
	}

	public Boolean isReadOnly() {
		return readOnly;
	}

	@Description("readOnly : Boolean "
			+ "True to mark the field as readOnly in HTML (defaults to false) -- Note: this only sets the element's readOnly DOM attribute. ")
	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public Integer getTabIndex() {
		return tabIndex;
	}

	@Description("tabIndex : Number "
			+ "The tabIndex for this field. Note this only applies to fields that are rendered, not those which are built via applyTo (defaults to undefined). ")
	public void setTabIndex(Integer tabIndex) {
		this.tabIndex = tabIndex;
	}

	public Boolean isValidateOnBlur() {
		return validateOnBlur;
	}

	@Description("validateOnBlur : Boolean"
			+ "Whether the field should validate when it loses focus (defaults to true). ")
	public void setValidateOnBlur(Boolean validateOnBlur) {
		this.validateOnBlur = validateOnBlur;
	}

	public Integer getValidationDelay() {
		return validationDelay;
	}

	@Description("validationDelay : Number"
			+ "The length of time in milliseconds after user input begins until validation is initiated (defaults to 250) ")
	public void setValidationDelay(Integer validationDelay) {
		this.validationDelay = validationDelay;
	}

	@Variable
	public String getValidationEvent() {
		return validationEvent;
	}

	@Description("validationEvent : String/Boolean"
			+ "The event that should initiate field validation. Set to false to disable automatic validation (defaults to 'keyup'). ")
	public void setValidationEvent(String validationEvent) {
		this.validationEvent = validationEvent;
	}

	public String getValue() {
		return value;
	}

	@Description("value : Mixed" + "A value to initialize this field with. ")
	public void setValue(String value) {
		this.value = value;
	}

}
