package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.ext.data.Connection;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("Global Ajax request class. Provides a simple way to make Ajax requests with maximum flexibility. Example usage:"
		+ "// Basic request"
		+ "Ext.Ajax.request({"
		+ "   url: 'foo.php',"
		+ "   success: someFn,"
		+ "   failure: otherFn,"
		+ "   headers: {"
		+ "       'my-header': 'foo'"
		+ "   },"
		+ "   params: { foo: 'bar' }"
		+ "});"
		+ "// Simple ajax form submission"
		+ "Ext.Ajax.request({"
		+ "    form: 'some-form',"
		+ "    params: 'foo=bar'"
		+ "});"
		+ "// Default headers to pass in every request"
		+ "Ext.Ajax.defaultHeaders = {"
		+ "    'Powered-By': 'Ext'"
		+ "};"
		+ "// Global Ajax events can be handled on every request!"
		+ "Ext.Ajax.on('beforerequest', this.showSpinner, this);")
public class Ajax extends Connection {

}
