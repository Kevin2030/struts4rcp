package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Flyweight composite class. Reuses the same Ext.Element for element operations."
		+ "var els = Ext.select('#some-el div.some-class');"
		+ " // or select directly from an existing element"
		+ " var el = Ext.get('some-el');"
		+ "el.select('div.some-class');"
		+ " els.setWidth(100); // all elements become 100 width"
		+ " els.hide(true); // all elements fade out and hide"
		+ " // or"
		+ " els.setWidth(100).hide(true);")
public class CompositeElementLite extends CompositeElement {

}
