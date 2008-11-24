package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("Creates draggable splitter bar functionality from two elements (element to be dragged and element to be resized). "
		+ "Usage: "
		+ "var split = new Ext.SplitBar('elementToDrag', 'elementToSize',"
		+ "                   Ext.SplitBar.HORIZONTAL, Ext.SplitBar.LEFT);"
		+ "split.setAdapter(new Ext.SplitBar.AbsoluteLayoutAdapter('container'));"
		+ "split.minSize = 100;"
		+ "split.maxSize = 600;"
		+ "split.animate = true;" + "split.on('moved', splitterMoved);")
public class SplitBar extends Observable {
	public static class BasicLayoutAdapter {

	}

	public static class AbsoluteLayoutAdapter extends BasicLayoutAdapter {

	}
}
