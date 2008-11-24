package com.googlecode.struts4rcp.server.view.extjs.ext.menu;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

@Description("A menu object. This is the container to which you add all other menu items. Menu can also serve a as a base class when you want a specialzed menu based off of another component (like Ext.menu.DateMenu for example). ")
public class Menu extends Observable {

	private static final long serialVersionUID = -4564740377918903573L;

	@Override
	protected String getDefaultKey() {
		return "menu";
	}

	private Boolean allowOtherMenus;

	private String defaultAlign;

	private String defaults;

	private String items;

	private Integer minWidth;

	private Boolean shadow;

	private String subMenuAlign;

	public Boolean isAllowOtherMenus() {
		return allowOtherMenus;
	}

	@Description("allowOtherMenus : Boolean \n"
			+ "True to allow multiple menus to be displayed at the same time (defaults to false) ")
	public void setAllowOtherMenus(Boolean allowOtherMenus) {
		this.allowOtherMenus = allowOtherMenus;
	}

	public String getDefaultAlign() {
		return defaultAlign;
	}

	@Description("defaultAlign : String \n"
			+ "The default {@link Ext.Element#alignTo) anchor position value for this menu relative to its element of origin (defaults to 'tl-bl?') ")
	public void setDefaultAlign(String defaultAlign) {
		this.defaultAlign = defaultAlign;
	}

	@Variable
	public String getDefaults() {
		return defaults;
	}

	@Description("defaults : Object \n"
			+ "A config object that will be applied to all items added to this container either via the items config or via the add method. The defaults config can contain any number of name/value property pairs to be added to each item, and should be valid for the types of items being added to the menu. ")
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}

	@Variable
	public String getItems() {
		return items;
	}

	@Description("items : Mixed\n"
			+ "An array of items to be added to this menu. See add for a list of valid item types. ")
	public void setItems(String items) {
		this.items = items;
	}

	public Integer getMinWidth() {
		return minWidth;
	}

	@Description("minWidth : Number \n"
			+ "The minimum width of the menu in pixels (defaults to 120) ")
	public void setMinWidth(Integer minWidth) {
		this.minWidth = minWidth;
	}

	public Boolean isShadow() {
		return shadow;
	}

	@Description("shadow : Boolean/String \n"
			+ "True or 'sides' for the default effect, 'frame' for 4-way shadow, and 'drop' for bottom-right shadow (defaults to 'sides') ")
	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}

	public String getSubMenuAlign() {
		return subMenuAlign;
	}

	@Description("subMenuAlign : String \n"
			+ "The Ext.Element.alignTo anchor position value to use for submenus of this menu (defaults to 'tl-tr?') ")
	public void setSubMenuAlign(String subMenuAlign) {
		this.subMenuAlign = subMenuAlign;
	}
}
