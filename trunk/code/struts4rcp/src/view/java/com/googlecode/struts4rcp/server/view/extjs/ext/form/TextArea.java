package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Multiline text field. Can be used as a direct replacement for traditional textarea fields, plus adds support for auto-sizing. ")
public class TextArea extends TextField {
	private Boolean preventScrollbars;

	public Boolean isPreventScrollbars() {
		return preventScrollbars;
	}

	@Description("preventScrollbars : Boolean "
			+ "True to prevent scrollbars from appearing regardless of how much text is in the field (equivalent to setting overflow: hidden, defaults to false) ")
	public void setPreventScrollbars(Boolean preventScrollbars) {
		this.preventScrollbars = preventScrollbars;
	}

}
