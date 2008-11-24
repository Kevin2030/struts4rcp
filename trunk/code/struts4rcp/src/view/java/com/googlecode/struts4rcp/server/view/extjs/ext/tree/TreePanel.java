package com.googlecode.struts4rcp.server.view.extjs.ext.tree;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.Panel;

@Description("The TreePanel provides tree-structured UI representation of tree-structured data."
		+ "TreeNodes added to the TreePanel may each contain metadata used by your application in their attributes property."
		+ "A TreePanel must have a root node before it is rendered. This may either be specified using the root config option, or using the setRootNode method. ")
public class TreePanel extends Panel {
	private Boolean animate;
	private Boolean containerScroll;
	private String ddAppendOnly;
	private String ddGroup;
	private Boolean ddScroll;
	private String dragConfig;
	private String dropConfig;
	private Boolean enableDD;
	private Boolean enableDrag;
	private Boolean enableDrop;
	private String hlColor;
	private Boolean hlDrop;
	private Boolean lines;
	private String loader;
	private String pathSeparator;
	private String root;
	private Boolean rootVisible;
	private Boolean selModel;
	private Boolean singleExpand;
	
	//扩展属性--值如:   'multiple':多选; 'single':单选; 'cascade':级联多选   
	private String checkModel;
	
	//扩展属性--对树所有结点都可选
	private Boolean onlyLeafCheckable;

	public Boolean isAnimate() {
		return animate;
	}

	@Description("animate : Boolean \n"
			+ "true to enable animated expand/collapse (defaults to the value of Ext.enableFx) ")
	public void setAnimate(Boolean animate) {
		this.animate = animate;
	}

	public Boolean isContainerScroll() {
		return containerScroll;
	}

	@Description("containerScroll : Boolean \n"
			+ "true to register this container with ScrollManager ")
	public void setContainerScroll(Boolean containerScroll) {
		this.containerScroll = containerScroll;
	}

	public String getDdAppendOnly() {
		return ddAppendOnly;
	}

	@Description("ddAppendOnly : String \n"
			+ "True if the tree should only allow append drops (use for trees which are sorted) ")
	public void setDdAppendOnly(String ddAppendOnly) {
		this.ddAppendOnly = ddAppendOnly;
	}

	public String getDdGroup() {
		return ddGroup;
	}

	@Description("ddGroup : String \n"
			+ "The DD group this TreePanel belongs to ")
	public void setDdGroup(String ddGroup) {
		this.ddGroup = ddGroup;
	}

	public Boolean isDdScroll() {
		return ddScroll;
	}

	@Description("ddScroll : Boolean \n" + "true to enable body scrolling ")
	public void setDdScroll(Boolean ddScroll) {
		this.ddScroll = ddScroll;
	}

	@Variable
	public String getDragConfig() {
		return dragConfig;
	}

	@Description("dragConfig : Object \n"
			+ "Custom config to pass to the Ext.tree.TreeDragZone instance ")
	public void setDragConfig(String dragConfig) {
		this.dragConfig = dragConfig;
	}

	@Variable
	public String getDropConfig() {
		return dropConfig;
	}

	@Description("dropConfig : Object \n"
			+ "Custom config to pass to the Ext.tree.TreeDropZone instance")
	public void setDropConfig(String dropConfig) {
		this.dropConfig = dropConfig;
	}

	public Boolean isEnableDD() {
		return enableDD;
	}

	@Description("enableDD : Boolean \n" + "true to enable drag and drop ")
	public void setEnableDD(Boolean enableDD) {
		this.enableDD = enableDD;
	}

	public Boolean isEnableDrag() {
		return enableDrag;
	}

	@Description("enableDrag : Boolean \n" + "true to enable just drag ")
	public void setEnableDrag(Boolean enableDrag) {
		this.enableDrag = enableDrag;
	}

	public Boolean isEnableDrop() {
		return enableDrop;
	}

	@Description("enableDrop : Boolean \n" + "true to enable just drop ")
	public void setEnableDrop(Boolean enableDrop) {
		this.enableDrop = enableDrop;
	}

	public String getHlColor() {
		return hlColor;
	}

	@Description("hlColor : String \n"
			+ "The color of the node highlight (defaults to C3DAF9)")
	public void setHlColor(String hlColor) {
		this.hlColor = hlColor;
	}

	public Boolean isHlDrop() {
		return hlDrop;
	}

	@Description("hlDrop : Boolean \n"
			+ "false to disable node highlight on drop (defaults to the value of Ext.enableFx) ")
	public void setHlDrop(Boolean hlDrop) {
		this.hlDrop = hlDrop;
	}

	public Boolean isLines() {
		return lines;
	}

	@Description("lines : Boolean \n"
			+ "false to disable tree lines (defaults to true) ")
	public void setLines(Boolean lines) {
		this.lines = lines;
	}

	@Variable
	public String getLoader() {
		return loader;
	}

	@Description("loader : Ext.tree.TreeLoader \n"
			+ "A Ext.tree.TreeLoader for use with this TreePanel ")
	public void setLoader(String loader) {
		this.loader = loader;
	}

	public String getPathSeparator() {
		return pathSeparator;
	}

	@Description("pathSeparator : String \n"
			+ "The token used to separate sub-paths in path strings (defaults to '/') ")
	public void setPathSeparator(String pathSeparator) {
		this.pathSeparator = pathSeparator;
	}

	@Variable
	public String getRoot() {
		return root;
	}

	@Description("root : Ext.tree.TreeNode \n" + "The root node for the tree.")
	public void setRoot(String root) {
		this.root = root;
	}

	public Boolean isRootVisible() {
		return rootVisible;
	}

	@Description("rootVisible : Boolean \n"
			+ "false to hide the root node (defaults to true) ")
	public void setRootVisible(Boolean rootVisible) {
		this.rootVisible = rootVisible;
	}

	public Boolean isSelModel() {
		return selModel;
	}

	@Description("selModel : Boolean \n"
			+ "A tree selection model to use with this TreePanel (defaults to a Ext.tree.DefaultSelectionModel) ")
	public void setSelModel(Boolean selModel) {
		this.selModel = selModel;
	}

	public Boolean isSingleExpand() {
		return singleExpand;
	}

	@Description("singleExpand : Boolean \n"
			+ "true if only 1 node per branch may be expanded ")
	public void setSingleExpand(Boolean singleExpand) {
		this.singleExpand = singleExpand;
	}

	public String getCheckModel() {
		return checkModel;
	}

	public void setCheckModel(String checkModel) {
		this.checkModel = checkModel;
	}

	public Boolean getOnlyLeafCheckable() {
		return onlyLeafCheckable;
	}

	public void setOnlyLeafCheckable(Boolean onlyLeafCheckable) {
		this.onlyLeafCheckable = onlyLeafCheckable;
	}

}
