package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("Base class for all Ext components. All subclasses of Component can automatically participate in the standard Ext component lifecycle of creation, rendering and destruction. They also have automatic support for basic hide/show and enable/disable behavior. Component allows any subclass to be lazy-rendered into any Ext.Container and to be automatically registered with the Ext.ComponentMgr so that it can be referenced at any time via Ext.getCmp. All visual widgets that require rendering into a layout should subclass Component (or Ext.BoxComponent if managed box model handling is required)."
		+ "Every component has a specific xtype, which is its Ext-specific type name, along with methods for checking the xtype like getXType and isXType. This is the list of all valid xtypes:"
		+ "xtype            Class"
		+ "-------------    ------------------"
		+ "box              Ext.BoxComponent"
		+ "button           Ext.Button"
		+ "colorpalette     Ext.ColorPalette"
		+ "component        Ext.Component"
		+ "container        Ext.Container"
		+ "cycle            Ext.CycleButton"
		+ "dataview         Ext.DataView"
		+ "datepicker       Ext.DatePicker"
		+ "editor           Ext.Editor"
		+ "editorgrid       Ext.grid.EditorGridPanel"
		+ "grid             Ext.grid.GridPanel"
		+ "paging           Ext.PagingToolbar"
		+ "panel            Ext.Panel"
		+ "progress         Ext.ProgressBar"
		+ "splitbutton      Ext.SplitButton"
		+ "tabpanel         Ext.TabPanel"
		+ "treepanel        Ext.tree.TreePanel"
		+ "viewport         Ext.ViewPort"
		+ "window           Ext.Window"
		+ "Toolbar components"
		+ "---------------------------------------"
		+ "toolbar          Ext.Toolbar"
		+ "tbbutton         Ext.Toolbar.Button"
		+ "tbfill           Ext.Toolbar.Fill"
		+ "tbitem           Ext.Toolbar.Item"
		+ "tbseparator      Ext.Toolbar.Separator"
		+ "tbspacer         Ext.Toolbar.Spacer"
		+ "tbsplit          Ext.Toolbar.SplitButton"
		+ "tbtext           Ext.Toolbar.TextItem"
		+ "Form components"
		+ "---------------------------------------"
		+ "form             Ext.FormPanel"
		+ "checkbox         Ext.form.Checkbox"
		+ "combo            Ext.form.ComboBox"
		+ "datefield        Ext.form.DateField"
		+ "field            Ext.form.Field"
		+ "fieldset         Ext.form.FieldSet"
		+ "hidden           Ext.form.Hidden"
		+ "htmleditor       Ext.form.HtmlEditor"
		+ "numberfield      Ext.form.NumberField"
		+ "radio            Ext.form.Radio"
		+ "textarea         Ext.form.TextArea"
		+ "textfield        Ext.form.TextField"
		+ "timefield        Ext.form.TimeField"
		+ "trigger          Ext.form.TriggerField")
public class Component extends Observable {
	private Boolean allowDomMove;
	private String applyTo;
	private Boolean autoShow;
	private String cls;
	private String ctCls;
	private String disabledClass;
	private String hideMode;
	private Boolean hideParent;
	private String id;
	private String plugins;
	private String renderTo;
	private String stateEvents;
	private String stateId;
	private String style;
	private String xtype;

	public Boolean isAllowDomMove() {
		return allowDomMove;
	}

	@Description("allowDomMove : Boolean \n"
			+ "Whether the component can move the Dom node when rendering (defaults to true). ")
	public void setAllowDomMove(Boolean allowDomMove) {
		this.allowDomMove = allowDomMove;
	}

	public String getApplyTo() {
		return applyTo;
	}

	@Description("applyTo : Mixed \n"
			+ "The id of the node, a DOM node or an existing Element corresponding to a DIV that is already present in the document that specifies some structural markup for this component. When applyTo is used, constituent parts of the component can also be specified by id or CSS class name within the main element, and the component being created may attempt to create its subcomponents from that markup if applicable. Using this config, a call to render() is not required. If applyTo is specified, any value passed for renderTo will be ignored and the target element's parent node will automatically be used as the component's container. ")
	public void setApplyTo(String applyTo) {
		this.applyTo = applyTo;
	}

	public Boolean isAutoShow() {
		return autoShow;
	}

	@Description("autoShow : Boolean \n"
			+ "True if the component should check for hidden classes (e.g. 'x-hidden' or 'x-hide-display') and remove them on render (defaults to false). ")
	public void setAutoShow(Boolean autoShow) {
		this.autoShow = autoShow;
	}

	public String getCls() {
		return cls;
	}

	@Description("cls : String \n"
			+ "An optional extra CSS class that will be added to this component's Element (defaults to ''). This can be useful for adding customized styles to the component or any of its children using standard CSS rules. ")
	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getCtCls() {
		return ctCls;
	}

	@Description("ctCls : String \n"
			+ "An optional extra CSS class that will be added to this component's container (defaults to ''). This can be useful for adding customized styles to the container or any of its children using standard CSS rules. ")
	public void setCtCls(String ctCls) {
		this.ctCls = ctCls;
	}

	public String getDisabledClass() {
		return disabledClass;
	}

	@Description("disabledClass : String \n"
			+ "CSS class added to the component when it is disabled (defaults to 'x-item-disabled'). ")
	public void setDisabledClass(String disabledClass) {
		this.disabledClass = disabledClass;
	}

	public String getHideMode() {
		return hideMode;
	}

	@Description("hideMode : String \n"
			+ "How this component should hidden. Supported values are 'visibility' (css visibility), 'offsets' (negative offset position) and 'display' (css display) - defaults to 'display'. ")
	public void setHideMode(String hideMode) {
		this.hideMode = hideMode;
	}

	public Boolean isHideParent() {
		return hideParent;
	}

	@Description("hideParent : Boolean \n"
			+ "True to hide and show the component's container when hide/show is called on the component, false to hide and show the component itself (defaults to false). For example, this can be used as a shortcut for a hide button on a window by setting hide:true on the button when adding it to its parent container. ")
	public void setHideParent(Boolean hideParent) {
		this.hideParent = hideParent;
	}

	public String getId() {
		return id;
	}

	@Description("id : String \n"
			+ "The unique id of this component (defaults to an auto-assigned id).")
	public void setId(String id) {
		this.id = id;
	}

	@Variable
	public String getPlugins() {
		return plugins;
	}

	@Description("plugins : Object/Array \n"
			+ "An object or array of objects that will provide custom functionality for this component. The only requirement for a valid plugin is that it contain an init method that accepts a reference of type Ext.Component. When a component is created, if any plugins are available, the component will call the init method on each plugin, passing a reference to itself. Each plugin can then call methods or respond to events on the component as needed to provide its functionality. ")
	public void setPlugins(String plugins) {
		this.plugins = plugins;
	}

	@Variable
	public String getRenderTo() {
		return renderTo;
	}

	@Description("renderTo : Mixed \n"
			+ "The id of the node, a DOM node or an existing Element that will be the container to render this component into. Using this config, a call to render() is not required. ")
	public void setRenderTo(String renderTo) {
		this.renderTo = renderTo;
	}

	@Variable
	public String getStateEvents() {
		return stateEvents;
	}

	@Description("stateEvents : Array \n"
			+ "An array of events that, when fired, should trigger this component to save its state (defaults to none). These can be any types of events supported by this component, including browser or custom events (e.g., ['click', 'customerchange']). ")
	public void setStateEvents(String stateEvents) {
		this.stateEvents = stateEvents;
	}

	public String getStateId() {
		return stateId;
	}

	@Description("stateId : String \n"
			+ "The unique id for this component to use for state management purposes (defaults to the component id). ")
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStyle() {
		return style;
	}

	@Description("style : String \n"
			+ "A custom style specification to be applied to this component's Element. Should be a valid argument to Ext.Element.applyStyles. ")
	public void setStyle(String style) {
		this.style = style;
	}

	public String getXtype() {
		return xtype;
	}

	@Description("xtype : String \n"
			+ "The registered xtype to create. This config option is not used when passing a config object into a constructor. This config option is used only when lazy instantiation is being used, and a child item of a Container is being specified not as a fully instantiated Component, but as a Component config object. The xtype will be looked up at render time up to determine what type of child Component to create."
			+ "The predefined xtypes are listed here. ")
	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

}
