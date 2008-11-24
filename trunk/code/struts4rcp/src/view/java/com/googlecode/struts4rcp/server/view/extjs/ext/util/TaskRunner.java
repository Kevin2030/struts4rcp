package com.googlecode.struts4rcp.server.view.extjs.ext.util;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Provides the ability to execute one or more arbitrary tasks in a multithreaded manner. Generally, you can use the singleton Ext.TaskMgr instead, but if needed, you can create separate instances of TaskRunner. Any number of separate tasks can be started at any time and will run independently of each other. Example usage:"
		+ "// Start a simple clock task that updates a div once per second"
		+ "var task = {"
		+ "    run: function(){"
		+ "        Ext.fly('clock').update(new Date().format('g:i:s A'));"
		+ "    },"
		+ "    interval: 1000 //1 second"
		+ "}"
		+ "var runner = new Ext.util.TaskRunner();" + "runner.start(task);")
public class TaskRunner extends ComponentTag {

}
