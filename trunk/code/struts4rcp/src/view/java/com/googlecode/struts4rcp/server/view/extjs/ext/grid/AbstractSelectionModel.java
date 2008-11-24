package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

@Description("Abstract base class for grid SelectionModels. It provides the interface that should be implemented by descendant classes. This class should not be directly instantiated. ")
public class AbstractSelectionModel extends Observable {

	@Override
	protected String getDefaultKey() {
		return "sm";
	}

}
