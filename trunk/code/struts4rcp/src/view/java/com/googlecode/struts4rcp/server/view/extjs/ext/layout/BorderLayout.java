package com.googlecode.struts4rcp.server.view.extjs.ext.layout;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

@Description("This is a specialized type of BorderLayout region that has a built-in Ext.SplitBar for user resizing of regions. ")
public class BorderLayout extends ContainerLayout {
	public static class Region {
		private Boolean animFloat;
		private Boolean autoHide;
		private String cmargins;
		private String collapseMode;
		private Boolean collapsible;
		private Boolean floatable;
		private String margins;
		private Integer minHeight;
		private Integer minWidth;
		private Boolean split;

		public Boolean isAnimFloat() {
			return animFloat;
		}

		@Description("animFloat : Boolean \n"
				+ "When a collapsed region's bar is clicked, the region's panel will be displayed as a floated panel that will close again once the user mouses out of that panel (or clicks out if autoHide = false). Setting animFloat to false will prevent the open and close of these floated panels from being animated (defaults to true)")
		public void setAnimFloat(Boolean animFloat) {
			this.animFloat = animFloat;
		}

		public Boolean isAutoHide() {
			return autoHide;
		}

		@Description("autoHide : Boolean \n"
				+ "When a collapsed region's bar is clicked, the region's panel will be displayed as a floated panel. If autoHide is true, the panel will automatically hide after the user mouses out of the panel. If autoHide is false, the panel will continue to display until the user clicks outside of the panel (defaults to true). ")
		public void setAutoHide(Boolean autoHide) {
			this.autoHide = autoHide;
		}

		@Variable
		public String getCmargins() {
			return cmargins;
		}

		@Description("cmargins : Object \n"
				+ "An object containing margins to apply to the region's collapsed element in the format {left: (left margin), top: (top margin), right: (right margin), bottom: (bottom margin)} ")
		public void setCmargins(String cmargins) {
			this.cmargins = cmargins;
		}

		public String getCollapseMode() {
			return collapseMode;
		}

		@Description("collapseMode : String \n"
				+ "By default, collapsible regions are collapsed by clicking the expand/collapse tool button that renders into the region's title bar. Optionally, when collapseMode is set to 'mini' the region's split bar will also display a small collapse button in the center of the bar. In 'mini' mode the region will collapse to a thinner bar than in normal mode. By default collapseMode is undefined, and the only two supported values are undefined and 'mini'. Note that if a collapsible region does not have a title bar, then collapseMode must be set to 'mini' in order for the region to be collapsible by the user as the tool button will not be rendered. ")
		public void setCollapseMode(String collapseMode) {
			this.collapseMode = collapseMode;
		}

		public Boolean isCollapsible() {
			return collapsible;
		}

		@Description("collapsible : Boolean \n"
				+ "True to allow the user to collapse this region (defaults to false). If true, an expand/collapse tool button will automatically be rendered into the title bar of the region, otherwise the button will not be shown. Note that a title bar is required to display the toggle button -- if no region title is specified, the region will only be collapsible if collapseMode is set to 'mini'. ")
		public void setCollapsible(Boolean collapsible) {
			this.collapsible = collapsible;
		}

		public Boolean isFloatable() {
			return floatable;
		}

		@Description("floatable : Boolean \n"
				+ "True to allow clicking a collapsed region's bar to display the region's panel floated above the layout, false to force the user to fully expand a collapsed region by clicking the expand button to see it again (defaults to true). ")
		public void setFloatable(Boolean floatable) {
			this.floatable = floatable;
		}

		@Variable
		public String getMargins() {
			return margins;
		}

		@Description("margins : Object \n"
				+ "An object containing margins to apply to the region in the format {left: (left margin), top: (top margin), right: (right margin), bottom: (bottom margin)} ")
		public void setMargins(String margins) {
			this.margins = margins;
		}

		public Integer getMinHeight() {
			return minHeight;
		}

		@Description("minHeight : Number \n"
				+ "The minimum allowable height in pixels for this region (defaults to 50) ")
		public void setMinHeight(Integer minHeight) {
			this.minHeight = minHeight;
		}

		public Integer getMinWidth() {
			return minWidth;
		}

		@Description("minWidth : Number \n"
				+ "The minimum allowable width in pixels for this region (defaults to 50) ")
		public void setMinWidth(Integer minWidth) {
			this.minWidth = minWidth;
		}

		public Boolean isSplit() {
			return split;
		}

		@Description("split : Boolean \n"
				+ "True to display a Ext.SplitBar between this region and its neighbor, allowing the user to resize the regions dynamically (defaults to false). When split = true, it is common to specify a minSize and maxSize for the region. ")
		public void setSplit(Boolean split) {
			this.split = split;
		}
	}

	public static class SplitRegion extends Region {
		private String collapsibleSplitTip;
		private String splitTip;
		private Boolean useSplitTips;

		public String getCollapsibleSplitTip() {
			return collapsibleSplitTip;
		}

		public void setCollapsibleSplitTip(String collapsibleSplitTip) {
			this.collapsibleSplitTip = collapsibleSplitTip;
		}

		public String getSplitTip() {
			return splitTip;
		}

		public void setSplitTip(String splitTip) {
			this.splitTip = splitTip;
		}

		public Boolean isUseSplitTips() {
			return useSplitTips;
		}

		@Description("")
		public void setUseSplitTips(Boolean useSplitTips) {
			this.useSplitTips = useSplitTips;
		}
	}
}
