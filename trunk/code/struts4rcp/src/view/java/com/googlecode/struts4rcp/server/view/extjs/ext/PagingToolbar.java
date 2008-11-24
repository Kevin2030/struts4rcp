package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("A specialized toolbar that is bound to a Ext.data.Store and provides automatic paging controls. ")
public class PagingToolbar extends Toolbar {

	@Override
	protected String getDefaultKey() {
		return "tbar";
	}

	private Boolean displayInfo;
	private String displayMsg;
	private String emptyMsg;
	private Integer pageSize;
	private String store;

	public Boolean isDisplayInfo() {
		return displayInfo;
	}

	@Description("displayInfo : Boolean \n"
			+ "True to display the displayMsg (defaults to false) ")
	public void setDisplayInfo(Boolean displayInfo) {
		this.displayInfo = displayInfo;
	}

	public String getDisplayMsg() {
		return displayMsg;
	}

	@Description("displayMsg : String \n"
			+ "The paging status message to display (defaults to 'Displaying {0} - {1} of {2}'). Note that this string is formatted using the braced numbers 0-2 as tokens that are replaced by the values for start, end and total respectively. These tokens should be preserved when overriding this string if showing those values is desired. ")
	public void setDisplayMsg(String displayMsg) {
		this.displayMsg = displayMsg;
	}

	public String getEmptyMsg() {
		return emptyMsg;
	}

	@Description("emptyMsg : String \n"
			+ "The message to display when no records are found (defaults to 'No data to display') ")
	public void setEmptyMsg(String emptyMsg) {
		this.emptyMsg = emptyMsg;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	@Description("pageSize : Number \n"
			+ "The number of records to display per page (defaults to 20) ")
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Variable
	public String getStore() {
		return store;
	}

	@Description("store : Ext.data.Store \n"
			+ "The Ext.data.Store the paging toolbar should use as its data source (required). ")
	public void setStore(String store) {
		this.store = store;
	}
}
