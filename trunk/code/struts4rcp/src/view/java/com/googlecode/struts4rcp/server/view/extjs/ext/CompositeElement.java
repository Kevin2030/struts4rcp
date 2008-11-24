package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Standard composite class. Creates a Ext.Element for every element in the collection. "
		+ "NOTE: Although they are not listed, this class supports all of the set/update methods of Ext.Element. All Ext.Element actions will be performed on all the elements in this collection."
		+ "All methods return this and can be chained. "
		+ "var els = Ext.select('#some-el div.some-class', true);"
		+ " // or select directly from an existing element"
		+ " var el = Ext.get('some-el');"
		+ " el.select('div.some-class', true);"
		+ " els.setWidth(100); // all elements become 100 width"
		+ " els.hide(true); // all elements fade out and hide"
		+ " // or"
		+ " els.setWidth(100).hide(true);")
public class CompositeElement extends ComponentTag {

}
