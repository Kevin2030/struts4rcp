package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.ext.Panel;

@Description("Standard container used for grouping form fields. ")
public class FieldSet extends Panel {
	private String checkboxName;
	private Boolean checkboxToggle;
	private String itemCls;
	private Integer labelWidth;

	public String getCheckboxName() {
		return checkboxName;
	}

	@Description("checkboxName : String"
			+ "The name to assign to the fieldset's checkbox if checkboxToggle = true (defaults to '[checkbox id]-checkbox'). ")
	public void setCheckboxName(String checkboxName) {
		this.checkboxName = checkboxName;
	}

	public Boolean isCheckboxToggle() {
		return checkboxToggle;
	}

	@Description("checkboxToggle : Boolean "
			+ "True to render a checkbox into the fieldset frame just in front of the legend (defaults to false). The fieldset will be expanded or collapsed when the checkbox is toggled. ")
	public void setCheckboxToggle(Boolean checkboxToggle) {
		this.checkboxToggle = checkboxToggle;
	}

	public String getItemCls() {
		return itemCls;
	}

	@Description("itemCls : String"
			+ "A css class to apply to the x-form-item of fields. This property cascades to child containers. ")
	public void setItemCls(String itemCls) {
		this.itemCls = itemCls;
	}

	public Integer getLabelWidth() {
		return labelWidth;
	}

	@Description("labelWidth : Number"
			+ "The width of labels. This property cascades to child containers. ")
	public void setLabelWidth(Integer labelWidth) {
		this.labelWidth = labelWidth;
	}

}
