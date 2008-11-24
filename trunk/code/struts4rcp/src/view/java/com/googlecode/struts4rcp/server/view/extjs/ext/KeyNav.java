package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("Provides a convenient wrapper for normalized keyboard navigation. KeyNav allows you to bind navigation keys to function calls that will get called when the keys are pressed, providing an easy way to implement custom navigation schemes for any UI component."
		+ "The following are all of the possible keys that can be implemented: enter, left, right, up, down, tab, esc, pageUp, pageDown, del, home, end. Usage:"
		+ "var nav = new Ext.KeyNav('my-element', {"
		+ "    'left' : function(e){"
		+ "        this.moveLeft(e.ctrlKey);"
		+ "    },"
		+ "    'right' : function(e){"
		+ "        this.moveRight(e.ctrlKey);"
		+ "    },"
		+ "    'enter' : function(e){"
		+ "        this.save();"
		+ "    },"
		+ "    scope : this" + "});")
public class KeyNav extends ComponentTag {
	private String defaultEventAction;
	private Boolean disabled;
	private Boolean forceKeyDown;

	public String getDefaultEventAction() {
		return defaultEventAction;
	}

	@Description("defaultEventAction : String \n"
			+ "The method to call on the Ext.EventObject after this KeyNav intercepts a key. Valid values are Ext.EventObject.stopEvent, Ext.EventObject.preventDefault and Ext.EventObject.stopPropagation (defaults to 'stopEvent') ")
	public void setDefaultEventAction(String defaultEventAction) {
		this.defaultEventAction = defaultEventAction;
	}

	public Boolean isDisabled() {
		return disabled;
	}

	@Description("disabled : Boolean \n"
			+ "True to disable this KeyNav instance (defaults to false) ")
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean isForceKeyDown() {
		return forceKeyDown;
	}

	@Description("forceKeyDown : Boolean \n"
			+ "Handle the keydown event instead of keypress (defaults to false). KeyNav automatically does this for IE since IE does not propagate special keys on keypress, but setting this to true will force other browsers to also handle keydown instead of keypress. ")
	public void setForceKeyDown(Boolean forceKeyDown) {
		this.forceKeyDown = forceKeyDown;
	}
}
