package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

public class SelectionAction extends GridAction {

	@Override
	protected String getParamsName() {
		return "selected";
	}

	@Override
	protected String getDataFunction() {
		return "getSelectionModel().getSelections()";
	}
	
}
