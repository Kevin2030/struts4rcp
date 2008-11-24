package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

@Description("This is a utility class that can be passed into a Ext.grid.ColumnModel as a column config that provides an automatic row numbering column."
		+ "Usage:"
		+ "// This is a typical column config with the first column providing row numbers"
		+ " var colModel = new Ext.grid.ColumnModel(["
		+ "    new Ext.grid.RowNumberer(),"
		+ "    {header: 'Name', width: 80, sortable: true},"
		+ "    {header: 'Code', width: 50, sortable: true},"
		+ "    {header: 'Description', width: 200, sortable: true}" + " ]);")
public class RowNumberer extends ComponentTag {
	private String header;
	private Boolean sortable;
	private String width;

	public String getHeader() {
		return header;
	}

	@Description("header : String \n"
			+ "Any valid text or HTML fragment to display in the header cell for the row number column (defaults to ''). ")
	public void setHeader(String header) {
		this.header = header;
	}

	public Boolean isSortable() {
		return sortable;
	}

	@Description("sortable : Boolean \n"
			+ "True if the row number column is sortable (defaults to false). ")
	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

	@Variable
	public String getWidth() {
		return width;
	}

	@Description("width : Number \n"
			+ "The default width in pixels of the row number column (defaults to 23). ")
	public void setWidth(String width) {
		this.width = width;
	}
}
