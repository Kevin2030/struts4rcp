package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("A standard tooltip implementation for providing additional information when hovering over a target element. ")
public class ToolTip extends Tip {
	private Boolean autoHide;
	private Integer dismissDelay;
	private Integer hideDelay;
	private String mouseOffset;
	private Integer showDelay;
	private String target;
	private Boolean trackMouse;

	public Boolean isAutoHide() {
		return autoHide;
	}

	@Description("autoHide : Boolean \n"
			+ "True to automatically hide the tooltip after the mouse exits the target element or after the dismissDelay has expired if set (defaults to true). If closable = true a close tool button will be rendered into the tooltip header. ")
	public void setAutoHide(Boolean autoHide) {
		this.autoHide = autoHide;
	}

	public Integer getDismissDelay() {
		return dismissDelay;
	}

	@Description("dismissDelay : Number \n"
			+ "Delay in milliseconds before the tooltip automatically hides (defaults to 5000). To disable automatic hiding, set dismissDelay = 0. ")
	public void setDismissDelay(Integer dismissDelay) {
		this.dismissDelay = dismissDelay;
	}

	public Integer getHideDelay() {
		return hideDelay;
	}

	@Description("hideDelay : Number \n"
			+ "Delay in milliseconds after the mouse exits the target element but before the tooltip actually hides (defaults to 200). Set to 0 for the tooltip to hide immediately. ")
	public void setHideDelay(Integer hideDelay) {
		this.hideDelay = hideDelay;
	}

	public String getMouseOffset() {
		return mouseOffset;
	}

	@Description("mouseOffset : Array \n"
			+ "An XY offset from the mouse position where the tooltip should be shown (defaults to [15,18]). ")
	public void setMouseOffset(String mouseOffset) {
		this.mouseOffset = mouseOffset;
	}

	public Integer getShowDelay() {
		return showDelay;
	}

	@Description("showDelay : Number \n"
			+ "Delay in milliseconds before the tooltip displays after the mouse enters the target element (defaults to 500) ")
	public void setShowDelay(Integer showDelay) {
		this.showDelay = showDelay;
	}

	@Variable
	public String getTarget() {
		return target;
	}

	@Description("target : Mixed \n"
			+ "The target HTMLElement, Ext.Element or id to associate with this tooltip. ")
	public void setTarget(String target) {
		this.target = target;
	}

	public Boolean isTrackMouse() {
		return trackMouse;
	}

	@Description("trackMouse : Boolean \n"
			+ "True to have the tooltip follow the mouse as it moves over the target element (defaults to false). ")
	public void setTrackMouse(Boolean trackMouse) {
		this.trackMouse = trackMouse;
	}
}
