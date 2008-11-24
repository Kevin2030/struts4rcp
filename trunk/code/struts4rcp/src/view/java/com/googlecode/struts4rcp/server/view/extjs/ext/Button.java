package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("Simple Button class ")
public class Button extends Component {

	private String clickEvent;
	private Boolean disabled;
	private Boolean enableToggle;
	private Boolean handleMouseEvents;
	private String handler;
	private Boolean hidden;
	private String icon;
	private String iconCls;
	private String menu;
	private String menuAlign;
	private Integer minWidth;
	private Boolean pressed;
	private Boolean repeat;
	private String scope;
	private Integer tabIndex;
	private String text;
	private String toggleGroup;
	private String tooltip;
	private String tooltipType;
	private String type;

	@Override
	public String getCls() { // 设置按钮默认样式
		String cls = super.getCls();
		if (cls == null || cls.length() == 0)
			return "x-btn-normal";
		return cls;
	}

	@Variable
	public String getClickEvent() {
		return clickEvent;
	}

	@Description("clickEvent : String \n"
			+ "The type of event to map to the button's event handler (defaults to 'click') ")
	public void setClickEvent(String clickEvent) {
		this.clickEvent = clickEvent;
	}

	public Boolean isDisabled() {
		return disabled;
	}

	@Description("disabled : Boolean \n"
			+ "True to start disabled (defaults to false) ")
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean isEnableToggle() {
		return enableToggle;
	}

	@Description("enableToggle : Boolean \n"
			+ "True to enable pressed/not pressed toggling (defaults to false) ")
	public void setEnableToggle(Boolean enableToggle) {
		this.enableToggle = enableToggle;
	}

	public Boolean isHandleMouseEvents() {
		return handleMouseEvents;
	}

	@Description("handleMouseEvents : Boolean \n"
			+ "False to disable visual cues on mouseover, mouseout and mousedown (defaults to true) ")
	public void setHandleMouseEvents(Boolean handleMouseEvents) {
		this.handleMouseEvents = handleMouseEvents;
	}

	@Variable
	public String getHandler() {
		return handler;
	}

	@Description("A function called when the button is clicked (can be used instead of click event) ")
	public void setHandler(String handler) {
		this.handler = handler;
	}

	public Boolean isHidden() {
		return hidden;
	}

	@Description("hidden : Boolean \n"
			+ "True to start hidden (defaults to false) ")
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String getIcon() {
		return icon;
	}

	@Description("icon : String \n"
			+ "The path to an image to display in the button (the image will be set as the background-image CSS property of the button by default, so if you want a mixed icon/text button, set cls:'x-btn-text-icon') ")
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconCls() {
		return iconCls;
	}

	@Description("iconCls : String \n"
			+ "A css class which sets a background image to be used as the icon for this button ")
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getMenu() {
		return menu;
	}

	@Description("menu : Mixed \n"
			+ "Standard menu attribute consisting of a reference to a menu object, a menu id or a menu config blob (defaults to undefined). ")
	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getMenuAlign() {
		return menuAlign;
	}

	@Description("menuAlign : String \n"
			+ "The position to align the menu to (see Ext.Element.alignTo for more details, defaults to 'tl-bl?'). ")
	public void setMenuAlign(String menuAlign) {
		this.menuAlign = menuAlign;
	}

	public Integer getMinWidth() {
		return minWidth;
	}

	@Description("minWidth : Number \n"
			+ "The minimum width for this button (used to give a set of buttons a common width) ")
	public void setMinWidth(Integer minWidth) {
		this.minWidth = minWidth;
	}

	public Boolean isPressed() {
		return pressed;
	}

	@Description("pressed : Boolean \n"
			+ "True to start pressed (only if enableToggle = true) ")
	public void setPressed(Boolean pressed) {
		this.pressed = pressed;
	}

	public Boolean isRepeat() {
		return repeat;
	}

	@Description("repeat : Boolean/Object \n"
			+ "True to repeat fire the click event while the mouse is down. This can also be an Ext.util.ClickRepeater config object (defaults to false). ")
	public void setRepeat(Boolean repeat) {
		this.repeat = repeat;
	}

	public String getScope() {
		return scope;
	}

	@Description("scope : Object \n" + "The scope of the handler ")
	public void setScope(String scope) {
		this.scope = scope;
	}

	public Integer getTabIndex() {
		return tabIndex;
	}

	@Description("tabIndex : Number \n"
			+ "Set a DOM tabIndex for this button (defaults to undefined) ")
	public void setTabIndex(Integer tabIndex) {
		this.tabIndex = tabIndex;
	}

	public String getText() {
		return text;
	}

	@Description("text : String \n" + "The button text ")
	public void setText(String text) {
		this.text = text;
	}

	public String getToggleGroup() {
		return toggleGroup;
	}

	@Description("toggleGroup : String \n"
			+ "The group this toggle button is a member of (only 1 per group can be pressed, only applies if enableToggle = true) ")
	public void setToggleGroup(String toggleGroup) {
		this.toggleGroup = toggleGroup;
	}

	public String getTooltip() {
		return tooltip;
	}

	@Description("tooltip : String/Object \n"
			+ "The tooltip for the button - can be a string or QuickTips config object ")
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getTooltipType() {
		return tooltipType;
	}

	@Description("tooltipType : String \n"
			+ "The type of tooltip to use. Either 'qtip' (default) for QuickTips or 'title' for title attribute. ")
	public void setTooltipType(String tooltipType) {
		this.tooltipType = tooltipType;
	}

	public String getType() {
		return type;
	}

	@Description("type : String \n"
			+ "submit, reset or button - defaults to 'button' ")
	public void setType(String type) {
		this.type = type;
	}

}
