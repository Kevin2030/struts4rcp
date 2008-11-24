package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("A static Ext.util.TaskRunner instance that can be used to start and stop arbitrary tasks. See Ext.util.TaskRunner for supported methods and task config properties."
		+ "// Start a simple clock task that updates a div once per second"
		+ "var task = {"
		+ "    run: function(){"
		+ "        Ext.fly('clock').update(new Date().format('g:i:s A'));"
		+ "    },"
		+ "    interval: 1000 //1 second"
		+ "}"
		+ "Ext.TaskMgr.start(task);"
		+ "This class is a singleton and cannot be created directly. ")
public class TaskMgr extends ComponentTag {

}
