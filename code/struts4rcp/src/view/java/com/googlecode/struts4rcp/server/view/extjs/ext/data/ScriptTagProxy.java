package com.googlecode.struts4rcp.server.view.extjs.ext.data;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("An implementation of Ext.data.DataProxy that reads a data object from a URL which may be in a domain other than the originating domain of the running page."
		+ "Note that if you are retrieving data from a page that is in a domain that is NOT the same as the originating domain of the running page, you must use this class, rather than HttpProxy."
		+ "The content passed back from a server resource requested by a ScriptTagProxy is executable JavaScript source code that is used as the source inside a <script> tag."
		+ "In order for the browser to process the returned data, the server must wrap the data object with a call to a callback function, the name of which is passed as a parameter by the ScriptTagProxy. Below is a Java example for a servlet which returns data for either a ScriptTagProxy, or an HttpProxy depending on whether the callback name was passed:"
		+ "boolean scriptTag = false;"
		+ "String cb = request.getParameter('callback');"
		+ "if (cb != null) {"
		+ "    scriptTag = true;"
		+ "    response.setContentType('text/javascript');"
		+ "} else {"
		+ "    response.setContentType('application/x-json');"
		+ "}"
		+ "Writer out = response.getWriter();"
		+ "if (scriptTag) {"
		+ "    out.write(cb + '('');"
		+ "}"
		+ "out.print(dataBlock.toJsonString());"
		+ "if (scriptTag) {"
		+ "out.write(')';');" + "}")
public class ScriptTagProxy extends DataProxy {
	private String callbackParam;
	private Boolean nocache;
	private Integer timeout;
	private String url;

	@Variable
	public String getCallbackParam() {
		return callbackParam;
	}

	@Description("callbackParam : String \n(Optional) The name of the parameter to pass to the server which tells the server the name of the callback function set up by the load call to process the returned data object. Defaults to 'callback'."
			+ "The server-side processing must read this parameter value, and generate javascript output which calls this named function passing the data object as its only parameter.")
	public void setCallbackParam(String callbackParam) {
		this.callbackParam = callbackParam;
	}

	public Boolean isNocache() {
		return nocache;
	}

	@Description("nocache : Boolean \n(Optional) Defaults to true. Disable cacheing by adding a unique parameter name to the request. ")
	public void setNocache(Boolean nocache) {
		this.nocache = nocache;
	}

	public Integer getTimeout() {
		return timeout;
	}

	@Description("timeout : Number \n(Optional) The number of milliseconds to wait for a response. Defaults to 30 seconds. ")
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getUrl() {
		return url;
	}

	@Description("url : String \n The URL from which to request the data object. ")
	public void setUrl(String url) {
		this.url = url;
	}

}
