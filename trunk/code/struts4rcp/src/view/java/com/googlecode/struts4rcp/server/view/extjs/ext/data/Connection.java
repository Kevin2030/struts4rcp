package com.googlecode.struts4rcp.server.view.extjs.ext.data;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("The class encapsulates a connection to the page's originating domain, allowing requests to be made either to a configured URL, or to a URL specified at request time."
		+ "Requests made by this class are asynchronous, and will return immediately. No data from the server will be available to the statement immediately following the request call. To process returned data, use a callback in the request options object, or an event listener."
		+ "Note: If you are doing a file upload, you will not get a normal response object sent back to your callback or event handler. Since the upload is handled via in IFRAME, there is no XMLHttpRequest. The response object is created using the innerHTML of the IFRAME's document as the responseText property and, if present, the IFRAME's XML document as the responseXML property."
		+ "This means that a valid XML or HTML document must be returned. If JSON data is required, it is suggested that it be placed either inside a <textarea> in an HTML document and retrieved from the responseText using a regex, or inside a CDATA section in an XML document and retrieved from the responseXML using standard DOM methods. ")
public class Connection extends Observable {
	private Boolean autoAbort;
	private String defaultHeaders;
	private Boolean disableCaching;
	private String extraParams;
	private String method;
	private Integer timeout;
	private String url;

	public Boolean isAutoAbort() {
		return autoAbort;
	}

	@Description("autoAbort : Boolean \n(Optional) Whether this request should abort any pending requests. (defaults to false) ")
	public void setAutoAbort(Boolean autoAbort) {
		this.autoAbort = autoAbort;
	}

	@Variable
	public String getDefaultHeaders() {
		return defaultHeaders;
	}

	@Description("defaultHeaders : Object \n(Optional) An object containing request headers which are added to each request made by this object. (defaults to undefined) ")
	public void setDefaultHeaders(String defaultHeaders) {
		this.defaultHeaders = defaultHeaders;
	}

	public Boolean isDisableCaching() {
		return disableCaching;
	}

	@Description("disableCaching : Boolean \n(Optional) True to add a unique cache-buster param to GET requests. (defaults to true) ")
	public void setDisableCaching(Boolean disableCaching) {
		this.disableCaching = disableCaching;
	}

	@Variable
	public String getExtraParams() {
		return extraParams;
	}

	@Description("extraParams : Object \n(Optional) An object containing properties which are used as extra parameters to each request made by this object. (defaults to undefined) ")
	public void setExtraParams(String extraParams) {
		this.extraParams = extraParams;
	}

	public String getMethod() {
		return method;
	}

	@Description("method : String \n(Optional) The default HTTP method to be used for requests. (defaults to undefined; if not set but parms are present will use POST, otherwise GET)")
	public void setMethod(String method) {
		this.method = method;
	}

	public Integer getTimeout() {
		return timeout;
	}

	@Description("timeout : Number\n(Optional) The timeout in milliseconds to be used for requests. (defaults to 30000) ")
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getUrl() {
		return url;
	}

	@Description("url : String \n(Optional) The default URL to be used for requests to the server. (defaults to undefined) ")
	public void setUrl(String url) {
		this.url = url;
	}

}
