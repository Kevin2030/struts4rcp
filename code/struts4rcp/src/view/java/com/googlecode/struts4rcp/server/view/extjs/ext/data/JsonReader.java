package com.googlecode.struts4rcp.server.view.extjs.ext.data;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("var Employee = Ext.data.Record.create(["
		+ " {name: 'name', mapping: 'name'},     // 'mapping' property not needed if it's the same as 'name'"
		+ " {name: 'occupation'}                 // This field will use 'occupation' as the mapping."
		+ "]);"
		+ "var myReader = new Ext.data.JsonReader({"
		+ "    totalProperty: 'results',    // The property which contains the total dataset size (optional)"
		+ "    root: 'rows',                // The property which contains an Array of row objects"
		+ "    id: 'id'                     // The property within each row object that provides an ID for the record (optional)"
		+ "}, Employee);"
		+ "This would consume a JSON file like this:"

		+ "{ 'results': 2, 'rows': ["
		+ "    { 'id': 1, 'name': 'Bill', occupation: 'Gardener' },"
		+ "    { 'id': 2, 'name': 'Ben', occupation: 'Horticulturalist' } ]"
		+ "}"
		+ "It is possible to change a JsonReader's metadata at any time by including a MetaData property in the data object. If this is detected in the object, a Store object using this Reader will fire its metachange event."

		+ "The MetaData property may contain any of the configuration options for this class. Additionally, it may contain a fields property which the JsonReader will use that as an argument to Ext.data.Record.create to configure the layout of the Records which it will produce."

		+ "Using the MetaData property, and the Store's metachange event, it is possible to have a Store-driven control initialize itself. The metachange event handler may interrogate the MetaData property (which may contain any user-defined properties needed) and the MetaData.fields property to perform any configuration required."

		+ "To use this facility to send the same data as the above example without having to code the creation of the Record constructor, you would create the JsonReader like this:"

		+ "var myReader = new Ext.data.JsonReader();"
		+ "The first data packet from the server would configure the reader by containing a metaData property as well as the data:"

		+ "{" + "  'metaData': {" + "    totalProperty: 'results',"
		+ "    root: 'rows'," + "    id: 'id'," + "    fields: ["
		+ "      {name: 'name'}," + "      {name: 'occupation'} ]" + "   },"
		+ "  'results': 2, 'rows': ["
		+ "    { 'id': 1, 'name': 'Bill', occupation: 'Gardener' },"
		+ "    { 'id': 2, 'name': 'Ben', occupation: 'Horticulturalist' } ]"
		+ "}")
public class JsonReader extends DataReader {
	private String id;
	private String root;
	private String successProperty;
	private String totalProperty;
	private String record;

	public String getId() {
		return id;
	}

	@Description("id : String"
			+ "Name of the property within a row object that contains a record identifier value. ")
	public void setId(String id) {
		this.id = id;
	}

	public String getRoot() {
		return root;
	}

	@Description("root : String"
			+ "name of the property which contains the Array of row objects")
	public void setRoot(String root) {
		this.root = root;
	}

	public String getSuccessProperty() {
		return successProperty;
	}

	@Description("successProperty : String"
			+ "Name of the property from which to retrieve the success attribute used by forms. ")
	public void setSuccessProperty(String successProperty) {
		this.successProperty = successProperty;
	}

	public String getTotalProperty() {
		return totalProperty;
	}

	@Override
	protected String getComponentAfter() {
		return "," + getRecord();
	}

	@Description("totalProperty : String "
			+ "Name of the property from which to retrieve the total number of records in the dataset. This is only needed if the whole dataset is not passed in one go, but is being paged from the remote server. ")
	public void setTotalProperty(String totalProperty) {
		this.totalProperty = totalProperty;
	}

	protected String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}
}
