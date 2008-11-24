package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("Provides AJAX-style update for Element object." + "Usage:"
		+ "// Get it from a Ext.Element object" + "var el = Ext.get('foo');"
		+ "var mgr = el.getUpdater();" + "mgr.update({"
		+ "url: 'http://myserver.com/index.php'," + "params: {"
		+ "param1: 'foo'," + "param2: 'bar'" + "}" + "});" + "..."
		+ "mgr.formUpdate('myFormId', 'http://myserver.com/index.php');"
		+ "// or directly (returns the same Updater instance)"
		+ "var mgr = new Ext.Updater('myElementId');"
		+ "mgr.startAutoRefresh(60, 'http://myserver.com/index.php');"
		+ "mgr.on('update', myFcnNeedsToKnow);"
		+ "   // short handed call directly from the element object"
		+ "   Ext.get('foo').load({" + "        url: 'bar.php',"
		+ "        scripts: true," + "        params: 'param1=foo&param2=bar',"
		+ "        text: 'Loading Foo...'" + "   });")
public class Updater extends Observable {
	public static class BasicRenderer {

	}

	public static class defaults {

	}
}
