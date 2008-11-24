package com.googlecode.struts4rcp.server.view.extjs.ext.data;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("The Store class encapsulates a client side cache of Record objects which provide input data for Components such as the GridPanel, the ComboBox, or the DataView"
		+ "A Store object uses its configured implementation of DataProxy to access a data object unless you call loadData directly and pass in your data."
		+ "A Store object has no knowledge of the format of the data returned by the Proxy."
		+ "A Store object uses its configured implementation of DataReader to create Record instances from the data object. These Records are cached and made available through accessor functions.")
public class Store extends Observable {
	private Boolean autoLoad;
	private String baseParams;
	private String data;
	private String proxy;
	private Boolean pruneModifiedRecords;
	private String reader;
	private Boolean remoteSort;
	private String sortInfo;
	private String storeId;
	private String url;

	@Override
	protected boolean isVarRequired() {
		return true;
	}

	/*@Override
	protected void afterEndTag() {
		super.defineAfterScript(super.getVar()
						+ ".on('beforeload',function(store,opts){var srcCallback = opts.callback; opts.callback=function(r,o,s){if (s == false) {Ext.MessageBox.alert('ERROR','系统错误！');} if (srcCallback) {srcCallback(r,o,s);}};})");
	}*/

	@Override
	protected String getDefaultKey() {
		return "store";
	}

	public Boolean isAutoLoad() {
		return autoLoad;
	}

	@Description("autoLoad : Boolean/Object"
			+ "If passed, this store's load method is automatically called after creation with the autoLoad object ")
	public void setAutoLoad(Boolean autoLoad) {
		this.autoLoad = autoLoad;
	}

	@Variable
	public String getBaseParams() {
		return baseParams;
	}

	@Description("baseParams : Object"
			+ "An object containing properties which are to be sent as parameters on any HTTP request ")
	public void setBaseParams(String baseParams) {
		this.baseParams = baseParams;
	}

	@Variable
	public String getData() {
		return data;
	}

	@Description("data : Array"
			+ "Inline data to be loaded when the store is initialized. ")
	public void setData(String data) {
		this.data = data;
	}

	@Variable
	public String getProxy() {
		return proxy;
	}

	@Description("proxy : Ext.data.DataProxy"
			+ "The Proxy object which provides access to a data object. ")
	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public Boolean isPruneModifiedRecords() {
		return pruneModifiedRecords;
	}

	@Description("pruneModifiedRecords : boolean"
			+ "True to clear all modified record information each time the store is loaded or when a record is removed. (defaults to false). ")
	public void setPruneModifiedRecords(Boolean pruneModifiedRecords) {
		this.pruneModifiedRecords = pruneModifiedRecords;
	}

	@Variable
	public String getReader() {
		return reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}

	public Boolean isRemoteSort() {
		return remoteSort;
	}

	public void setRemoteSort(Boolean remoteSort) {
		this.remoteSort = remoteSort;
	}

	public String getSortInfo() {
		return sortInfo;
	}

	public void setSortInfo(String sortInfo) {
		this.sortInfo = sortInfo;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
