package com.googlecode.struts4rcp.server.view.extjs.ext.layout;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("This is a layout specifically designed for creating forms. This class can be extended or created via the layout:'form' Ext.Container.layout config, and should generally not need to be created directly via the new keyword. However, when used in an application, it will usually be preferrable to use a Ext.form.FormPanel (which automatically uses FormLayout as its layout class) since it also provides built-in functionality for loading, validating and submitting the form."
		+ "Note that when creating a layout via config, the layout-specific config properties must be passed in via the Ext.Container.layoutConfig object which will then be applied internally to the layout. The container using the FormLayout can also supply the following form-specific config properties which will be applied by the layout: "
		+ "hideLabels: (Boolean) True to hide field labels by default (defaults to false) "
		+ "itemCls: (String) A CSS class to add to the div wrapper that contains each field label and field element (the default class is 'x-form-item' and itemCls will be added to that)"
		+ "labelAlign: (String) The default label alignment. The default value is empty string '' for left alignment, but specifying 'top' will align the labels above the fields. "
		+ "labelPad: (Number) The default padding in pixels for field labels (defaults to 5). labelPad only applies if labelWidth is also specified, otherwise it will be ignored. "
		+ "labelWidth: (Number) The default width in pixels of field labels (defaults to 100) "
		+ "Any type of components can be added to a FormLayout, but items that inherit from Ext.form.Field can also supply the following field-specific config properties:"
		+ "clearCls: (String) The CSS class to apply to the special clearing div rendered directly after each form field wrapper (defaults to 'x-form-clear-left') "
		+ "fieldLabel: (String) The text to display as the label for this field (defaults to '') "
		+ "hideLabel: (Boolean) True to hide the label and separator for this field (defaults to false)."
		+ "itemCls: (String) A CSS class to add to the div wrapper that contains this field label and field element (the default class is 'x-form-item' and itemCls will be added to that). If supplied, itemCls at the field level will override the default itemCls supplied at the container level."
		+ "labelSeparator: (String) The separator to display after the text of the label for this field (defaults to a colon ':' or the layout's value for labelSeparator). To hide the separator use empty string ''. "
		+ "labelStyle: (String) A CSS style specification string to add to the field label for this field (defaults to '' or the layout's value for labelStyle). "
		+ "Example usage: "
		+ "// Required if showing validation messages"
		+ "Ext.QuickTips.init();"
		+ "// While you can create a basic Panel with layout:'form', practically"
		+ "// you should usually use a FormPanel to also get its form functionality"
		+ "// since it already creates a FormLayout internally."
		+ "var form = new Ext.form.FormPanel({"
		+ "    labelWidth: 75,"
		+ "    title: 'Form Layout',"
		+ "    bodyStyle:'padding:15px',"
		+ "    width: 350,"
		+ "    labelPad: 10,"
		+ "    defaultType: 'textfield',"
		+ "    defaults: {"
		+ "        // applied to each contained item"
		+ "        width: 230,"
		+ "        msgTarget: 'side'"
		+ "    },"
		+ "    layoutConfig: {"
		+ "        // layout-specific configs go here"
		+ "        labelSeparator: ''"
		+ "    },"
		+ "    items: [{"
		+ "            fieldLabel: 'First Name',"
		+ "           name: 'first',"
		+ "            allowBlank: false"
		+ "        },{"
		+ "            fieldLabel: 'Last Name',"
		+ "            name: 'last'"
		+ "        },{"
		+ "            fieldLabel: 'Company',"
		+ "            name: 'company'"
		+ "        },{"
		+ "            fieldLabel: 'Email',"
		+ "            name: 'email',"
		+ "            vtype:'email'"
		+ "        }"
		+ "    ],"
		+ "    buttons: [{"
		+ "        text: 'Save'"
		+ "    },{"
		+ "        text: 'Cancel'" + "    }]" + "});")
public class FormLayout extends AnchorLayout {
	private String elementStyle;
	private String labelSeparator;
	private String labelStyle;

	public String getElementStyle() {
		return elementStyle;
	}

	@Description("elementStyle : String \n "
			+ "A CSS style specification string to add to each field element in this layout (defaults to ''). ")
	public void setElementStyle(String elementStyle) {
		this.elementStyle = elementStyle;
	}

	public String getLabelSeparator() {
		return labelSeparator;
	}

	@Description("labelSeparator : String \n "
			+ "The standard separator to display after the text of each form label (defaults to a colon ':'). To turn off separators for all fields in this layout by default specify empty string '' (if the labelSeparator value is explicitly set at the field level, those will still be displayed). ")
	public void setLabelSeparator(String labelSeparator) {
		this.labelSeparator = labelSeparator;
	}

	public String getLabelStyle() {
		return labelStyle;
	}

	@Description("labelStyle : String \n"
			+ "A CSS style specification string to add to each field label in this layout (defaults to ''). ")
	public void setLabelStyle(String labelStyle) {
		this.labelStyle = labelStyle;
	}
}
