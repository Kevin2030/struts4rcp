package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("A split button that provides a built-in dropdown arrow that can fire an event separately from the default click event of the button. Typically this would be used to display a dropdown menu that provides additional options to the primary button action, but any custom handler can provide the arrowclick implementation. ")
public class SplitButton extends Button {
	private String arrowHandler;
	private String arrowTooltip;

	@Override
	public String getCls() { // 取消按钮默认样式
		String cls = super.getCls();
		if ("x-btn-normal".equals(cls))
			return null;
		return cls;
	}

	@Variable
	public String getArrowHandler() {
		return arrowHandler;
	}

	@Description("arrowHandler : Function \n"
			+ "A function called when the arrow button is clicked (can be used instead of click event) ")
	public void setArrowHandler(String arrowHandler) {
		this.arrowHandler = arrowHandler;
	}

	public String getArrowTooltip() {
		return arrowTooltip;
	}

	@Description("arrowTooltip : String \n"
			+ "The title attribute of the arrow ")
	public void setArrowTooltip(String arrowTooltip) {
		this.arrowTooltip = arrowTooltip;
	}

}
