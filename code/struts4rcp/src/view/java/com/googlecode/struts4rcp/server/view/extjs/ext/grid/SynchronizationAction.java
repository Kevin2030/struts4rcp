package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

public class SynchronizationAction extends GridAction {

	@Override
	protected String getParamsName() {
		return "synchronized";
	}

	@Override
	protected String getDataFunction() {
		return "getStore().getRange()";
	}

}
