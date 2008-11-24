package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Provides attractive and customizable tooltips for any element. The QuickTips singleton is used to configure and manage tooltips globally for multiple elements in a generic manner. To create individual tooltips with maximum customizability, you should consider either Ext.Tip or Ext.ToolTip."
		+ "Quicktips can be configured via tag attributes directly in markup, or by registering quick tips programmatically via the register method."
		+ "The singleton's instance of Ext.QuickTip is available via getQuickTip, and supports all the methods, and all the all the configuration properties of Ext.QuickTip. These settings will apply to all tooltips shown by the singleton."
		+ "Below is the summary of the configuration properties which can be used. For detailed descriptions see getQuickTip"
		+ "QuickTips singleton configs (all are optional)"
		+ "dismissDelay "
		+ "hideDelay "
		+ "maxWidth "
		+ "minWidth "
		+ "showDelay "
		+ "trackMouse"
		+ "Target element configs (optional unless otherwise noted)"
		+ "autoHide "
		+ "cls "
		+ "dismissDelay (overrides singleton value)"
		+ "target (required) "
		+ "text (required) "
		+ "title "
		+ "width"
		+ "Here is an example showing how some of these config options could be used:"
		+ "// Init the singleton.  Any tag-based quick tips will start working."
		+ "Ext.QuickTips.init();"
		+ "// Apply a set of config properties to the singleton"
		+ "Ext.apply(Ext.QuickTips.getQuickTip(), {"
		+ "    maxWidth: 200,"
		+ "    minWidth: 100,"
		+ "    showDelay: 50,"
		+ "    trackMouse: true"
		+ "});"
		+ "// Manually register a quick tip for a specific element"
		+ "q.register({"
		+ "    target: 'my-div',"
		+ "    title: 'My Tooltip',"
		+ "    text: 'This tooltip was added in code',"
		+ "    width: 100,"
		+ "    dismissDelay: 20"
		+ "});"
		+ "To register a quick tip in markup, you simply add one or more of the valid QuickTip attributes prefixed with the ext: namespace. The HTML element itself is automatically set as the quick tip target. Here is the summary of supported attributes (optional unless otherwise noted):"
		+ "hide: Specifying 'user' is equivalent to setting autoHide = false. Any other value will be the same as autoHide = true. "
		+ "qclass: A CSS class to be applied to the quick tip (equivalent to the 'cls' target element config). "
		+ "qtip (required): The quick tip text (equivalent to the 'text' target element config). "
		+ "qtitle: The quick tip title (equivalent to the 'title' target element config). "
		+ "qwidth: The quick tip width (equivalent to the 'width' target element config)."
		+ "Here is an example of configuring an HTML element to display a tooltip from markup:"
		+ "// Add a quick tip to an HTML button"
		+ "<input type='button' value='OK' ext:qtitle='OK Button' ext:qwidth='100'"
		+ "     ext:qtip='This is a quick tip from markup!'></input>"
		+ "This class is a singleton and cannot be created directly. ")
public class QuickTips extends ComponentTag {

}