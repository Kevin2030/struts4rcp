package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

@Description("A specialized grid implementation intended to mimic the traditional property grid as typically seen in development IDEs. Each row in the grid represents a property of some object, and the data is stored as a set of name/value pairs in Ext.grid.PropertyRecords. Example usage: "
		+ "var grid = new Ext.grid.PropertyGrid({"
		+ "title: 'Properties Grid',"
		+ "autoHeight: true,"
		+ "width: 300,"
		+ "renderTo: 'grid-ct',"
		+ "source: {"
		+ "    '(name)': 'My Object',"
		+ "    'Created': new Date(Date.parse('10/15/2006')),"
		+ "    'Available': false,"
		+ "    'Version': .01,"
		+ "    'Description': 'A test object'" + "	}" + "});")
public class PropertyGrid extends EditorGridPanel {
	private String customEditors;
	private String source;

	@Variable
	public String getCustomEditors() {
		return customEditors;
	}

	@Description("An object containing name/value pairs of custom editor type definitions that allow the grid to support additional types of editable fields. By default, the grid supports strongly-typed editing of strings, dates, numbers and booleans using built-in form editors, but any custom type can be supported and associated with a custom input control by specifying a custom editor. The name of the editor type should correspond with the name of the property that will use the editor. Example usage:"
			+ "var grid = new Ext.grid.PropertyGrid({"
			+ "   ..."
			+ "   customEditors: {"
			+ "        'Start Time': new Ext.grid.GridEditor(new Ext.form.TimeField({selectOnFocus:true}))"
			+ "   },"
			+ "   source: {"
			+ "       'Start Time': '10:00 AM'"
			+ "   }" + "});")
	public void setCustomEditors(String customEditors) {
		this.customEditors = customEditors;
	}

	@Variable
	public String getSource() {
		return source;
	}

	@Description("source : Object"
			+ "A data object to use as the data source of the grid (see setSource for details). ")
	public void setSource(String source) {
		this.source = source;
	}
}
