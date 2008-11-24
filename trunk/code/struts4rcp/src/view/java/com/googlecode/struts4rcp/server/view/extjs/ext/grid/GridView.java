package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

@Description("This class encapsulates the user interface of an Ext.grid.GridPanel. Methods of this class may be used to access user interface elements to enable special display effects. Do not change the DOM structure of the user interface."
		+ "This class does not provide ways to manipulate the underlying data. The data model of a Grid is held in an Ext.data.Store.")
public class GridView extends Observable {
	private Boolean autoFill;
	private String emptyText;
	private Boolean enableRowBody;
	private Boolean forceFit;

	public Boolean isAutoFill() {
		return autoFill;
	}

	@Description("autoFill : Boolean"
			+ "True to auto expand the columns to fit the grid when the grid is created. ")
	public void setAutoFill(Boolean autoFill) {
		this.autoFill = autoFill;
	}

	public String getEmptyText() {
		return emptyText;
	}

	@Description("emptyText : String"
			+ "Default text to display in the grid body when no rows are available (defaults to ''). ")
	public void setEmptyText(String emptyText) {
		this.emptyText = emptyText;
	}

	public Boolean isEnableRowBody() {
		return enableRowBody;
	}

	@Description("enableRowBody : Boolean "
			+ "True to add a second TR element per row that can be used to provide a row body that spans beneath the data row. Use the getRowClass method's rowParams config to customize the row body. ")
	public void setEnableRowBody(Boolean enableRowBody) {
		this.enableRowBody = enableRowBody;
	}

	public Boolean isForceFit() {
		return forceFit;
	}

	@Description("forceFit : Boolean"
			+ "True to auto expand/contract the size of the columns to fit the grid width and prevent horizontal scrolling. ")
	public void setForceFit(Boolean forceFit) {
		this.forceFit = forceFit;
	}
}
