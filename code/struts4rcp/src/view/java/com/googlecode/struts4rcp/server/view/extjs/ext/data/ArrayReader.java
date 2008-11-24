package com.googlecode.struts4rcp.server.view.extjs.ext.data;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("Data reader class to create an Array of Ext.data.Record objects from an Array."
		+ " Each element of that Array represents a row of data fields."
		+ " The fields are pulled into a Record object using as a subscript, "
		+ "the mapping property of the field definition if it exists, "
		+ "or the field's ordinal position in the definition Example code:. "
		+ "var Employee = Ext.data.Record.create(["
		+ "{name: 'name', mapping: 1},         // 'mapping' only needed if an 'id' field is present which"
		+ "{name: 'occupation', mapping: 2}    // precludes using the ordinal position as the index."
		+ "	]);"
		+ "var myReader = new Ext.data.ArrayReader({"
		+ "id: 0                     // The subscript within row Array that provides an ID for the Record (optional)"
		+ "}, Employee);"
		+ "This would consume an Array like this: "
		+ "[ [1, 'Bill', 'Gardener'], [2, 'Ben', 'Horticulturalist'] ]")
public class ArrayReader extends DataReader {
	private String id;

	@Variable
	public String getId() {
		return id;
	}

	@Description("id:String \n(optional) The subscript within row Array that provides an ID for the Record ")
	public void setId(String id) {
		this.id = id;
	}
}
