package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

@Description("Supplies the functionality to do 'actions' on forms and initialize Ext.form.Field types on existing markup."
		+ "By default, Ext Forms are submitted through Ajax, using Ext.form.Action. To enable normal browser submission of an Ext Form, override the Form's onSubmit, and submit methods:"
		+ "var myForm = new Ext.form.BasicForm('form-el-id', {"
		+ "        onSubmit: Ext.emptyFn,"
		+ "        submit: function() {"
		+ "            this.getEl().dom.submit();" + "        }" + "    });")
public class BasicForm extends Observable {
	private String baseParams;
	private String errorReader;
	private Boolean fileUpload;
	private String method;
	private String reader;
	private Integer timeout;
	private Boolean trackResetOnLoad;
	private String url;

	@Variable
	public String getBaseParams() {
		return baseParams;
	}

	@Description("baseParams : Object"
			+ "Parameters to pass with all requests. e.g. baseParams: {id: '123', foo: 'bar'}. ")
	public void setBaseParams(String baseParams) {
		this.baseParams = baseParams;
	}

	@Variable
	public String getErrorReader() {
		return errorReader;
	}

	@Description("errorReader : DataReader"
			+ "An Ext.data.DataReader (e.g. Ext.data.XmlReader) to be used to read data when reading validation errors on 'submit' actions. This is completely optional as there is built-in support for processing JSON. ")
	public void setErrorReader(String errorReader) {
		this.errorReader = errorReader;
	}

	public Boolean isFileUpload() {
		return fileUpload;
	}

	@Description("fileUpload : Boolean"
			+ "Set to true if this form is a file upload. ")
	public void setFileUpload(Boolean fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getMethod() {
		return method;
	}

	@Description("method : String"
			+ "The request method to use (GET or POST) for form actions if one isn't supplied in the action options. ")
	public void setMethod(String method) {
		this.method = method;
	}

	@Variable
	public String getReader() {
		return reader;
	}

	@Description("reader : DataReader"
			+ "An Ext.data.DataReader (e.g. Ext.data.XmlReader) to be used to read data when executing 'load' actions. This is optional as there is built-in support for processing JSON. ")
	public void setReader(String reader) {
		this.reader = reader;
	}

	public Integer getTimeout() {
		return timeout;
	}

	@Description("timeout : Number"
			+ "Timeout for form actions in seconds (default is 30 seconds). ")
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Boolean isTrackResetOnLoad() {
		return trackResetOnLoad;
	}

	@Description("trackResetOnLoad : Boolean"
			+ "If set to true, form.reset() resets to the last loaded or setValues() data instead of when the form was first created. ")
	public void setTrackResetOnLoad(Boolean trackResetOnLoad) {
		this.trackResetOnLoad = trackResetOnLoad;
	}

	public String getUrl() {
		return url;
	}

	@Description("url : String"
			+ "The URL to use for form actions if one isn't supplied in the action options. ")
	public void setUrl(String url) {
		this.url = url;
	}

}
