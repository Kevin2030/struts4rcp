package com.googlecode.struts4rcp.server.view.extjs.ext.tree;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.data.Node;

public class TreeNode extends Node {
	private Boolean allowChildren;
	private Boolean allowDrag;
	private Boolean allowDrop;
	private Boolean checked;
	private String cls;
	private Boolean disabled;
	private Boolean draggable;
	private Boolean expandable;
	private Boolean expanded;
	private String href;
	private String hrefTarget;
	private String icon;
	private String iconCls;
	private Boolean isTarget;
	private String qtip;
	private String qtipCfg;
	private Boolean singleClickExpand;
	private String text;
	private String uiProvider;

	public Boolean isAllowChildren() {
		return allowChildren;
	}

	@Description("allowChildren : Boolean \n"
			+ "False to not allow this node to have child nodes (defaults to true) ")
	public void setAllowChildren(Boolean allowChildren) {
		this.allowChildren = allowChildren;
	}

	public Boolean isAllowDrag() {
		return allowDrag;
	}

	@Description("allowDrag : Boolean \n"
			+ "False to make this node undraggable if draggable = true (defaults to true) ")
	public void setAllowDrag(Boolean allowDrag) {
		this.allowDrag = allowDrag;
	}

	public Boolean isAllowDrop() {
		return allowDrop;
	}

	@Description("allowDrop : Boolean \n"
			+ "False if this node cannot have child nodes dropped on it (defaults to true) ")
	public void setAllowDrop(Boolean allowDrop) {
		this.allowDrop = allowDrop;
	}

	public Boolean isChecked() {
		return checked;
	}

	@Description("checked : Boolean \n"
			+ "True to render a checked checkbox for this node, false to render an unchecked checkbox (defaults to undefined with no checkbox rendered) ")
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getCls() {
		return cls;
	}

	@Description("cls : String \n" + "A css class to be added to the node ")
	public void setCls(String cls) {
		this.cls = cls;
	}

	public Boolean isDisabled() {
		return disabled;
	}

	@Description("disabled : Boolean \n" + "true to start the node disabled ")
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean isDraggable() {
		return draggable;
	}

	@Description("draggable : Boolean \n"
			+ "True to make this node draggable (defaults to false) ")
	public void setDraggable(Boolean draggable) {
		this.draggable = draggable;
	}

	public Boolean isExpandable() {
		return expandable;
	}

	@Description("expandable : Boolean \n"
			+ "If set to true, the node will always show a plus/minus icon, even when empty ")
	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

	public Boolean isExpanded() {
		return expanded;
	}

	@Description("expanded : Boolean \n" + "true to start the node expanded ")
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public String getHref() {
		return href;
	}

	@Description("href : String \n"
			+ "URL of the link used for the node (defaults to #) ")
	public void setHref(String href) {
		this.href = href;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	@Description("hrefTarget : String \n" + "target frame for the link ")
	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public String getIcon() {
		return icon;
	}

	@Description("icon : String \n"
			+ "The path to an icon for the node. The preferred way to do this is to use the cls or iconCls attributes and add the icon via a CSS background image. ")
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconCls() {
		return iconCls;
	}

	@Description("iconCls : String \n"
			+ "A css class to be added to the nodes icon element for applying css background images ")
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Boolean isTarget() {
		return isTarget;
	}

	@Description("isTarget : Boolean \n"
			+ "False to not allow this node to act as a drop target (defaults to true) ")
	public void setTarget(Boolean isTarget) {
		this.isTarget = isTarget;
	}

	public String getQtip() {
		return qtip;
	}

	@Description("qtip : String \n" + "An Ext QuickTip for the node ")
	public void setQtip(String qtip) {
		this.qtip = qtip;
	}

	public String getQtipCfg() {
		return qtipCfg;
	}

	@Description("qtipCfg : String \n"
			+ "An Ext QuickTip config for the node (used instead of qtip) ")
	public void setQtipCfg(String qtipCfg) {
		this.qtipCfg = qtipCfg;
	}

	public Boolean isSingleClickExpand() {
		return singleClickExpand;
	}

	@Description("singleClickExpand : Boolean \n"
			+ "True for single click expand on this node ")
	public void setSingleClickExpand(Boolean singleClickExpand) {
		this.singleClickExpand = singleClickExpand;
	}

	public String getText() {
		return text;
	}

	@Description("text : String \n" + "The text for this node ")
	public void setText(String text) {
		this.text = text;
	}

	@Variable
	public String getUiProvider() {
		return uiProvider;
	}

	@Description("uiProvider : Function \n"
			+ "A UI class to use for this node (defaults to Ext.tree.TreeNodeUI) ")
	public void setUiProvider(String uiProvider) {
		this.uiProvider = uiProvider;
	}

}
