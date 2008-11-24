package com.googlecode.struts4rcp.server.view.extjs.ext.menu;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.Component;

@Description("The base class for all items that render into menus. BaseItem provides default rendering, activated state management and base configuration options shared by all menu components. ")
public class BaseItem extends Component {
	private String activeClass;
	private Boolean canActivate;
	private String handler;
	private Integer hideDelay;
	private Boolean hideOnClick;
	private String scope;

	public String getActiveClass() {
		return activeClass;
	}

	@Description("activeClass : String \n"
			+ "The CSS class to use when the item becomes activated (defaults to 'x-menu-item-active') ")
	public void setActiveClass(String activeClass) {
		this.activeClass = activeClass;
	}

	public Boolean isCanActivate() {
		return canActivate;
	}

	@Description("canActivate : Boolean \n"
			+ "True if this item can be visually activated (defaults to false) ")
	public void setCanActivate(Boolean canActivate) {
		this.canActivate = canActivate;
	}

	@Variable
	public String getHandler() {
		return handler;
	}

	@Description("handler : Function \n"
			+ "A function that will handle the click event of this menu item (defaults to undefined) ")
	public void setHandler(String handler) {
		this.handler = handler;
	}

	public Integer getHideDelay() {
		return hideDelay;
	}

	@Description("hideDelay : Number \n"
			+ "Length of time in milliseconds to wait before hiding after a click (defaults to 100) ")
	public void setHideDelay(Integer hideDelay) {
		this.hideDelay = hideDelay;
	}

	public Boolean isHideOnClick() {
		return hideOnClick;
	}

	@Description("hideOnClick : Boolean \n"
			+ "True to hide the containing menu after this item is clicked (defaults to true) ")
	public void setHideOnClick(Boolean hideOnClick) {
		this.hideOnClick = hideOnClick;
	}

	@Variable
	public String getScope() {
		return scope;
	}

	@Description("scope : Object \n"
			+ "The scope in which the handler function will be called. ")
	public void setScope(String scope) {
		this.scope = scope;
	}
}
