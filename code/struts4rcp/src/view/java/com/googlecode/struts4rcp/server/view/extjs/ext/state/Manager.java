package com.googlecode.struts4rcp.server.view.extjs.ext.state;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("This is the global state manager. By default all components that are 'state aware' check this class for state information if you don't pass them a custom state provider. In order for this class to be useful, it must be initialized with a provider when your application initializes. "
		+ "// in your initialization function"
		+ "init : function(){"
		+ "   Ext.state.Manager.setProvider(new Ext.state.CookieProvider());"
		+ "   ..."
		+ "   // supposed you have a 'Ext.BorderLayout' href='output/Ext.BorderLayout.html'>Ext.BorderLayout"
		+ "   var layout = new Ext.BorderLayout(...);"
		+ "   layout.restoreState();"
		+ "   // or a {Ext.BasicDialog}"
		+ "   var dialog = new Ext.BasicDialog(...);"
		+ "   dialog.restoreState();")
public class Manager extends ComponentTag {

}
