package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("Handles mapping keys to actions for an element. One key map can be used for multiple actions. The constructor accepts the same config object as defined by addBinding. If you bind a callback function to a KeyMap, anytime the KeyMap handles an expected key combination it will call the function with this signature (if the match is a multi-key combination the callback will still be called only once): (String key, Ext.EventObject e) A KeyMap can also handle a string representation of keys."
		+ "Usage: "
		+ "// map one key by key code"
		+ "var map = new Ext.KeyMap('my-element', {"
		+ "    key: 13, // or Ext.EventObject.ENTER"
		+ "    fn: myHandler,"
		+ "    scope: myObject"
		+ "});"
		+ "// map multiple keys to one action by string"
		+ "var map = new Ext.KeyMap('my-element', {"
		+ "    key: 'a\r\n\t',"
		+ "    fn: myHandler,"
		+ "    scope: myObject"
		+ "});"
		+ "// map multiple keys to multiple actions by strings and array of codes"
		+ "var map = new Ext.KeyMap('my-element', ["
		+ "    {"
		+ "        key: [10,13],"
		+ "        fn: function(){ alert('Return was pressed'); }"
		+ "    }, {"
		+ "        key: 'abc',"
		+ "        fn: function(){ alert('a, b or c was pressed'); }"
		+ "    }, {"
		+ "        key: '\t',"
		+ "        ctrl:true,"
		+ "        shift:true,"
		+ "        fn: function(){ alert('Control + shift + tab was pressed.'); }"
		+ "    }" + "]);" + "Note: A KeyMap starts enabled ")
public class KeyMap extends ComponentTag {

}
