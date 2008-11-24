package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

@Description("Panel is a container that has specific functionality and structural components that make it the perfect building block for application-oriented user interfaces. The Panel contains bottom and top toolbars, along with separate header, footer and body sections. It also provides built-in expandable and collapsible behavior, along with a variety of prebuilt tool buttons that can be wired up to provide other customized behavior. Panels can be easily dropped into any Container or layout, and the layout and rendering pipeline is completely managed by the framework. ")
public class Panel extends Container {
	private Boolean animCollapse;
	private String autoLoad;
	private Boolean autoScroll;
	private String baseCls;
	private String bbar;
	private Boolean bodyBorder;
	private String bodyStyle;
	private Boolean border;
	private String buttonAlign;
	private String buttons;
	private Boolean collapseFirst;
	private Boolean collapsed;
	private String collapsedCls;
	private Boolean collapsible;
	private String contentEl;
	private Boolean draggable;
	private String elements;
	private Boolean floating;
	private Boolean footer;
	private Boolean frame;
	private Boolean header;
	private Boolean headerAsText;
	private Boolean hideCollapseTool;
	private String html;
	private String iconCls;
	private String keys;
	private Boolean maskDisabled;
	private Integer minButtonWidth;
	private Boolean shadow;
	private Integer shadowOffset;
	private Boolean shim;
	private String tbar;
	private String title;
	private Boolean titleCollapse;
	private String tools;
	private String region;

	public Boolean isAnimCollapse() {
		return animCollapse;
	}

	@Description("animCollapse : Boolean \n"
			+ "True to animate the transition when the panel is collapsed, false to skip the animation (defaults to true if the Ext.Fx class is available, otherwise false). ")
	public void setAnimCollapse(Boolean animCollapse) {
		this.animCollapse = animCollapse;
	}

	public String getAutoLoad() {
		return autoLoad;
	}

	@Description("autoLoad : Object/String/Function \n"
			+ "A valid url spec according to the Updater Ext.Updater.update method. If autoLoad is not null, the panel will attempt to load its contents immediately upon render."
			+ "The URL will become the default URL for this panel's body element, so it may be refreshed at any time.")
	public void setAutoLoad(String autoLoad) {
		this.autoLoad = autoLoad;
	}

	public Boolean isAutoScroll() {
		return autoScroll;
	}

	@Description("autoScroll : Boolean \n"
			+ "True to use overflow:'auto' on the panel's body element and show scroll bars automatically when necessary, false to clip any overflowing content (defaults to false). ")
	public void setAutoScroll(Boolean autoScroll) {
		this.autoScroll = autoScroll;
	}

	public String getBaseCls() {
		return baseCls;
	}

	@Description("baseCls : String \n"
			+ "The base CSS class to apply to this panel's element (defaults to 'x-panel').")
	public void setBaseCls(String baseCls) {
		this.baseCls = baseCls;
	}

	@Variable
	public String getBbar() {
		return bbar;
	}

	@Description("bbar : Object/Array \n"
			+ "The bottom toolbar of the panel. This can be a Ext.Toolbar object, a toolbar config, or an array of buttons/button configs to be added to the toolbar. Note that this is not available as a property after render. To access the bottom toolbar after render, use getBottomToolbar. ")
	public void setBbar(String bbar) {
		this.bbar = bbar;
	}

	public Boolean isBodyBorder() {
		return bodyBorder;
	}

	@Description("bodyBorder : Boolean \n"
			+ "True to display an interior border on the body element of the panel, false to hide it (defaults to true). This only applies when border == true. If border == true and bodyBorder == false, the border will display as a 1px wide inset border, giving the entire body element an inset appearance. ")
	public void setBodyBorder(Boolean bodyBorder) {
		this.bodyBorder = bodyBorder;
	}

	public String getBodyStyle() {
		return bodyStyle;
	}

	@Description("bodyStyle : String/Object/Function \n"
			+ "Custom CSS styles to be applied to the body element in the format expected by Ext.Element.applyStyles (defaults to null). ")
	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}

	public Boolean isBorder() {
		return border;
	}

	@Description("border : Boolean \n"
			+ "True to display the borders of the panel's body element, false to hide them (defaults to true). By default, the border is a 2px wide inset border, but this can be further altered by setting bodyBorder to false. ")
	public void setBorder(Boolean border) {
		this.border = border;
	}

	public String getButtonAlign() {
		return buttonAlign;
	}

	@Description("buttonAlign : String \n"
			+ "The alignment of any buttons added to this panel. Valid values are 'right,' 'left' and 'center' (defaults to 'right'). ")
	public void setButtonAlign(String buttonAlign) {
		this.buttonAlign = buttonAlign;
	}

	@Variable
	public String getButtons() {
		return buttons;
	}

	@Description("buttons : Array \n"
			+ "An array of Ext.Button configs used to add buttons to the footer of this panel. ")
	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

	public Boolean isCollapseFirst() {
		return collapseFirst;
	}

	@Description("collapseFirst : Boolean \n"
			+ "True to make sure the collapse/expand toggle button always renders first (to the left of) any other tools in the panel's title bar, false to render it last (defaults to true). ")
	public void setCollapseFirst(Boolean collapseFirst) {
		this.collapseFirst = collapseFirst;
	}

	public Boolean isCollapsed() {
		return collapsed;
	}

	@Description("collapsed : Boolean \n"
			+ "True to render the panel collapsed, false to render it expanded (defaults to false). ")
	public void setCollapsed(Boolean collapsed) {
		this.collapsed = collapsed;
	}

	public String getCollapsedCls() {
		return collapsedCls;
	}

	@Description("collapsedCls : String \n"
			+ "A CSS class to add to the panel's element after it has been collapsed (defaults to 'x-panel-collapsed'). ")
	public void setCollapsedCls(String collapsedCls) {
		this.collapsedCls = collapsedCls;
	}

	public Boolean isCollapsible() {
		return collapsible;
	}

	@Description("collapsible : Boolean \n"
			+ "True to make the panel collapsible and have the expand/collapse toggle button automatically rendered into the header tool button area, false to keep the panel statically sized with no button (defaults to false). ")
	public void setCollapsible(Boolean collapsible) {
		this.collapsible = collapsible;
	}

	public String getContentEl() {
		return contentEl;
	}

	@Description("contentEl : String \n"
			+ "The id of an existing HTML node to use as the panel's body content (defaults to ''). ")
	public void setContentEl(String contentEl) {
		this.contentEl = contentEl;
	}

	public Boolean isDraggable() {
		return draggable;
	}

	@Description("draggable : Boolean \n"
			+ "True to enable dragging of this Panel (defaults to false). For custom drag/drop implementations, an Ext.Panel.DD config could also be passed in this config instead of true, although Ext.Panel.DD is an internal, undocumented class")
	public void setDraggable(Boolean draggable) {
		this.draggable = draggable;
	}

	public String getElements() {
		return elements;
	}

	@Description("elements : String \n"
			+ "A comma-delimited list of panel elements to initialize when the panel is rendered. Normally, this list will be generated automatically based on the items added to the panel at config time, but sometimes it might be useful to make sure a structural element is rendered even if not specified at config time (for example, you may want to add a button or toolbar dynamically after the panel has been rendered). Adding those elements to this list will allocate the required placeholders in the panel when it is rendered. Valid values are"
			+ "header " + "tbar (top bar)" + "body " + "bbar (bottom bar)"
			+ "footer " + "Defaults to 'body'. ")
	public void setElements(String elements) {
		this.elements = elements;
	}

	public Boolean isFloating() {
		return floating;
	}

	@Description("floating : Boolean \n"
			+ "True to float the panel (absolute position it with automatic shimming and shadow), false to display it inline where it is rendered (defaults to false). Note that by default, setting floating to true will cause the panel to display at negative offsets so that it is hidden -- because the panel is absolute positioned, the position must be set explicitly after render (e.g., myPanel.setPosition(100,100);). Also, when floating a panel you should always assign a fixed width, otherwise it will be auto width and will expand to fill to the right edge of the viewport. ")
	public void setFloating(Boolean floating) {
		this.floating = floating;
	}

	public Boolean isFooter() {
		return footer;
	}

	@Description("footer : Boolean \n"
			+ "True to create the footer element explicitly, false to skip creating it. By default, when footer is not specified, if one or more buttons have been added to the panel the footer will be created automatically, otherwise it will not. ")
	public void setFooter(Boolean footer) {
		this.footer = footer;
	}

	public Boolean isFrame() {
		return frame;
	}

	@Description("frame : Boolean \n"
			+ "True to render the panel with custom rounded borders, false to render with plain 1px square borders (defaults to false). ")
	public void setFrame(Boolean frame) {
		this.frame = frame;
	}

	public Boolean isHeader() {
		return header;
	}

	@Description("header : Boolean \n"
			+ "True to create the header element explicitly, false to skip creating it. By default, when header is not specified, if a title is set the header will be created automatically, otherwise it will not. If a title is set but header is explicitly set to false, the header will not be rendered. ")
	public void setHeader(Boolean header) {
		this.header = header;
	}

	public Boolean isHeaderAsText() {
		return headerAsText;
	}

	@Description("headerAsText : Boolean \n"
			+ "True to display the panel title in the header, false to hide it (defaults to true). ")
	public void setHeaderAsText(Boolean headerAsText) {
		this.headerAsText = headerAsText;
	}

	public Boolean isHideCollapseTool() {
		return hideCollapseTool;
	}

	@Description("hideCollapseTool : Boolean \n"
			+ "True to hide the expand/collapse toggle button when collapsible = true, false to display it (defaults to false). ")
	public void setHideCollapseTool(Boolean hideCollapseTool) {
		this.hideCollapseTool = hideCollapseTool;
	}

	public String getHtml() {
		return html;
	}

	@Description("html : String/Object \n"
			+ "An HTML fragment, or a DomHelper specification to use as the panel's body content (defaults to ''). ")
	public void setHtml(String html) {
		this.html = html;
	}

	public String getIconCls() {
		return iconCls;
	}

	@Description("iconCls : String \n"
			+ "A CSS class that will provide a background image to be used as the panel header icon (defaults to ''). ")
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Variable
	public String getKeys() {
		return keys;
	}

	@Description("keys : Object/Array \n"
			+ "A KeyMap config object (in the format expected by Ext.KeyMap.addBinding used to assign custom key handling to this panel (defaults to null). ")
	public void setKeys(String keys) {
		this.keys = keys;
	}

	public Boolean isMaskDisabled() {
		return maskDisabled;
	}

	@Description("maskDisabled : Boolean \n"
			+ "True to mask the panel when it is disabled, false to not mask it (defaults to true). Either way, the panel will always tell its contained elements to disable themselves when it is disabled, but masking the panel can provide an additional visual cue that the panel is disabled. ")
	public void setMaskDisabled(Boolean maskDisabled) {
		this.maskDisabled = maskDisabled;
	}

	public Integer getMinButtonWidth() {
		return minButtonWidth;
	}

	@Description("")
	public void setMinButtonWidth(Integer minButtonWidth) {
		this.minButtonWidth = minButtonWidth;
	}

	public Boolean isShadow() {
		return shadow;
	}

	@Description("minButtonWidth : Number \n"
			+ "Minimum width in pixels of all buttons in this panel (defaults to 75) ")
	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}

	public Integer getShadowOffset() {
		return shadowOffset;
	}

	@Description("shadowOffset : Number \n"
			+ "The number of pixels to offset the shadow if displayed (defaults to 4). Note that this option only applies when floating = true. ")
	public void setShadowOffset(Integer shadowOffset) {
		this.shadowOffset = shadowOffset;
	}

	public Boolean isShim() {
		return shim;
	}

	@Description("shim : Boolean \n"
			+ "False to disable the iframe shim in browsers which need one (defaults to true). Note that this option only applies when floating = true. ")
	public void setShim(Boolean shim) {
		this.shim = shim;
	}

	@Variable
	public String getTbar() {
		return tbar;
	}

	@Description("tbar : Object/Array \n"
			+ "The top toolbar of the panel. This can be either an Ext.Toolbar object or an array of buttons/button configs to be added to the toolbar. Note that this is not available as a property after render. To access the top toolbar after render, use getTopToolbar. ")
	public void setTbar(String tbar) {
		this.tbar = tbar;
	}

	public String getTitle() {
		return title;
	}

	@Description("title : String \n"
			+ "The title text to display in the panel header (defaults to ''). When a title is specified the header element will automatically be created and displayed unless header is explicitly set to false. If you don't want to specify a title at config time, but you may want one later, you must either specify a non-empty title (a blank space ' ' will do) or header:true so that the container element will get created. ")
	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean isTitleCollapse() {
		return titleCollapse;
	}

	@Description("titleCollapse : Boolean \n"
			+ "True to allow expanding and collapsing the panel (when collapsible = true) by clicking anywhere in the header bar, false to allow it only by clicking to tool button (defaults to false). ")
	public void setTitleCollapse(Boolean titleCollapse) {
		this.titleCollapse = titleCollapse;
	}

	@Variable
	public String getTools() {
		return tools;
	}

	@Description("tools : Array \n"
			+ "An array of tool button configs to be added to the header tool area. Each tool config may contain the following properties:"
			+ "id : String "
			+ "Required. The type of tool to create. Values may be"
			+ "toggle (Created by default when collapsible is true)"
			+ "close "
			+ "minimize"
			+ "maximize "
			+ "restore "
			+ "gear "
			+ "pin "
			+ "unpin "
			+ "right "
			+ "left "
			+ "up "
			+ "down"
			+ "refresh"
			+ "minus "
			+ "plus "
			+ "help "
			+ "search"
			+ "save "
			+ "handler : Function"
			+ "Required. The function to call when clicked. Arguments passed are:"
			+ "event : Ext.EventObject "
			+ "The click event."
			+ "toolEl : Ext.Element"
			+ "The tool Element."
			+ "Panel : Ext.Panel "
			+ "The host Panel"
			+ "scope : Object "
			+ "The scope in which to call the handler."
			+ "qtip : String/Object "
			+ "A tip string, or a config argument to Ext.QuickTip.register"
			+ "hidden : Boolean "
			+ "True to initially render hidden."
			+ "on : Object "
			+ "A listener config object specifiying event listeners in the format of an argument to addListener"
			+ "Example usage: "
			+ "tools:[{"
			+ "    id:'refresh',"
			+ "    // hidden:true,"
			+ "    handler: function(event, toolEl, panel){"
			+ "        // refresh logic"
			+ "    }"
			+ "}]"
			+ "Note that apart from the toggle tool which is provided when a panel is collapsible, these tools only provide the visual button. Any required functionality must be provided by adding handlers that implement the necessary behavior. ")
	public void setTools(String tools) {
		this.tools = tools;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}
