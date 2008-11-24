package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("A specialized container representing the viewable application area (the browser viewport)."
		+ "The Viewport renders itself to the document body, and automatically sizes itself to the size of the browser viewport and manages window resizing. There may only be one Viewport created in a page. Inner layouts are available by virtue of the fact that all Panels added to the Viewport, either through its items, or through the items, or the add method of any of its child Panels may themselves have a layout."
		+ "The Viewport does not provide scrolling, so child Panels within the Viewport should provide for scrolling if needed using the autoScroll config."
		+ "Example showing a classic application border layout :"
		+ "new Ext.Viewport({"
		+ "    layout: 'border',"
		+ "    defaults: {"
		+ "        activeItem: 0,"
		+ "    },"
		+ "    items: [{"
		+ "        region: 'north',"
		+ "        html: 'Page Title',"
		+ "        autoHeight: true,"
		+ "        border: false,"
		+ "        margins: '0 0 5 0'"
		+ "    }, {"
		+ "        region: 'west',"
		+ "        collapsible: true,"
		+ "        title: 'Navigation',"
		+ "        xtype: 'treepanel',"
		+ "        width: 200,"
		+ "        autoScroll: true,"
		+ "        split: true,"
		+ "        loader: new Ext.tree.TreeLoader(),"
		+ "        root: new Ext.tree.AsyncTreeNode({"
		+ "            expanded: true,"
		+ "            children: [{"
		+ "                text: 'Menu Option 1',"
		+ "                leaf: true"
		+ "            }, {"
		+ "                text: 'Menu Option 2',"
		+ "                leaf: true"
		+ "            }, {"
		+ "                text: 'Menu Option 3',"
		+ "                leaf: true"
		+ "            }]"
		+ "        }),"
		+ "        rootVisible: false,"
		+ "        listeners: {"
		+ "            click: function(n) {"
		+ "                Ext.Msg.alert('Navigation Tree Click', 'You clicked: ' + n.attributes.text + '');"
		+ "            }"
		+ "        }"
		+ "    }, {"
		+ "        region: 'center',"
		+ "        xtype: 'tabpanel',"
		+ "        items: {"
		+ "            title: 'Default Tab',"
		+ "            html: 'The first tab\'s content. Others may be added dynamically'"
		+ "        }"
		+ "    }, {"
		+ "        region: 'south',"
		+ "        title: 'Information',"
		+ "        collapsible: true,"
		+ "        html: 'Information goes here',"
		+ "        split: true,"
		+ "        height: 100," + "        minHeight: 100" + "    }]" + "});")
public class Viewport extends Container {

}
