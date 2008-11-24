package com.googlecode.struts4rcp.server.view.extjs.ext.dd;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("This class provides a container DD instance that proxies for multiple child node sources."
		+ "By default, this class requires that draggable child nodes are registered with Ext.dd.Registry. ")
public class DragZone extends DragSource {
	private Boolean containerScroll;
	private String hlColor;

	public Boolean isContainerScroll() {
		return containerScroll;
	}

	@Description("containerScroll : Boolean"
			+ "True to register this container with the Scrollmanager for auto scrolling during drag operations.")
	public void setContainerScroll(Boolean containerScroll) {
		this.containerScroll = containerScroll;
	}

	public String getHlColor() {
		return hlColor;
	}

	@Description("hlColor : String "
			+ "The color to use when visually highlighting the drag source in the afterRepair method after a failed drop (defaults to 'c3daf9' - light blue) ")
	public void setHlColor(String hlColor) {
		this.hlColor = hlColor;
	}
}
