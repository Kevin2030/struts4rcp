package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.Panel;

@Description("This class represents the primary interface of a component based grid control. "
		+ "Usage:"
		+ "var grid = new Ext.grid.GridPanel({"
		+ "    store: new Ext.data.Store({"
		+ "        reader: reader,"
		+ "        data: xg.dummyData"
		+ "    }),"
		+ "    columns: ["
		+ "        {id:'company', header: 'Company', width: 200, sortable: true, dataIndex: 'company'},"
		+ "        {header: 'Price', width: 120, sortable: true, renderer: Ext.util.Format.usMoney, dataIndex: 'price'},"
		+ "        {header: 'Change', width: 120, sortable: true, dataIndex: 'change'},"
		+ "        {header: '% Change', width: 120, sortable: true, dataIndex: 'pctChange'},"
		+ "        {header: 'Last Updated', width: 135, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'lastChange'}"
		+ "    ],"
		+ "    viewConfig: {"
		+ "        forceFit: true"
		+ "    },"
		+ "    sm: new Ext.grid.RowSelectionModel({singleSelect:true}),"
		+ "    width:600,"
		+ "    height:300,"
		+ "    frame:true,"
		+ "    title:'Framed with Checkbox Selection and Horizontal Scrolling',"
		+ "    iconCls:'icon-grid'"
		+ "});"
		+ "Note: Although this class inherits many configuration options from base classes, some of them (such as autoScroll, layout, items, etc) won't function as they do with the base Panel class."
		+ "To access the data in a Grid, it is necessary to use the data model encapsulated by the Store. See the cellclick event. ")
public class GridPanel extends Panel {

	private String autoExpandColumn;
	private Integer autoExpandMax;
	private Integer autoExpandMin;
	private String cm;
	private String colModel;
	private String columns;
	private Boolean disableSelection;
	private Boolean enableColumnHide;
	private Boolean enableColumnMove;
	private Boolean enableColumnResize;
	private Boolean enableDragDrop;
	private Boolean enableHdMenu;
	private Boolean enableRowHeightSync;
	private String loadMask;
	private Integer maxHeight;
	private Integer minColumnWidth;
	private Boolean monitorWindowResize;
	private String selModel;
	private String sm;
	private String store;
	private Boolean stripeRows;
	private Boolean trackMouseOver;
	private String view;
	private String viewConfig;

	@Variable
	public String getStore() {
		return store;
	}

	@Description("store : Ext.data.Store"
			+ "The Ext.data.Store the grid should use as its data source (required). ")
	public void setStore(String store) {
		this.store = store;
	}

	public String getAutoExpandColumn() {
		return autoExpandColumn;
	}

	@Description("autoExpandColumn : String"
			+ "The id of a column in this grid that should expand to fill unused space. This id can not be 0. ")
	public void setAutoExpandColumn(String autoExpandColumn) {
		this.autoExpandColumn = autoExpandColumn;
	}

	public Integer getAutoExpandMax() {
		return autoExpandMax;
	}

	@Description("autoExpandMax : Number"
			+ "The maximum width the autoExpandColumn can have (if enabled). Defaults to 1000. ")
	public void setAutoExpandMax(Integer autoExpandMax) {
		this.autoExpandMax = autoExpandMax;
	}

	public Integer getAutoExpandMin() {
		return autoExpandMin;
	}

	@Description("autoExpandMin : Number"
			+ "The minimum width the autoExpandColumn can have (if enabled). defaults to 50. ")
	public void setAutoExpandMin(Integer autoExpandMin) {
		this.autoExpandMin = autoExpandMin;
	}

	@Variable
	public String getCm() {
		return cm;
	}

	@Description("cm : Object" + "Shorthand for colModel. ")
	public void setCm(String cm) {
		this.cm = cm;
	}

	@Variable
	public String getColModel() {
		return colModel;
	}

	@Description("colModel : Object"
			+ "The Ext.grid.ColumnModel to use when rendering the grid (required). ")
	public void setColModel(String colModel) {
		this.colModel = colModel;
	}

	@Variable
	public String getColumns() {
		return columns;
	}

	@Description("columns : Array"
			+ "An array of columns to auto create a ColumnModel ")
	public void setColumns(String columns) {
		this.columns = columns;
	}

	public Boolean isDisableSelection() {
		return disableSelection;
	}

	@Description("disableSelection : Boolean"
			+ "True to disable selections in the grid (defaults to false). - ignored a SelectionModel is specified ")
	public void setDisableSelection(Boolean disableSelection) {
		this.disableSelection = disableSelection;
	}

	public Boolean isEnableColumnHide() {
		return enableColumnHide;
	}

	@Description("enableColumnHide : Boolean"
			+ "True to enable hiding of columns with the header context menu. ")
	public void setEnableColumnHide(Boolean enableColumnHide) {
		this.enableColumnHide = enableColumnHide;
	}

	public Boolean isEnableColumnMove() {
		return enableColumnMove;
	}

	@Description("enableColumnMove : Boolean"
			+ "True to enable drag and drop reorder of columns. ")
	public void setEnableColumnMove(Boolean enableColumnMove) {
		this.enableColumnMove = enableColumnMove;
	}

	public Boolean isEnableColumnResize() {
		return enableColumnResize;
	}

	@Description("enableColumnResize : Boolean"
			+ "False to turn off column resizing for the whole grid (defaults to true). ")
	public void setEnableColumnResize(Boolean enableColumnResize) {
		this.enableColumnResize = enableColumnResize;
	}

	public Boolean isEnableDragDrop() {
		return enableDragDrop;
	}

	@Description("enableDragDrop : Boolean"
			+ "True to enable drag and drop of rows. ")
	public void setEnableDragDrop(Boolean enableDragDrop) {
		this.enableDragDrop = enableDragDrop;
	}

	public Boolean isEnableHdMenu() {
		//return enableHdMenu;
		return Boolean.FALSE;
	}

	@Description("enableHdMenu : Boolean"
			+ "True to enable the drop down button for menu in the headers. ")
	public void setEnableHdMenu(Boolean enableHdMenu) {
		this.enableHdMenu = enableHdMenu;
	}

	public Boolean isEnableRowHeightSync() {
		return enableRowHeightSync;
	}

	@Description("enableRowHeightSync : Boolean"
			+ "True to manually sync row heights across locked and not locked rows. ")
	public void setEnableRowHeightSync(Boolean enableRowHeightSync) {
		this.enableRowHeightSync = enableRowHeightSync;
	}

	@Variable
	public String getLoadMask() {
		return loadMask;
	}

	@Description("loadMask : Object"
			+ "An Ext.LoadMask config or true to mask the grid while loading (defaults to false). ")
	public void setLoadMask(String loadMask) {
		this.loadMask = loadMask;
	}

	public Integer getMaxHeight() {
		return maxHeight;
	}

	@Description("maxHeight : Number"
			+ "Sets the maximum height of the grid - ignored if autoHeight is not on. ")
	public void setMaxHeight(Integer maxHeight) {
		this.maxHeight = maxHeight;
	}

	public Integer getMinColumnWidth() {
		return minColumnWidth;
	}

	@Description("minColumnWidth : Number"
			+ "The minimum width a column can be resized to. Defaults to 25. ")
	public void setMinColumnWidth(Integer minColumnWidth) {
		this.minColumnWidth = minColumnWidth;
	}

	public Boolean isMonitorWindowResize() {
		return monitorWindowResize;
	}

	@Description("monitorWindowResize : Boolean"
			+ "True to autoSize the grid when the window resizes. Defaults to true. ")
	public void setMonitorWindowResize(Boolean monitorWindowResize) {
		this.monitorWindowResize = monitorWindowResize;
	}

	@Variable
	public String getSelModel() {
		return selModel;
	}

	@Description("selModel : Object "
			+ "Any subclass of AbstractSelectionModel that will provide the selection model for the grid (defaults to Ext.grid.RowSelectionModel if not specified). ")
	public void setSelModel(String selModel) {
		this.selModel = selModel;
	}

	@Variable
	public String getSm() {
		return sm;
	}

	@Description("sm : Object" + "Shorthand for selModel. ")
	public void setSm(String sm) {
		this.sm = sm;
	}

	public Boolean isStripeRows() {
		return stripeRows;
	}

	@Description("stripeRows : Boolean"
			+ "True to stripe the rows. Default is false. ")
	public void setStripeRows(Boolean stripeRows) {
		this.stripeRows = stripeRows;
	}

	public Boolean isTrackMouseOver() {
		return trackMouseOver;
	}

	@Description("trackMouseOver : Boolean"
			+ "True to highlight rows when the mouse is over. Default is true. ")
	public void setTrackMouseOver(Boolean trackMouseOver) {
		this.trackMouseOver = trackMouseOver;
	}

	@Variable
	public String getView() {
		return view;
	}

	@Description("view : Object"
			+ "The Ext.grid.GridView used by the grid. This can be set before a call to render(). ")
	public void setView(String view) {
		this.view = view;
	}

	@Variable
	public String getViewConfig() {
		return viewConfig;
	}

	@Description("viewConfig : Object"
			+ "A config object that will be applied to the grid's UI view. Any of the config options available for Ext.grid.GridView can be specified here. ")
	public void setViewConfig(String viewConfig) {
		this.viewConfig = viewConfig;
	}

}
