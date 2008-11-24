package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

@Description("A custom selection model that renders a column of checkboxes that can be toggled to select or deselect rows.")
public class CheckboxSelectionModel extends RowSelectionModel {

	public static final String CHECKBOX_COLUMN_KEY = "____CHECKBOX_COLUMN_KEY____";

	@Override
	protected boolean isVarRequired() {
		return true;
	}

	@Override
	protected void afterEndTag() {
		super.setStatus(CHECKBOX_COLUMN_KEY, getVar());
	}

	private String header;
	private Boolean sortable;
	private String width;

	public String getHeader() {
		return header;
	}

	@Description("header : String "
			+ "Any valid text or HTML fragment to display in the header cell for the checkbox column (defaults to '<div class='x-grid3-hd-checker'> </div>'). The default CSS class of 'x-grid3-hd-checker' displays a checkbox in the header and provides support for automatic check all/none behavior on header click. This string can be replaced by any valid HTML fragment, including a simple text string (e.g., 'Select Rows'), but the automatic check all/none behavior will only work if the 'x-grid3-hd-checker' class is supplied. ")
	public void setHeader(String header) {
		this.header = header;
	}

	public Boolean isSortable() {
		return sortable;
	}

	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

	@Variable
	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
}
