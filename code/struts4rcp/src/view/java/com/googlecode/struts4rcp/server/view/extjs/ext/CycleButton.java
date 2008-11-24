package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("A specialized SplitButton that contains a menu of Ext.menu.CheckItem elements. The button automatically cycles through each menu item on click, raising the button's change event (or calling the button's changeHandler function, if supplied) for the active menu item. Clicking on the arrow section of the button displays the dropdown menu just like a normal SplitButton. Example usage:"
		+ "var btn = new Ext.CycleButton({"
		+ "    showText: true,"
		+ "    prependText: 'View as ',"
		+ "    items: [{"
		+ "        text:'text only',"
		+ "        iconCls:'view-text',"
		+ "        checked:true"
		+ "    },{"
		+ "        text:'HTML',"
		+ "        iconCls:'view-html'"
		+ "    }],"
		+ "    changeHandler:function(btn, item){"
		+ "        Ext.Msg.alert('Change View', item.text);" + "    }" + "});")
public class CycleButton extends SplitButton {
	private String changeHandler;
	private String items;
	private String prependText;
	private Boolean showText;

	@Variable
	public String getChangeHandler() {
		return changeHandler;
	}

	@Description("changeHandler : Function \n"
			+ "A callback function that will be invoked each time the active menu item in the button's menu has changed. If this callback is not supplied, the SplitButton will instead fire the change event on active item change. The changeHandler function will be called with the following argument list: (SplitButton this, Ext.menu.CheckItem item) ")
	public void setChangeHandler(String changeHandler) {
		this.changeHandler = changeHandler;
	}

	@Variable
	public String getItems() {
		return items;
	}

	@Description("items : Array \n"
			+ "An array of Ext.menu.CheckItem config objects to be used when creating the button's menu items (e.g., {text:'Foo', iconCls:'foo-icon'}) ")
	public void setItems(String items) {
		this.items = items;
	}

	public String getPrependText() {
		return prependText;
	}

	@Description("prependText : String \n"
			+ "A static string to prepend before the active item's text when displayed as the button's text (only applies when showText = true, defaults to '') ")
	public void setPrependText(String prependText) {
		this.prependText = prependText;
	}

	public Boolean isShowText() {
		return showText;
	}

	@Description("showText : Boolean \n"
			+ "True to display the active item's text as the button text (defaults to false) ")
	public void setShowText(Boolean showText) {
		this.showText = showText;
	}
}
