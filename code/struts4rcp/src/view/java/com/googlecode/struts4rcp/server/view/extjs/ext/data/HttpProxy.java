package com.googlecode.struts4rcp.server.view.extjs.ext.data;

import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("An implementation of Ext.data.DataProxy that reads a data object from a Connection object configured to reference a certain URL."
		+ "Note that this class cannot be used to retrieve data from a domain other than the domain from which the running page was served."
		+ "For cross-domain access to remote data, use a ScriptTagProxy."
		+ "Be aware that to enable the browser to parse an XML document, the server must set the Content-Type header in the HTTP response to 'text/xml'.")
public class HttpProxy extends DataProxy {

}
