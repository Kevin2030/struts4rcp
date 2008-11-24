package com.googlecode.struts4rcp.server.view.extjs.ext.tree;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.ext.Editor;

@Description("Provides editor functionality for inline tree node editing. Any valid Ext.form.Field can be used as the editor field. ")
public class TreeEditor extends Editor {
	private Integer editDelay;
	private Boolean hideEl;
	private Integer maxWidth;
	private Boolean shim;

	public Integer getEditDelay() {
		return editDelay;
	}

	public void setEditDelay(Integer editDelay) {
		this.editDelay = editDelay;
	}

	public Boolean isHideEl() {
		return hideEl;
	}

	public void setHideEl(Boolean hideEl) {
		this.hideEl = hideEl;
	}

	public Integer getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(Integer maxWidth) {
		this.maxWidth = maxWidth;
	}

	public Boolean isShim() {
		return shim;
	}

	public void setShim(Boolean shim) {
		this.shim = shim;
	}
}
