package com.googlecode.struts4rcp.server.view.extjs.ext.tree;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Event;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

@Description("A TreeLoader provides for lazy loading of an Ext.tree.TreeNode's child nodes from a specified URL. The response must be a JavaScript Array definition whose elements are node definition objects. eg:"
		+ "[{"
		+ "        id: 1,"
		+ "        text: 'A leaf Node',"
		+ "        leaf: true"
		+ "    },{"
		+ "        id: 2,"
		+ "        text: 'A folder Node',"
		+ "        children: [{"
		+ "            id: 3,"
		+ "            text: 'A child Node',"
		+ "            leaf: true"
		+ "        }]"
		+ "   }]"
		+ "A server request is sent, and child nodes are loaded only when a node is expanded. The loading node's id is passed to the server under the parameter name 'node' to enable the server to produce the correct child nodes."
		+ "To pass extra parameters, an event handler may be attached to the 'beforeload' event, and the parameters specified in the TreeLoader's baseParams property: "
		+ "myTreeLoader.on('beforeload', function(treeLoader, node) {"
		+ "        this.baseParams.category = node.attributes.category;"
		+ "    }, this);"
		+ "< This would pass an HTTP parameter called 'category' to the server containing the value of the Node's 'category' attribute. ")
public class TreeLoader extends Observable {

	@Override
	protected String getDefaultKey() {
		return "loader";
	}

	@Override
	protected boolean isVarRequired() {
		return true;
	}

	@Override
	protected void afterEndTag() {
		if (handler != null && handler.length() > 0) {
			super.defineAfterScript(getVar() + ".on('load', function(loader, node){"
					+ "var childs = node.childNodes;"
					+ "for(var i=0; i < childs.length; i ++) {"
					+ "childs[i].on('click', " + handler
					+ ");}});");
		}
	}

	private String baseAttrs;
	private String baseParams;
	private Boolean clearOnLoad;
	private String dataUrl;
	private Boolean preloadChildren;
	private String requestMethod;
	private String uiProviders;

	// 事件
	private String beforeload;
	private String load;
	private String loadexception;

	// 扩展属性
	private String textField;
	private String idField;
	private String leafField;
	private String clsField;
	private String childrenField;
	private String handler;

	public String getUrl() {
		return this.getDataUrl();
	}

	@Description("url : String \n" + "Equivalent to dataUrl. ")
	public void setUrl(String url) {
		this.setDataUrl(url);
	}

	public String getDataUrl() {
		if (dataUrl == null)
			throw new RuntimeException("dataUrl必需设置!!!");
		String params = "textField=" + filter(textField)
						+ "&idField=" + filter(idField)
						+ "&leafField=" + filter(leafField)
						+ "&clsField=" + filter(clsField)
						+ "&childrenField=" + filter(childrenField);
		int i = dataUrl.indexOf('?');
		if (i == -1) {
			return dataUrl + "?" + params;
		} else {
			return dataUrl + "&" + params;
		}
	}

	@Description("")
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	@Variable
	public String getBaseAttrs() {
		return baseAttrs;
	}

	@Description("baseAttrs : Object \n"
			+ "(optional) An object containing attributes to be added to all nodes created by this loader. If the attributes sent by the server have an attribute in this object, they take priority. ")
	public void setBaseAttrs(String baseAttrs) {
		this.baseAttrs = baseAttrs;
	}

	@Variable
	public String getBaseParams() {
		return baseParams;
	}

	@Description("baseParams : Object \n"
			+ "(optional) An object containing properties which specify HTTP parameters to be passed to each request for child nodes. ")
	public void setBaseParams(String baseParams) {
		this.baseParams = baseParams;
	}

	public Boolean isClearOnLoad() {
		return clearOnLoad;
	}

	@Description("clearOnLoad : Boolean \n"
			+ "(optional) Default to true. Remove previously existing child nodes before loading. ")
	public void setClearOnLoad(Boolean clearOnLoad) {
		this.clearOnLoad = clearOnLoad;
	}

	public Boolean isPreloadChildren() {
		return preloadChildren;
	}

	@Description("preloadChildren : Boolean \n"
			+ "If set to true, the loader recursively loads 'children' attributes when doing the first load on nodes. ")
	public void setPreloadChildren(Boolean preloadChildren) {
		this.preloadChildren = preloadChildren;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	@Description("requestMethod : String \n"
			+ "The HTTP request method for loading data (defaults to 'POST'). ")
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	@Variable
	public String getUiProviders() {
		return uiProviders;
	}

	@Description("uiProviders : Object \n"
			+ "(optional) An object containing properties which specify custom Ext.tree.TreeNodeUI implementations. If the optional ...")
	public void setUiProviders(String uiProviders) {
		this.uiProviders = uiProviders;
	}

	protected String getTextField() {
		return textField;
	}

	public void setTextField(String textField) {
		this.textField = textField;
	}

	protected String getIdField() {
		return idField;
	}

	@Description("")
	public void setIdField(String idField) {
		this.idField = idField;
	}

	protected String getLeafField() {
		return leafField;
	}

	@Description("")
	public void setLeafField(String leafField) {
		this.leafField = leafField;
	}

	protected String getClsField() {
		return clsField;
	}

	@Description("")
	public void setClsField(String clsField) {
		this.clsField = clsField;
	}

	protected String getChildrenField() {
		return childrenField;
	}

	@Description("")
	public void setChildrenField(String childrenField) {
		this.childrenField = childrenField;
	}

	@Variable
	protected String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	@Event
	protected String getBeforeload() {
		return beforeload;
	}

	public void setBeforeload(String beforeload) {
		this.beforeload = beforeload;
	}

	@Event
	protected String getLoad() {
		return load;
	}

	public void setLoad(String load) {
		this.load = load;
	}

	@Event
	protected String getLoadexception() {
		return loadexception;
	}

	public void setLoadexception(String loadexception) {
		this.loadexception = loadexception;
	}

}
