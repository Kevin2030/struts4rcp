package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("Base class for any Ext.BoxComponent that can contain other components. Containers handle the basic behavior of containing items, namely adding, inserting and removing them. The specific layout logic required to visually render contained items is delegated to any one of the different layout classes available. This class is intended to be extended and should generally not need to be created directly via the new keyword. ")
public class Container extends BoxComponent {
	private String activeItem;
	private Boolean autoDestroy;
	private Boolean bufferResize;
	private String defaultType;
	private String defaults;
	private Boolean hideBorders;
	private String items;
	private String layout;
	private String layoutConfig;
	private Boolean monitorResize;

	public String getActiveItem() {
		return activeItem;
	}

	@Description("activeItem : String/Number \n"
			+ "A string component id or the numeric index of the component that should be initially activated within the container's layout on render. For example, activeItem: 'item-1' or activeItem: 0 (index 0 = the first item in the container's collection). activeItem only applies to layout styles that can display items one at a time (like Ext.layout.Accordion, Ext.layout.CardLayout and Ext.layout.FitLayout). Related to Ext.layout.ContainerLayout.activeItem. ")
	public void setActiveItem(String activeItem) {
		this.activeItem = activeItem;
	}

	public Boolean isAutoDestroy() {
		return autoDestroy;
	}

	@Description("autoDestroy : Boolean"
			+ "If true the container will automatically destroy any contained component that is removed from it, else destruction must be handled manually (defaults to true).")
	public void setAutoDestroy(Boolean autoDestroy) {
		this.autoDestroy = autoDestroy;
	}

	public Boolean isBufferResize() {
		return bufferResize;
	}

	@Description("bufferResize : Boolean/Number \n"
			+ "When set to true (100 milliseconds) or a number of milliseconds, the layout assigned for this container will buffer the frequency it calculates and does a re-layout of components. This is useful for heavy containers or containers with a large amount of sub components that frequent calls to layout are expensive. ")
	public void setBufferResize(Boolean bufferResize) {
		this.bufferResize = bufferResize;
	}

	public String getDefaultType() {
		return defaultType;
	}

	@Description("defaultType : String \n"
			+ "The default type of container represented by this object as registered in Ext.ComponentMgr (defaults to 'panel'). ")
	public void setDefaultType(String defaultType) {
		this.defaultType = defaultType;
	}

	@Variable
	public String getDefaults() {
		return defaults;
	}

	@Description("defaults : Object \n"
			+ "A config object that will be applied to all components added to this container either via the items config or via the add or insert methods. The defaults config can contain any number of name/value property pairs to be added to each item, and should be valid for the types of items being added to the container. For example, to automatically apply padding to the body of each of a set of contained Ext.Panel items, you could pass: defaults: {bodyStyle:'padding:15px'}. ")
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}

	public Boolean isHideBorders() {
		return hideBorders;
	}

	@Description("hideBorders : Boolean \n"
			+ "True to hide the borders of each contained component, false to defer to the component's existing border settings (defaults to false). ")
	public void setHideBorders(Boolean hideBorders) {
		this.hideBorders = hideBorders;
	}

	@Variable
	public String getItems() {
		return items;
	}

	@Description("items : Mixed \n"
			+ "A single item, or an array of child Components to be added to this container. Each item can be any type of object based on Ext.Component."
			+ "Component config objects may also be specified in order to avoid the overhead of constructing a real Component object if lazy rendering might mean that the added Component will not be rendered immediately. To take advantage of this 'lazy instantiation', set the Ext.Component.xtype config property to the registered type of the Component wanted."
			+ "For a list of all available xtypes, see Ext.Component. If a single item is being passed, it should be passed directly as an object reference (e.g., items: {...}). Multiple items should be passed as an array of objects (e.g., items: [{...}, {...}]). ")
	public void setItems(String items) {
		this.items = items;
	}

	public String getLayout() {
		return layout;
	}

	@Description("layout : String \n"
			+ "The layout type to be used in this container. If not specified, a default Ext.layout.ContainerLayout will be created and used. Valid values are: accordion, anchor, border, card, column, fit, form and table. Specific config values for the chosen layout type can be specified using layoutConfig. ")
	public void setLayout(String layout) {
		this.layout = layout;
	}

	@Variable
	public String getLayoutConfig() {
		return layoutConfig;
	}

	@Description("layoutConfig : Object \n"
			+ "This is a config object containing properties specific to the chosen layout (to be used in conjunction with the layout config value). For complete details regarding the valid config options for each layout type, see the layout class corresponding to the type specified:"
			+ "Ext.layout.Accordion " + "Ext.layout.AnchorLayout"
			+ "Ext.layout.BorderLayout " + "Ext.layout.CardLayout "
			+ "Ext.layout.ColumnLayout" + "Ext.layout.FitLayout "
			+ "Ext.layout.FormLayout " + "Ext.layout.TableLayout")
	public void setLayoutConfig(String layoutConfig) {
		this.layoutConfig = layoutConfig;
	}

	public Boolean isMonitorResize() {
		return monitorResize;
	}

	@Description("monitorResize : Boolean \n"
			+ "True to automatically monitor window resize events to handle anything that is sensitive to the current size of the viewport. This value is typically managed by the chosen layout and should not need to be set manually. ")
	public void setMonitorResize(Boolean monitorResize) {
		this.monitorResize = monitorResize;
	}

}
