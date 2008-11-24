package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

@Description("A combobox control with support for autocomplete, remote-loading, paging and many other features. ")
public class ComboBox extends TriggerField {
	private String allQuery;
	private Boolean autoCreate;
	private String displayField;
	private Boolean editable;
	private Boolean forceSelection;
	private Integer handleHeight;
	private String hiddenName;
	private Boolean lazyInit;
	private Boolean lazyRender;
	private String listAlign;
	private String listClass;
	private Integer listWidth;
	private String loadingText;
	private Integer maxHeight;
	private Integer minChars;
	private Integer minListWidth;
	private String mode;
	private Integer pageSize;
	private Integer queryDelay;
	private String queryParam;
	private Boolean resizable;
	private String selectedClass;
	private Boolean shadow;
	private String store;
	private String title;
	private String tpl;
	private String transform;
	private String triggerAction;
	private Boolean typeAhead;
	private String valueField;
	private String valueNotFoundText;

	// 扩展属性
	private String url;
	protected String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	// 以下属性引用自data.JsonReader及Store
	private String root;
	private String data;
	protected String getRoot() {
		return root;
	}
	@Description("root : String"
			+ "name of the property which contains the Array of row objects")
	public void setRoot(String root) {
		this.root = root;
	}
	@Variable
	protected String getData() {
		return data;
	}
	@Description("data : Array"
			+ "Inline data to be loaded when the store is initialized. ")
	public void setData(String data) {
		this.data = data;
	}

	@Variable
	public String getStore() {
		if (getClass() == ComboBox.class) { // 不处理子类
			String root = getRoot();
			if (root == null)
				root = "";
			else
				root = ",root:\"" + root + "\"";
			if (store == null || store.trim().length() == 0) {
				if ("remote".equalsIgnoreCase(this.getMode()))
					return "new Ext.data.Store({url:'" + getUrl() + "', reader: new Ext.data.JsonReader({fields: [{name: '" + getValueField() + "'},{name:'" + getDisplayField() + "'}]" + root + "})})";
				else
					return "new Ext.data.SimpleStore({data: [" + data + "], fields: ['" + getValueField() + "','" + getDisplayField() + "']})";
			}
		}
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getAllQuery() {
		return allQuery;
	}

	@Description("allQuery : String"
			+ "The text query to send to the server to return all records for the list with no filtering (defaults to '') ")
	public void setAllQuery(String allQuery) {
		this.allQuery = allQuery;
	}

	public Boolean isAutoCreate() {
		return autoCreate;
	}

	@Description("autoCreate : Boolean/Object"
			+ "A DomHelper element spec, or true for a default element spec (defaults to: {tag: 'input', type: 'text', size: '24', autocomplete: 'off'}) ")
	public void setAutoCreate(Boolean autoCreate) {
		this.autoCreate = autoCreate;
	}

	public String getDisplayField() {
		return displayField;
	}

	@Description("displayField : String"
			+ "The underlying data field name to bind to this ComboBox (defaults to undefined if mode = 'remote' or 'text' if transforming a select) ")
	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}

	public Boolean isEditable() {
		return editable;
	}

	@Description("editable : Boolean"
			+ "False to prevent the user from typing text directly into the field, just like a traditional select (defaults to true) ")
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean isForceSelection() {
		return forceSelection;
	}

	@Description("forceSelection : Boolean "
			+ "True to restrict the selected value to one of the values in the list, false to allow the user to set arbitrary text into the field (defaults to false) ")
	public void setForceSelection(Boolean forceSelection) {
		this.forceSelection = forceSelection;
	}

	public Integer getHandleHeight() {
		return handleHeight;
	}

	@Description("handleHeight : Number"
			+ "The height in pixels of the dropdown list resize handle if resizable = true (defaults to 8) ")
	public void setHandleHeight(Integer handleHeight) {
		this.handleHeight = handleHeight;
	}

	public String getHiddenName() {
		return hiddenName;
	}

	@Description("hiddenName : String"
			+ "If specified, a hidden form field with this name is dynamically generated to store the field's data value (defaults to the underlying DOM element's name). Required for the combo's value to automatically post during a form submission. ")
	public void setHiddenName(String hiddenName) {
		this.hiddenName = hiddenName;
	}

	public Boolean isLazyInit() {
		return lazyInit;
	}

	@Description("lazyInit : Boolean"
			+ "True to not initialize the list for this combo until the field is focused. (defaults to true) ")
	public void setLazyInit(Boolean lazyInit) {
		this.lazyInit = lazyInit;
	}

	public Boolean isLazyRender() {
		return lazyRender;
	}

	public void setLazyRender(Boolean lazyRender) {
		this.lazyRender = lazyRender;
	}

	public String getListAlign() {
		return listAlign;
	}

	public void setListAlign(String listAlign) {
		this.listAlign = listAlign;
	}

	public String getListClass() {
		return listClass;
	}

	public void setListClass(String listClass) {
		this.listClass = listClass;
	}

	public Integer getListWidth() {
		return listWidth;
	}

	public void setListWidth(Integer listWidth) {
		this.listWidth = listWidth;
	}

	public String getLoadingText() {
		return loadingText;
	}

	public void setLoadingText(String loadingText) {
		this.loadingText = loadingText;
	}

	public Integer getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(Integer maxHeight) {
		this.maxHeight = maxHeight;
	}

	public Integer getMinChars() {
		return minChars;
	}

	public void setMinChars(Integer minChars) {
		this.minChars = minChars;
	}

	public Integer getMinListWidth() {
		return minListWidth;
	}

	public void setMinListWidth(Integer minListWidth) {
		this.minListWidth = minListWidth;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getQueryDelay() {
		return queryDelay;
	}

	public void setQueryDelay(Integer queryDelay) {
		this.queryDelay = queryDelay;
	}

	@Variable
	public String getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

	public Boolean isResizable() {
		return resizable;
	}

	public void setResizable(Boolean resizable) {
		this.resizable = resizable;
	}

	public String getSelectedClass() {
		return selectedClass;
	}

	public void setSelectedClass(String selectedClass) {
		this.selectedClass = selectedClass;
	}

	public Boolean isShadow() {
		return shadow;
	}

	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTpl() {
		return tpl;
	}

	public void setTpl(String tpl) {
		this.tpl = tpl;
	}

	@Variable
	public String getTransform() {
		return transform;
	}

	public void setTransform(String transform) {
		this.transform = transform;
	}

	public String getTriggerAction() {
		return triggerAction;
	}

	public void setTriggerAction(String triggerAction) {
		this.triggerAction = triggerAction;
	}

	public Boolean isTypeAhead() {
		return typeAhead;
	}

	public void setTypeAhead(Boolean typeAhead) {
		this.typeAhead = typeAhead;
	}

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getValueNotFoundText() {
		return valueNotFoundText;
	}

	public void setValueNotFoundText(String valueNotFoundText) {
		this.valueNotFoundText = valueNotFoundText;
	}

}
