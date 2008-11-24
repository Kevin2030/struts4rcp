package com.googlecode.struts4rcp.server.view.extjs.ext.menu;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("A base class for all menu items that require menu-related functionality (like sub-menus) and are not static display items. Item extends the base functionality of Ext.menu.BaseItem by adding menu-specific activation and click handling. ")
public class Item extends BaseItem {
	private String href;
	private String hrefTarget;
	private String icon;
	private String iconCls;
	private String itemCls;
	private Integer showDelay;
	private String text;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getItemCls() {
		return itemCls;
	}

	public void setItemCls(String itemCls) {
		this.itemCls = itemCls;
	}

	public Integer getShowDelay() {
		return showDelay;
	}

	public void setShowDelay(Integer showDelay) {
		this.showDelay = showDelay;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
