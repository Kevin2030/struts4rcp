package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Single checkbox field. Can be used as a direct replacement for traditional checkbox fields. ")
public class Checkbox extends Field {
	private String boxLabel;
	private Boolean checked;
	private String inputValue;

	public String getBoxLabel() {
		return boxLabel;
	}

	@Description("boxLabel : String"
			+ "The text that appears beside the checkbox ")
	public void setBoxLabel(String boxLabel) {
		this.boxLabel = boxLabel;
	}

	public Boolean isChecked() {
		return checked;
	}

	@Description("checked : Boolean"
			+ "True if the the checkbox should render already checked (defaults to false)")
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getInputValue() {
		return inputValue;
	}

	@Description("inputValue : String"
			+ "The value that should go into the generated input element's value attribute ")
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

}
