package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Provides a convenient wrapper for TextFields that adds a clickable trigger button (looks like a combobox by default). The trigger has no default action, so you must assign a function to implement the trigger click handler by overriding onTriggerClick. You can create a TriggerField directly, as it renders exactly like a combobox for which you can provide a custom implementation. For example:"
		+ "var trigger = new Ext.form.TriggerField();"
		+ "trigger.onTriggerClick = myTriggerFn;"
		+ "trigger.applyToMarkup('my-field');"
		+ "However, in general you will most likely want to use TriggerField as the base class for a reusable component. Ext.form.DateField and Ext.form.ComboBox are perfect examples of this. ")
public class TriggerField extends TextField {
	private Boolean hideTrigger;
	private String triggerClass;

	public Boolean isHideTrigger() {
		return hideTrigger;
	}

	@Description("hideTrigger : Boolean"
			+ "True to hide the trigger element and display only the base text field (defaults to false) ")
	public void setHideTrigger(Boolean hideTrigger) {
		this.hideTrigger = hideTrigger;
	}

	public String getTriggerClass() {
		return triggerClass;
	}

	@Description("triggerClass : String"
			+ "A CSS class to apply to the trigger ")
	public void setTriggerClass(String triggerClass) {
		this.triggerClass = triggerClass;
	}
}
