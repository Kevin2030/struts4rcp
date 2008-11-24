package com.googlecode.struts4rcp.server.view.extjs.ext.data;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("Small helper class to make creating Stores for JSON data easier. "
		+ "var store = new Ext.data.JsonStore({"
		+ "    url: 'get-images.php',"
		+ "    root: 'images',"
		+ "    fields: ['name', 'url', {name:'size', type: 'float'}, {name:'lastmod', type:'date'}]"
		+ "});"
		+ "This would consume a returned object of the form:"
		+ "{"
		+ "    images: ["
		+ "        {name: 'Image one', url:'/GetImage.php?id=1', size:46.5, lastmod: new Date(2007, 10, 29)},"
		+ "        {name: 'Image Two', url:'/GetImage.php?id=2', size:43.2, lastmod: new Date(2007, 10, 30)}"
		+ "    ]"
		+ "}"
		+ "An object literal of this form could also be used as the data config option. Note: Although they are not listed, this class inherits all of the config options of Store, JsonReader.")
public class JsonStore extends Store {

	private static final long serialVersionUID = -54238578795124747L;

	private String fields;

	private String root;

	@Variable
	public String getFields() {
		return fields;
	}

	@Description("fields : Array \nEither an Array of field definition objects as passed to Ext.data.Record.create, or a Record constructor created using Ext.data.Record.create. ")
	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getRoot() {
		return root;
	}

	@Description("root : String \n json list root")
	public void setRoot(String root) {
		this.root = root;
	}

}
