package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

@Description("The subclasses of this class provide actions to perform upon Forms."
		+ "Instances of this class are only created by a Form when the Form needs to perform an action such as submit or load. The Configuration options listed for this class are set through the Form's action methods: submit, load and doAction."
		+ "The instance of Action which performed the action is passed to the success and failure callbacks of the Form's action methods (submit, load and doAction), and to the actioncomplete and actionfailed event handlers. ")
public abstract class Action extends ComponentTag {
	private String failure;
	private String method;
	private String params;
	private String scope;
	private String success;
	private String url;
	private String waitMsg;
	private String waitTitle;

	public static class Load extends Action {

	}

	public static class Submit extends Action {
		private Boolean clientValidation;

		public Boolean isClientValidation() {
			return clientValidation;
		}

		public void setClientValidation(Boolean clientValidation) {
			this.clientValidation = clientValidation;
		}
	}

	@Variable
	public String getFailure() {
		if (failure == null || failure.length () == 0) {
			failure = "function(form, action){Ext.MessageBox.alert('ERROR',action.result.error);}";
		}
		return failure;
	}

	@Description("failure : Function"
			+ "The function to call when a failure packet was recieved, or when an error ocurred in the Ajax communication. The func..."
			+ "The function to call when a failure packet was recieved, or when an error ocurred in the Ajax communication. The function is passed the following parameters:"
			+ "form : Ext.form.BasicForm"
			+ "The form that requested the action"
			+ "action : Ext.form.Action"
			+ "The Action class. If an Ajax error ocurred, the failure type will be in failureType. The result property of this object may be examined to perform custom postprocessing.")
	public void setFailure(String failure) {
		this.failure = failure;
	}

	public String getMethod() {
		return method;
	}

	@Description("method : String "
			+ "The HTTP method to use to access the requested URL. Defaults to the Ext.form.BasicForm's method, or if that is not specified, the underlying DOM form's method. ")
	public void setMethod(String method) {
		this.method = method;
	}

	@Variable
	public String getParams() {
		return params;
	}

	@Description("params : Mixed "
			+ "Extra parameter values to pass. These are added to the Form's Ext.form.BasicForm.baseParams and passed to the specified URL along with the Form's input fields. ")
	public void setParams(String params) {
		this.params = params;
	}

	@Variable
	public String getScope() {
		return scope;
	}

	@Description("scope : Object"
			+ "The scope in which to call the callback functions (The this reference for the callback functions). ")
	public void setScope(String scope) {
		this.scope = scope;
	}

	@Variable
	public String getSuccess() {
		return success;
	}

	@Description("success : Function "
			+ "The function to call when a valid success return packet is recieved. The function is passed the following parameters:..."
			+ "The function to call when a valid success return packet is recieved. The function is passed the following parameters:"
			+ "form : Ext.form.BasicForm"
			+ "The form that requested the action"
			+ "action : Ext.form.Action"
			+ "The Action class. The result property of this object may be examined to perform custom postprocessing.")
	public void setSuccess(String success) {
		this.success = success;
	}

	public String getUrl() {
		return url;
	}

	@Description("url : String" + "The URL that the Action is to invoke.")
	public void setUrl(String url) {
		this.url = url;
	}

	public String getWaitMsg() {
		return waitMsg;
	}

	@Description("waitMsg : String"
			+ "The message to be displayed by a call to Ext.MessageBox.wait during the time the action is being processed. ")
	public void setWaitMsg(String waitMsg) {
		this.waitMsg = waitMsg;
	}

	public String getWaitTitle() {
		return waitTitle;
	}

	@Description("waitTitle : String"
			+ "The title to be displayed by a call to Ext.MessageBox.wait during the time the action is being processed. ")
	public void setWaitTitle(String waitTitle) {
		this.waitTitle = waitTitle;
	}

}
