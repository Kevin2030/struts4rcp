package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

import java.io.IOException;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

@Description("This is the default implementation of a ColumnModel used by the Grid. This class is initialized with an Array of column config objects. "
		+ "An individual column's config object defines the header string, the Ext.data.Record field the column draws its data from, an otional rendering function to provide customized data formatting, and the ability to apply a CSS class to all cells in a column through its id config option."
		+ "Usage:"
		+ "var colModel = new Ext.grid.ColumnModel(["
		+ "	{header: 'Ticker', width: 60, sortable: true},"
		+ "	{header: 'Company Name', width: 150, sortable: true},"
		+ "	{header: 'Market Cap.', width: 100, sortable: true},"
		+ "	{header: '$ Sales', width: 100, sortable: true, renderer: money},"
		+ "	{header: 'Employees', width: 100, sortable: true, resizable: false}"
		+ " ]);"
		+ "The config options listed for this class are options which may appear in each individual column definition. ")
public class ColumnModel extends Observable {

	@Override
	protected String getComponentBegin() {
		// 如果有定义CheckboxSelectionModel, 则在ColumnModel中自动注册
		String s = super.getStatus(CheckboxSelectionModel.CHECKBOX_COLUMN_KEY);
		super.removeStatus(CheckboxSelectionModel.CHECKBOX_COLUMN_KEY);
		StringBuilder buf = new StringBuilder();
		if (s != null) {
			try {
				super.appendComma(buf);
				return s + super.getComponentBegin();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return super.getComponentBegin();
	}

	@Override
	protected String getComponentName() {
		return super.getExtClassName();
	}

	@Override
	protected String getDefaultKey() {
		return "cm";
	}

	@Override
	protected boolean isList() {
		return true;
	}

	private String align;
	private String dataIndex;
	private String editor;
	private Boolean fixed;
	private String header;
	private Boolean hidden;
	private Boolean hideable;
	private String id;
	private String renderer;
	private Boolean resizable;
	private Boolean sortable;
	private String width;

	public String getAlign() {
		return align;
	}

	@Description("align : String \n"
			+ "(optional) Set the CSS text-align property of the column. Defaults to undefined. ")
	public void setAlign(String align) {
		this.align = align;
	}

	public String getDataIndex() {
		return dataIndex;
	}

	@Description("dataIndex : String \n"
			+ "(optional) The name of the field in the grid's Ext.data.Store's Ext.data.Record definition from which to draw the column's value. If not specified, the column's index is used as an index into the Record's data Array. ")
	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}

	@Variable
	public String getEditor() {
		return editor;
	}

	@Description("editor : Ext.form.Field \n"
			+ "(optional) The Ext.form.Field to use when editing values in this column if editing is supported by the grid. ")
	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Boolean isFixed() {
		return fixed;
	}

	@Description("fixed : Boolean \n"
			+ "(optional) True if the column width cannot be changed. Defaults to false. ")
	public void setFixed(Boolean fixed) {
		this.fixed = fixed;
	}

	public String getHeader() {
		return header;
	}

	@Description("header : String \n"
			+ "The header text to display in the Grid view. ")
	public void setHeader(String header) {
		this.header = header;
	}

	public Boolean isHidden() {
		return hidden;
	}

	@Description("hidden : Boolean \n"
			+ "(optional) True to hide the column. Defaults to false. ")
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Boolean isHideable() {
		return hideable;
	}

	@Description("hideable : Boolean \n"
			+ "(optional) Specify as false to prevent the user from hiding this column. Defaults to true. ")
	public void setHideable(Boolean hideable) {
		this.hideable = hideable;
	}

	public String getId() {
		return id;
	}

	@Description("id : String \n"
			+ "(optional) Defaults to the column's initial ordinal position. A name which identifies this column. The id is used to create a CSS class name which is applied to all table cells (including headers) in that column. The class name takes the form of"
			+ "x-grid3-td-id"
			+ "Header cells will also recieve this class name, but will also have the class x-grid3-hd")
	public void setId(String id) {
		this.id = id;
	}

	@Variable
	public String getRenderer() {
		return renderer;
	}

	@Description("renderer : Function \n"
			+ "(optional) A function used to generate HTML markup for a cell given the cell's data value. See setRenderer. If not specified, the default renderer uses the raw data value. ")
	public void setRenderer(String renderer) {
		this.renderer = renderer;
	}

	public Boolean isResizable() {
		return resizable;
	}

	@Description("resizable : Boolean \n"
			+ "(optional) False to disable column resizing. Defaults to true. ")
	public void setResizable(Boolean resizable) {
		this.resizable = resizable;
	}

	public Boolean isSortable() {
		return sortable;
	}

	@Description("sortable : Boolean \n"
			+ "(optional) True if sorting is to be allowed on this column. Defaults to the value of the defaultSortable property. Whether local/remote sorting is used is specified in Ext.data.Store.remoteSort")
	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

	@Variable
	public String getWidth() {
		return width;
	}

	@Description("width : Number \n"
			+ "(optional) The initial width in pixels of the column. Using this instead of Ext.grid.Grid.autoSizeColumns is more efficient. ")
	public void setWidth(String width) {
		this.width = width;
	}

}
