package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("This class provides the basic implementation for single cell selection in a grid. The object stored as the selection and returned by getSelectedCell contains the following properties:"
		+ "record : Ext.data.record"
		+ "The Record which provides the data for the row containing the selection"
		+ "cell : Ext.data.record"
		+ "An object containing the following properties:"
		+ "rowIndex : Number"
		+ "The index of the selected row"
		+ "cellIndex : Number"
		+ "The index of the selected cell"
		+ "Note that due to possible column reordering, the cellIndex should not be used as an index into the Record's data. Instead, the name of the selected field should be determined in order to retrieve the data value from the record by name:"
		+ "var fieldName = grid.getColumnModel().getDataIndex(cellIndex);"
		+ "var data = record.get(fieldName);")
public class CellSelectionModel extends AbstractSelectionModel {

}
