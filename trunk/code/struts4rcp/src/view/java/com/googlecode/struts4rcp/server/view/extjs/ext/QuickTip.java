package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("A specialized tooltip class for tooltips that can be specified in markup and automatically managed by the global Ext.QuickTips instance. See the QuickTips class header for additional usage details and examples. ")
public class QuickTip extends ToolTip {
	private Boolean interceptTitles;

	public Boolean isInterceptTitles() {
		return interceptTitles;
	}

	@Description("interceptTitles : Boolean \n"
			+ "True to automatically use the element's DOM title value if available (defaults to false). ")
	public void setInterceptTitles(Boolean interceptTitles) {
		this.interceptTitles = interceptTitles;
	}

}
