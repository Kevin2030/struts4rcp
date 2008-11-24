package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("An Action is a piece of reusable functionality that can be abstracted out of any particular component so that it can be usefully shared among multiple components. Actions let you share handlers, configuration options and UI updates across any components that support the Action interface (primarily Ext.Toolbar, Ext.Button and Ext.menu.Menu components)."
		+ "Aside from supporting the config object interface, any component that needs to use Actions must also support the following method list, as these will be called as needed by the Action class: setText(string), setIconCls(string), setDisabled(boolean), setVisible(boolean) and setHandler(function)."
		+ "Example usage:"
		+ "// Define the shared action.  Each component below will have the same"
		+ "// display text and icon, and will display the same message on click."
		+ "var action = new Ext.Action({"
		+ "    text: 'Do something',"
		+ "    handler: function(){"
		+ "        Ext.Msg.alert('Click', 'You did something.');"
		+ "    },"
		+ "    iconCls: 'do-something'"
		+ "});"
		+ "var panel = new Ext.Panel({"
		+ "    title: 'Actions',"
		+ "    width:500,"
		+ "    height:300,"
		+ "    tbar: ["
		+ "        // Add the action directly to a toolbar as a menu button"
		+ "        action, {"
		+ "            text: 'Action Menu',"
		+ "            // Add the action to a menu as a text item"
		+ "            menu: [action]"
		+ "        }"
		+ "    ],"
		+ "    items: ["
		+ "        // Add the action to the panel body as a standard button"
		+ "        new Ext.Button(action)"
		+ "    ],"
		+ "    renderTo: Ext.getBody()"
		+ "});"
		+ "// Change the text for all components using the action"
		+ "action.setText('Something else');")
public class Action extends ComponentTag {
	private Boolean disabled;
	private String handler;
	private Boolean hidden;
	private String iconCls;
	private String scope;
	private String text;

	public Boolean isDisabled() {
		return disabled;
	}

	@Description("disabled : Boolean \n"
			+ "True to disable all components using this action, false to enable them (defaults to false). ")
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	@Variable
	public String getHandler() {
		return handler;
	}

	@Description("handler : Function \n"
			+ "The function that will be invoked by each component tied to this action when the component's primary event is triggered (defaults to undefined). ")
	public void setHandler(String handler) {
		this.handler = handler;
	}

	public Boolean isHidden() {
		return hidden;
	}

	@Description("hidden : Boolean \n"
			+ "True to hide all components using this action, false to show them (defaults to false). ")
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String getIconCls() {
		return iconCls;
	}

	@Description("iconCls : String \n"
			+ "The icon CSS class for all components using this action (defaults to ''). The class should supply a background image that will be used as the icon image. ")
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getScope() {
		return scope;
	}

	@Description("scope : Object \n"
			+ "The scope in which the handler function will execute. ")
	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getText() {
		return text;
	}

	@Description("text : String \n"
			+ "The text to set for all components using this action (defaults to ''). ")
	public void setText(String text) {
		this.text = text;
	}

}
