package com.googlecode.struts4rcp.server.view.extjs.ext.menu;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Adds a static text string to a menu, usually used as either a heading or group separator. ")
public class TextItem extends BaseItem {

	private static final long serialVersionUID = 1852800271292152526L;

	@Override
	protected String getComponentName() {
		return null;
	}
	
	private String itemCls;
	
	private String text;

	public String getItemCls() {
		return itemCls;
	}

	@Description("itemCls : String \n"
			+ "The default CSS class to use for text items (defaults to 'x-menu-text') ")
	public void setItemCls(String itemCls) {
		this.itemCls = itemCls;
	}

	public String getText() {
		return text;
	}

	@Description("text : String \n"
			+ "The text to display for this item (defaults to '') ")
	public void setText(String text) {
		this.text = text;
	}

}
