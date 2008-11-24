package com.googlecode.struts4rcp.server.view.extjs.ext.data;

import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * @Description("")
 */
@Description("A specialized store implementation that provides for grouping records by one of the available fields. ")
public class GroupingStore extends Store {
	private String groupField;
	private Boolean groupOnSort;
	private Boolean remoteGroup;

	public String getGroupField() {
		return groupField;
	}

	@Description("groupField : String"
			+ "The field name by which to sort the store's data (defaults to ''). ")
	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}

	public Boolean isGroupOnSort() {
		return groupOnSort;
	}

	@Description("groupOnSort : Boolean "
			+ "True to sort the data on the grouping field when a grouping operation occurs, false to sort based on the existing sort info (defaults to false).")
	public void setGroupOnSort(Boolean groupOnSort) {
		this.groupOnSort = groupOnSort;
	}

	public Boolean isRemoteGroup() {
		return remoteGroup;
	}

	@Description("remoteGroup : Boolean"
			+ "True if the grouping should apply on the server side, false if it is local only (defaults to false). If the grouping is local, it can be applied immediately to the data. If it is remote, then it will simply act as a helper, automatically sending the grouping field name as the 'groupBy' param with each XHR call. ")
	public void setRemoteGroup(Boolean remoteGroup) {
		this.remoteGroup = remoteGroup;
	}
}
