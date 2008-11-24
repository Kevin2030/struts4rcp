package com.googlecode.struts4rcp.server.view.extjs.ext.dd;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("A specialized drag proxy that supports a drop status icon, Ext.Layer styles and auto-repair. This is the default drag proxy used by all Ext.dd components. ")
public class StatusProxy extends ComponentTag {
	private String dropAllowed;
	private String dropNotAllowed;

	public String getDropAllowed() {
		return dropAllowed;
	}

	@Description("dropAllowed : String"
			+ "The CSS class to apply to the status element when drop is allowed (defaults to 'x-dd-drop-ok'). ")
	public void setDropAllowed(String dropAllowed) {
		this.dropAllowed = dropAllowed;
	}

	public String getDropNotAllowed() {
		return dropNotAllowed;
	}

	@Description("dropNotAllowed : String"
			+ "The CSS class to apply to the status element when drop is not allowed (defaults to 'x-dd-drop-nodrop'). ")
	public void setDropNotAllowed(String dropNotAllowed) {
		this.dropNotAllowed = dropNotAllowed;
	}
}
