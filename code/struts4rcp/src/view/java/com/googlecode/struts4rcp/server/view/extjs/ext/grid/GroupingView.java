package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Adds the ability for single level grouping to the grid."
		+ "var grid = new Ext.grid.GridPanel({"
		+ "// A groupingStore is required for a GroupingView"
		+ "store: new Ext.data.GroupingStore({"
		+ "    reader: reader,"
		+ "    data: xg.dummyData,"
		+ "    sortInfo:{field: 'company', direction: 'ASC'},"
		+ "    groupField:'industry'"
		+ "}),"
		+ "columns: ["
		+ "    {id:'company',header: 'Company', width: 60, sortable: true, dataIndex: 'company'},"
		+ "    {header: 'Price', width: 20, sortable: true, renderer: Ext.util.Format.usMoney, dataIndex: 'price'},"
		+ "    {header: 'Change', width: 20, sortable: true, dataIndex: 'change', renderer: Ext.util.Format.usMoney},"
		+ "    {header: 'Industry', width: 20, sortable: true, dataIndex: 'industry'},"
		+ "    {header: 'Last Updated', width: 20, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'lastChange'}"
		+ "],"
		+ "view: new Ext.grid.GroupingView({"
		+ "    forceFit:true,"
		+ "    // custom grouping text template to display the number of items per group"
		+ "    groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? 'Items' : 'Item']})'"
		+ "})," + "frame:true," + "width: 700," + "height: 450,"
		+ "collapsible: true," + "animCollapse: false,"
		+ "title: 'Grouping Example'," + "iconCls: 'icon-grid',"
		+ "renderTo: document.body" + "});")
public class GroupingView extends GridView {
	private String emptyGroupText;
	private Boolean enableGrouping;
	private Boolean enableGroupingMenu;
	private Boolean enableNoGroups;
	private String groupByText;
	private String groupTextTpl;
	private Boolean hideGroupedColumn;
	private Boolean ignoreAdd;
	private Boolean showGroupName;
	private String showGroupsText;
	private Boolean startCollapsed;

	public String getEmptyGroupText() {
		return emptyGroupText;
	}

	@Description("emptyText : String"
			+ "Default text to display in the grid body when no rows are available (defaults to ''). ")
	public void setEmptyGroupText(String emptyGroupText) {
		this.emptyGroupText = emptyGroupText;
	}

	public Boolean isEnableGrouping() {
		return enableGrouping;
	}

	@Description("enableGrouping : Boolean"
			+ "False to disable grouping functionality (defaults to true) ")
	public void setEnableGrouping(Boolean enableGrouping) {
		this.enableGrouping = enableGrouping;
	}

	public Boolean isEnableGroupingMenu() {
		return enableGroupingMenu;
	}

	@Description("enableGroupingMenu : Boolean "
			+ "True to enable the grouping control in the column menu ")
	public void setEnableGroupingMenu(Boolean enableGroupingMenu) {
		this.enableGroupingMenu = enableGroupingMenu;
	}

	public Boolean isEnableNoGroups() {
		return enableNoGroups;
	}

	@Description("enableNoGroups : Boolean"
			+ "True to allow the user to turn off grouping ")
	public void setEnableNoGroups(Boolean enableNoGroups) {
		this.enableNoGroups = enableNoGroups;
	}

	public String getGroupByText() {
		return groupByText;
	}

	@Description("groupByText : String"
			+ "Text displayed in the grid header menu for grouping by a column (defaults to 'Group By This Field'). ")
	public void setGroupByText(String groupByText) {
		this.groupByText = groupByText;
	}

	public String getGroupTextTpl() {
		return groupTextTpl;
	}

	@Description("groupTextTpl : String"
			+ "The template used to render the group text ")
	public void setGroupTextTpl(String groupTextTpl) {
		this.groupTextTpl = groupTextTpl;
	}

	public Boolean isHideGroupedColumn() {
		return hideGroupedColumn;
	}

	@Description("hideGroupedColumn : Boolean"
			+ "True to hide the column that is currently grouped ")
	public void setHideGroupedColumn(Boolean hideGroupedColumn) {
		this.hideGroupedColumn = hideGroupedColumn;
	}

	public Boolean isIgnoreAdd() {
		return ignoreAdd;
	}

	@Description("ignoreAdd : Boolean"
			+ "True to skip refreshing the view when new rows are added (defaults to false) ")
	public void setIgnoreAdd(Boolean ignoreAdd) {
		this.ignoreAdd = ignoreAdd;
	}

	public Boolean isShowGroupName() {
		return showGroupName;
	}

	@Description("showGroupName : Boolean"
			+ "True to display the name for each set of grouped rows (defaults to false) ")
	public void setShowGroupName(Boolean showGroupName) {
		this.showGroupName = showGroupName;
	}

	public String getShowGroupsText() {
		return showGroupsText;
	}

	@Description("showGroupsText : String"
			+ "Text displayed in the grid header for enabling/disabling grouping (defaults to 'Show in Groups'). ")
	public void setShowGroupsText(String showGroupsText) {
		this.showGroupsText = showGroupsText;
	}

	public Boolean isStartCollapsed() {
		return startCollapsed;
	}

	@Description("startCollapsed : Boolean"
			+ "True to start all groups collapsed ")
	public void setStartCollapsed(Boolean startCollapsed) {
		this.startCollapsed = startCollapsed;
	}
}
