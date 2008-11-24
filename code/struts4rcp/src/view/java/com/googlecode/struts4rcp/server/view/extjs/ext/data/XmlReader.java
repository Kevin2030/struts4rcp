package com.googlecode.struts4rcp.server.view.extjs.ext.data;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("Data reader class to create an Array of Ext.data.Record objects from an XML document based on mappings in a provided Ext.data.Record constructor."
		+ "Note that in order for the browser to parse a returned XML document, the Content-Type header in the HTTP response must be set to 'text/xml'."
		+ "Example code:"
		+ "var Employee = Ext.data.Record.create(["
		+ "   {name: 'name', mapping: 'name'},     // 'mapping' property not needed if it's the same as 'name' "
		+ "   {name: 'occupation'}                 // This field will use 'occupation' as the mapping."
		+ "]);"
		+ "var myReader = new Ext.data.XmlReader({"
		+ "   totalRecords: 'results', // The element which contains the total dataset size (optional)"
		+ "   record: 'row',           // The repeated element which contains row information"
		+ "   id: 'id'                 // The element within the row that provides an ID for the record (optional)"
		+ "}, Employee);"
		+ "This would consume an XML file like this:"
		+ "<?xml?>"
		+ "<dataset>"
		+ " <results>2</results>"
		+ " <row>"
		+ "   <id>1</id>"
		+ "   <name>Bill</name>"
		+ "   <occupation>Gardener</occupation>"
		+ " </row>"
		+ " <row>"
		+ "   <id>2</id>"
		+ "   <name>Ben</name>"
		+ "   <occupation>Horticulturalist</occupation>"
		+ " </row>"
		+ "</dataset>")
public class XmlReader extends DataReader {
	private String id;
	private String record;
	private String success;
	private String totalRecords;

	public String getId() {
		return id;
	}

	@Description("id : String "
			+ "The DomQuery path relative from the record element to the element that contains a record identifier value. ")
	public void setId(String id) {
		this.id = id;
	}

	@Variable
	public String getRecord() {
		return record;
	}

	@Description("record : String"
			+ "The DomQuery path to the repeated element which contains record information. ")
	public void setRecord(String record) {
		this.record = record;
	}

	public String getSuccess() {
		return success;
	}

	@Description("success : String"
			+ "The DomQuery path to the success attribute used by forms. ")
	public void setSuccess(String success) {
		this.success = success;
	}

	@Variable
	public String getTotalRecords() {
		return totalRecords;
	}

	@Description("totalRecords : String "
			+ "The DomQuery path from which to retrieve the total number of records in the dataset. This is only needed if the whole dataset is not passed in one go, but is being paged from the remote server. ")
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
}
