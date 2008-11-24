package com.googlecode.struts4rcp.server.view.extjs.ext.util;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("Abstract base class that provides a common interface for publishing events. Subclasses are expected to to have a property 'events' with all the events defined."
		+ "For example: "
		+ "Employee = function(name){"
		+ "    this.name = name;"
		+ "    this.addEvents({"
		+ "        'fired' : true,"
		+ "        'quit' : true"
		+ "    });"
		+ " }" + " Ext.extend(Employee, Ext.util.Observable);")
public class Observable extends ComponentTag {
	private String listeners;

	@Variable
	public String getListeners() {
		return listeners;
	}

	@Description("listeners : Object \n"
			+ "A config object containing one or more event handlers to be added to this object during initialization. This should be a valid listeners config object as specified in the addListener example for attaching multiple handlers at once. ")
	public void setListeners(String listeners) {
		this.listeners = listeners;
	}
}
