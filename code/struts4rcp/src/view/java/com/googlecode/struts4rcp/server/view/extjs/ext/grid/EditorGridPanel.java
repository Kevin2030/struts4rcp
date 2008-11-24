package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Class for creating and editable grid. ")
public class EditorGridPanel extends GridPanel {
	private Integer clicksToEdit;

	public Integer getClicksToEdit() {
		return clicksToEdit;
	}

	@Description("clicksToEdit : Number"
			+ "The number of clicks on a cell required to display the cell's editor (defaults to 2) ")
	public void setClicksToEdit(Integer clicksToEdit) {
		this.clicksToEdit = clicksToEdit;
	}

}
