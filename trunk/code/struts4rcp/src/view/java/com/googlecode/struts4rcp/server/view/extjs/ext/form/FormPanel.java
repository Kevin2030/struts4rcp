package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.ext.Panel;

@Description("FormPanel uses a Ext.layout.FormLayout internally, and that is required for fields and labels to work correctly within the FormPanel's layout. To nest additional layout styles within a FormPanel, you should nest additional Panels or other containers that can provide additional layout functionality. You should not override FormPanel's layout. "
		+ "By default, Ext Forms are submitted through Ajax, using Ext.form.Action. To enable normal browser submission of the Ext Form contained in this FormPanel, override the Form's onSubmit, and submit methods:"
		+ "var myForm = new Ext.form.FormPanel({"
		+ "        onSubmit: Ext.emptyFn,"
		+ "        submit: function() {"
		+ "            this.getForm().getEl().dom.submit();"
		+ "        }"
		+ "    });")
public class FormPanel extends Panel {
	private String itemCls;
	private String labelAlign;
	private Integer labelWidth;
	private Integer monitorPoll;
	private Boolean monitorValid;
	private Boolean fileUpload;

	public String getItemCls() {
		return itemCls;
	}

	@Description("itemCls : String"
			+ "A css class to apply to the x-form-item of fields. This property cascades to child containers. ")
	public void setItemCls(String itemCls) {
		this.itemCls = itemCls;
	}

	public String getLabelAlign() {
		return labelAlign;
	}

	@Description("labelAlign : String"
			+ "Valid values are 'left,' 'top' and 'right' (defaults to 'left'). This property cascades to child containers if not set. ")
	public void setLabelAlign(String labelAlign) {
		this.labelAlign = labelAlign;
	}

	public Integer getLabelWidth() {
		return labelWidth;
	}

	@Description("labelWidth : Number"
			+ "The width of labels. This property cascades to child containers. ")
	public void setLabelWidth(Integer labelWidth) {
		this.labelWidth = labelWidth;
	}

	public Integer getMonitorPoll() {
		return monitorPoll;
	}

	@Description("monitorPoll : Number "
			+ "The milliseconds to poll valid state, ignored if monitorValid is not true (defaults to 200) ")
	public void setMonitorPoll(Integer monitorPoll) {
		this.monitorPoll = monitorPoll;
	}

	public Boolean isMonitorValid() {
		return monitorValid;
	}

	@Description("monitorValid : Boolean "
			+ "If true the form monitors its valid state client-side and fires a looping event with that state. This is required to bind buttons to the valid state using the config value formBind:true on the button. ")
	public void setMonitorValid(Boolean monitorValid) {
		this.monitorValid = monitorValid;
	}

	public Boolean isFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(Boolean fileUpload) {
		this.fileUpload = fileUpload;
	}

}
