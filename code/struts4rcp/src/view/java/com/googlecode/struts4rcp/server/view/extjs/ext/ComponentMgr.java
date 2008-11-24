package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Provides a registry of all Components (specifically subclasses of Ext.Component) on a page so that they can be easily accessed by component id (see Ext.getCmp)."
		+ "This object also provides a registry of available Component classes indexed by a mnemonic code known as the Component's Ext.Component.xtype. The xtype provides a way to avoid instantiating child Components when creating a full, nested config object for a complete Ext page."
		+ "A child Component may be specified simply as a config object as long as the correct xtype is specified so that if and when the Component needs rendering, the correct type can be looked up for lazy instantiation."
		+ "For a list of all available xtypes, see Ext.Component."
		+ "This class is a singleton and cannot be created directly. ")
public class ComponentMgr extends ComponentTag {

}
