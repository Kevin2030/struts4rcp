package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

public class ModificationAction extends GridAction {

	@Override
	protected String getParamsName() {
		return "modified";
	}

	@Override
	protected String getDataFunction() {
		return "getStore().getModifiedRecords()";
	}
	
}
