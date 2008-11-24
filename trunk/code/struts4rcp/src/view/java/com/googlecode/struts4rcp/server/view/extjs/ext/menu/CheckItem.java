package com.googlecode.struts4rcp.server.view.extjs.ext.menu;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Adds a menu item that contains a checkbox by default, but can also be part of a radio group. ")
public class CheckItem extends Item {
	private Boolean checked;
	private String group;
	private String groupClass;

	public Boolean isChecked() {
		return checked;
	}

	@Description("checked : Boolean \n"
			+ "True to initialize this checkbox as checked (defaults to false). Note that if this checkbox is part of a radio group (group = true) only the last item in the group that is initialized with checked = true will be rendered as checked. ")
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getGroup() {
		return group;
	}

	@Description("group : String \n"
			+ "All check items with the same group name will automatically be grouped into a single-select radio button group (defaults to '') ")
	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroupClass() {
		return groupClass;
	}

	@Description("groupClass : String \n "
			+ "The default CSS class to use for radio group check items (defaults to 'x-menu-group-item') ")
	public void setGroupClass(String groupClass) {
		this.groupClass = groupClass;
	}

}
