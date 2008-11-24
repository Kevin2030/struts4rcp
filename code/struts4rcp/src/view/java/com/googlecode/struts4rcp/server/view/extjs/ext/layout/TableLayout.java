package com.googlecode.struts4rcp.server.view.extjs.ext.layout;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Class Ext.layout.TableLayoutPackage: Ext.layout \n"
		+ "Defined In: TableLayout.js "
		+ "Class: TableLayout "
		+ "Extends: ContainerLayout "
		+ "This layout allows you to easily render content into an HTML table. The total number of columns can be specified, and rowspan and colspan can be used to create complex layouts within the table. This class is intended to be extended or created via the layout:'table' Ext.Container.layout config, and should generally not need to be created directly via the new keyword."
		+ "Note that when creating a layout via config, the layout-specific config properties must be passed in via the Ext.Container.layoutConfig object which will then be applied internally to the layout. In the case of TableLayout, the only valid layout config property is columns. However, the items added to a TableLayout can supply table-specific config properties of rowspan and colspan, as explained below."
		+ "The basic concept of building up a TableLayout is conceptually very similar to building up a standard HTML table. You simply add each panel (or 'cell') that you want to include along with any span attributes specified as the special config properties of rowspan and colspan which work exactly like their HTML counterparts. Rather than explicitly creating and nesting rows and columns as you would in HTML, you simply specify the total column count in the layoutConfig and start adding panels in their natural order from left to right, top to bottom. The layout will automatically figure out, based on the column count, rowspans and colspans, how to position each panel within the table. Just like with HTML tables, your rowspans and colspans must add up correctly in your overall layout or you'll end up with missing and/or extra cells! Example usage:"
		+ "// This code will generate a layout table that is 3 columns by 2 rows"
		+ "// with some spanning included.  The basic layout will be:"
		+ "// +--------+-----------------+" + "// |   A    |   B             |"
		+ "// |        |--------+--------|" + "// |        |   C    |   D    |"
		+ "// +--------+--------+--------+" + "var table = new Ext.Panel({"
		+ "    title: 'Table Layout'," + "    layout:'table',"
		+ "    defaults: {" + "        // applied to each contained panel"
		+ "        bodyStyle:'padding:20px'" + "    }," + "    layoutConfig: {"
		+ "        // The total column count must be specified here"
		+ "        columns: 3" + "    }," + "    items: [{"
		+ "        html: '<p>Cell A content</p>'," + "        rowspan: 2"
		+ "    },{" + "        html: '<p>Cell B content</p>',"
		+ "        colspan: 2" + "    },{"
		+ "        html: '<p>Cell C content</p>'" + "    },{"
		+ "        html: '<p>Cell D content</p>'" + "    }]" + "});")
public class TableLayout extends ContainerLayout {
	private Integer columns;

	public Integer getColumns() {
		return columns;
	}

	@Description("columns : Number \n"
			+ "The total number of columns to create in the table for this layout. If not specified, all panels added to this layout will be rendered into a single row using a column per panel. ")
	public void setColumns(Integer columns) {
		this.columns = columns;
	}
}
