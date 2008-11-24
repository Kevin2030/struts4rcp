package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("A mechanism for displaying data using custom layout templates and formatting. DataView uses an Ext.XTemplate as its internal templating mechanisma, and is bound to an Ext.data.Store so that as the data in the store changes the view is automatically updated to reflect the changes. The view also provides built-in behavior for many common events that can occur for its contained items including click, doubleclick, mouseover, mouseout, etc. as well as a built-in selection model. In order to use these features, an itemSelector config must be provided for the DataView to determine what nodes it will be working with."
		+ "The example below binds a DataView to a Ext.data.Store and renders it into an Ext.Panel."
		+ "var store = new Ext.data.JsonStore({"
		+ "    url: 'get-images.php',"
		+ "    root: 'images',"
		+ "    fields: ["
		+ "        'name', 'url',"
		+ "        {name:'size', type: 'float'},"
		+ "        {name:'lastmod', type:'date', dateFormat:'timestamp'}"
		+ "    ]"
		+ "});"
		+ "store.load();"
		+ "var tpl = new Ext.XTemplate("
		+ "    '<tpl for='.'>',"
		+ "        '<div class='thumb-wrap' id='{name}'>',"
		+ "        '<div class='thumb'><img src='{url}' title='{name}'></div>',"
		+ "        '<span class='x-editable'>{shortName}</span></div>',"
		+ "    '</tpl>',"
		+ "    '<div class='x-clear'></div>'"
		+ ");"
		+ "var panel = new Ext.Panel({"
		+ "    id:'images-view',"
		+ "    frame:true,"
		+ "    width:535,"
		+ "    autoHeight:true,"
		+ "    collapsible:true,"
		+ "    layout:'fit',"
		+ "    title:'Simple DataView',"
		+ "    items: new Ext.DataView({"
		+ "    	store: store,"
		+ "        tpl: tpl,"
		+ "        autoHeight:true,"
		+ "        multiSelect: true,"
		+ "        overClass:'x-view-over',"
		+ "        itemSelector:'div.thumb-wrap',"
		+ "emptyText: 'No images to display'"
		+ "    })"
		+ "});"
		+ "panel.render(document.body);")
public class DataView extends BoxComponent {
	private String emptyText;
	private String itemSelector;
	private String loadingText;
	private Boolean multiSelect;
	private String overClass;
	private String selectedClass;
	private Boolean simpleSelect;
	private Boolean singleSelect;
	private String store;
	private String tpl;

	public String getEmptyText() {
		return emptyText;
	}

	public void setEmptyText(String emptyText) {
		this.emptyText = emptyText;
	}

	@Variable
	public String getItemSelector() {
		return itemSelector;
	}

	public void setItemSelector(String itemSelector) {
		this.itemSelector = itemSelector;
	}

	public String getLoadingText() {
		return loadingText;
	}

	public void setLoadingText(String loadingText) {
		this.loadingText = loadingText;
	}

	public Boolean isMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(Boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	public String getOverClass() {
		return overClass;
	}

	public void setOverClass(String overClass) {
		this.overClass = overClass;
	}

	public String getSelectedClass() {
		return selectedClass;
	}

	public void setSelectedClass(String selectedClass) {
		this.selectedClass = selectedClass;
	}

	public Boolean isSimpleSelect() {
		return simpleSelect;
	}

	public void setSimpleSelect(Boolean simpleSelect) {
		this.simpleSelect = simpleSelect;
	}

	public Boolean isSingleSelect() {
		return singleSelect;
	}

	public void setSingleSelect(Boolean singleSelect) {
		this.singleSelect = singleSelect;
	}

	@Variable
	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getTpl() {
		return tpl;
	}

	public void setTpl(String tpl) {
		this.tpl = tpl;
	}

}
