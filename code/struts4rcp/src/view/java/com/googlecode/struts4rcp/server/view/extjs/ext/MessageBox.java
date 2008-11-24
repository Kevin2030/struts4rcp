package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("Utility class for generating different styles of message boxes. The alias Ext.Msg can also be used."
		+ "Note that the MessageBox is asynchronous. Unlike a regular JavaScript alert (which will halt browser execution), showing a MessageBox will not cause the code to stop. For this reason, if you have code that should only run after some user feedback from the MessageBox, you must use a callback function (see the function parameter for show for more details)."
		+ "Example usage:"
		+ "// Basic alert:"
		+ "Ext.Msg.alert('Status', 'Changes saved successfully.');"
		+ "// Prompt for user data and process the result using a callback:"
		+ "Ext.Msg.prompt('Name', 'Please enter your name:', function(btn, text){"
		+ "    if (btn == 'ok'){"
		+ "        // process text value and close..."
		+ "    }"
		+ "});"
		+ "// Show a dialog using config options:"
		+ "Ext.Msg.show({"
		+ "   title:'Save Changes?',"
		+ "   msg: 'Your are closing a tab that has unsaved changes. Would you like to save your changes?',"
		+ "   buttons: Ext.Msg.YESNOCANCEL,"
		+ "   fn: processResult,"
		+ "   animEl: 'elId'," + "   icon: Ext.MessageBox.QUESTION" + "});")
public class MessageBox extends ComponentTag {

}
