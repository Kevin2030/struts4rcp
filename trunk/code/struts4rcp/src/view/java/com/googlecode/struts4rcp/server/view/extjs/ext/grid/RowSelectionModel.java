package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

@Description("The default SelectionModel used by Ext.grid.Grid. It supports multiple selections and keyboard selection/navigation. The objects stored as selections and returned by getSelected, and getSelections are the Records which provide the data for the selected rows. ")
public class RowSelectionModel extends AbstractSelectionModel {
	private Boolean singleSelect;
	private String selectRow;

	public Boolean isSingleSelect() {
		return singleSelect;
	}

	@Description("singleSelect : Boolean, "
			+ "True to allow selection of only one row at a time (defaults to false) ")
	public void setSingleSelect(Boolean singleSelect) {
		this.singleSelect = singleSelect;
	}

	@Variable
	public String getSelectRow() {
		return selectRow;
	}
	
	@Description("selectRow : Function, on row selected.")
	public void setSelectRow(String selectRow) {
		this.selectRow = selectRow;
	}

}
