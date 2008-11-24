package com.googlecode.struts4rcp.server.view.extjs.ext.tree;

import com.googlecode.struts4rcp.server.view.extjs.ext.dd.DropZone;

public class TreeDropZone extends DropZone {
	private String allowContainerDrop;
	private Boolean allowParentInsert;
	private String appendOnly;
	private String expandDelay;

	public String getAllowContainerDrop() {
		return allowContainerDrop;
	}

	public void setAllowContainerDrop(String allowContainerDrop) {
		this.allowContainerDrop = allowContainerDrop;
	}

	public Boolean isAllowParentInsert() {
		return allowParentInsert;
	}

	public void setAllowParentInsert(Boolean allowParentInsert) {
		this.allowParentInsert = allowParentInsert;
	}

	public String getAppendOnly() {
		return appendOnly;
	}

	public void setAppendOnly(String appendOnly) {
		this.appendOnly = appendOnly;
	}

	public String getExpandDelay() {
		return expandDelay;
	}

	public void setExpandDelay(String expandDelay) {
		this.expandDelay = expandDelay;
	}

}
