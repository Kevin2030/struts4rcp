package com.googlecode.struts4rcp.server.view.extjs.ext.dd;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("A simple class that provides the basic implementation needed to make any element a drop target that can have draggable items dropped onto it. The drop has no effect until an implementation of notifyDrop is provided. ")
public class DropTarget extends DDTarget {
	private String ddGroup;
	private String dropAllowed;
	private String dropNotAllowed;
	private String overClass;

	public String getDdGroup() {
		return ddGroup;
	}

	@Description("ddGroup : String "
			+ "A named drag drop group to which this object belongs. If a group is specified, then this object will only interact with other drag drop objects in the same group (defaults to undefined). ")
	public void setDdGroup(String ddGroup) {
		this.ddGroup = ddGroup;
	}

	public String getDropAllowed() {
		return dropAllowed;
	}

	@Description("dropAllowed : String"
			+ "The CSS class returned to the drag source when drop is allowed (defaults to 'x-dd-drop-ok'). ")
	public void setDropAllowed(String dropAllowed) {
		this.dropAllowed = dropAllowed;
	}

	public String getDropNotAllowed() {
		return dropNotAllowed;
	}

	@Description("dropNotAllowed : String"
			+ "The CSS class returned to the drag source when drop is not allowed (defaults to 'x-dd-drop-nodrop'). ")
	public void setDropNotAllowed(String dropNotAllowed) {
		this.dropNotAllowed = dropNotAllowed;
	}

	public String getOverClass() {
		return overClass;
	}

	@Description("overClass : String"
			+ "The CSS class applied to the drop target element while the drag source is over it (defaults to ''). ")
	public void setOverClass(String overClass) {
		this.overClass = overClass;
	}
}
