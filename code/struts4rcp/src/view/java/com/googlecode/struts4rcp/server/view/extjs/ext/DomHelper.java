package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("Utility class for working with DOM and/or Templates. It transparently supports using HTML fragments or DOM."
		+ "This is an example, where an unordered list with 5 children items is appended to an existing element with id 'my-div':"
		+ "var list = dh.append('my-div', {"
		+ "    tag: 'ul', cls: 'my-list', children: ["
		+ "        {tag: 'li', id: 'item0', html: 'List Item 0'},"
		+ "        {tag: 'li', id: 'item1', html: 'List Item 1'}, "
		+ "        {tag: 'li', id: 'item2', html: 'List Item 2'}, "
		+ "        {tag: 'li', id: 'item3', html: 'List Item 3'}, "
		+ "        {tag: 'li', id: 'item4', html: 'List Item 4'}"
		+ "    ]"
		+ "});"
		+ "For more information and examples, see the original blog post.")
public class DomHelper extends ComponentTag {

}
