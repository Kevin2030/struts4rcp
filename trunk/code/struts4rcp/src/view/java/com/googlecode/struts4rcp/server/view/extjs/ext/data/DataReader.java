package com.googlecode.struts4rcp.server.view.extjs.ext.data;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("Abstract base class for reading structured data from a data source and converting it into an object containing Ext.data.Record objects and metadata for use by an Ext.data.Store. This class is intended to be extended and should not be created directly. For existing implementations, see Ext.data.ArrayReader, Ext.data.JsonReader and Ext.data.XmlReader. ")
public class DataReader extends ComponentTag {

	@Override
	protected String getDefaultKey() {
		return "reader";
	}

}
