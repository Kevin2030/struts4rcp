package com.googlecode.struts4rcp.server.view.extjs.ext.layout;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("This is a layout that contains multiple panels in an expandable accordion style such that only one panel can be open at any given time. Each panel has built-in support for expanding and collapsing. This class is intended to be extended or created via the layout:'accordion' Ext.Container.layout config, and should generally not need to be created directly via the new keyword."
		+ "Note that when creating a layout via config, the layout-specific config properties must be passed in via the Ext.Container.layoutConfig object which will then be applied internally to the layout. Example usage:"
		+ "		var accordion = new Ext.Panel({"
		+ "    title: 'Accordion Layout',"
		+ "    layout:'accordion',"
		+ "    defaults: {"
		+ "        // applied to each contained panel"
		+ "        bodyStyle: 'padding:15px'"
		+ "    },"
		+ "    layoutConfig: {"
		+ "        // layout-specific configs go here"
		+ "        titleCollapse: false,"
		+ "        animate: true,"
		+ "        activeOnTop: true"
		+ "    }"
		+ "    items: [{"
		+ "        title: 'Panel 1',"
		+ "        html: '<p>Panel content!</p>'"
		+ "    },{"
		+ "        title: 'Panel 2',"
		+ "        html: '<p>Panel content!</p>'"
		+ "    },{"
		+ "        title: 'Panel 3',"
		+ "        html: '<p>Panel content!</p>'"
		+ "    }]" + "});")
public class Accordion extends FitLayout {
	private Boolean activeOnTop;
	private Boolean animate;
	private Boolean autoWidth;
	private Boolean collapseFirst;
	private Boolean fill;
	private Boolean hideCollapseTool;
	private Boolean sequence;

	public Boolean isActiveOnTop() {
		return activeOnTop;
	}

	@Description("activeOnTop : Boolean \n "
			+ "True to swap the position of each panel as it is expanded so that it becomes the first item in the container, false to keep the panels in the rendered order. This is NOT compatible with 'animate:true' (defaults to false). ")
	public void setActiveOnTop(Boolean activeOnTop) {
		this.activeOnTop = activeOnTop;
	}

	public Boolean isAnimate() {
		return animate;
	}

	@Description("animate : Boolean \n"
			+ "True to slide the contained panels open and closed during expand/collapse using animation, false to open and close directly with no animation (defaults to false). Note: to defer to the specific config setting of each contained panel for this property, set this to undefined at the layout level. ")
	public void setAnimate(Boolean animate) {
		this.animate = animate;
	}

	public Boolean isAutoWidth() {
		return autoWidth;
	}

	@Description("autoWidth : Boolean \n"
			+ "True to set each contained item's width to 'auto', false to use the item's current width (defaults to true). ")
	public void setAutoWidth(Boolean autoWidth) {
		this.autoWidth = autoWidth;
	}

	public Boolean isCollapseFirst() {
		return collapseFirst;
	}

	@Description("collapseFirst : Boolean \n"
			+ "True to make sure the collapse/expand toggle button always renders first (to the left of) any other tools in the contained panels' title bars, false to render it last (defaults to false). ")
	public void setCollapseFirst(Boolean collapseFirst) {
		this.collapseFirst = collapseFirst;
	}

	public Boolean isFill() {
		return fill;
	}

	@Description("fill : Boolean \n"
			+ "True to adjust the active item's height to fill the available space in the container, false to use the item's current height, or auto height if not explicitly set (defaults to true). ")
	public void setFill(Boolean fill) {
		this.fill = fill;
	}

	public Boolean isHideCollapseTool() {
		return hideCollapseTool;
	}

	@Description("hideCollapseTool : Boolean \n"
			+ "True to hide the contained panels' collapse/expand toggle buttons, false to display them (defaults to false). When set to true, titleCollapse should be true also. ")
	public void setHideCollapseTool(Boolean hideCollapseTool) {
		this.hideCollapseTool = hideCollapseTool;
	}

	public Boolean isSequence() {
		return sequence;
	}

	@Description("sequence : Boolean \n"
			+ "Experimental. If animate is set to true, this will result in each animation running in sequence. ")
	public void setSequence(Boolean sequence) {
		this.sequence = sequence;
	}
}
