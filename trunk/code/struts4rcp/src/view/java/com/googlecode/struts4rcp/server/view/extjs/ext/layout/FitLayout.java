package com.googlecode.struts4rcp.server.view.extjs.ext.layout;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("This is a base class for layouts that contain a single item that automatically expands to fill the layout's container. This class is intended to be extended or created via the layout:'fit' Ext.Container.layout config, and should generally not need to be created directly via the new keyword."
		+ "FitLayout does not have any direct config options (other than inherited ones). To fit a panel to a container using FitLayout, simply set layout:'fit' on the container and add a single panel to it. If the container has multiple panels, only the first one will be displayed. Example usage:"
		+ "var p = new Ext.Panel({"
		+ "    title: 'Fit Layout',"
		+ "    layout:'fit',"
		+ "    items: {"
		+ "        title: 'Inner Panel',"
		+ "        html: '<p>This is the inner panel content</p>',"
		+ "        border: false" + "    }" + "});")
public class FitLayout extends ContainerLayout {

}
