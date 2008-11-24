package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("Base class for any visual Ext.Component that uses a box container. BoxComponent provides automatic box model adjustments for sizing and positioning and will work correctly withnin the Component rendering model. All container classes should subclass BoxComponent so that they will work consistently when nested within other Ext layout containers. ")
public class BoxComponent extends Component {
	private Boolean autoHeight;
	private Boolean autoWidth;
	private String height;
	private String width;

	public Boolean isAutoHeight() {
		return autoHeight;
	}

	@Description("autoHeight : Boolean \n"
			+ "True to use height:'auto', false to use fixed height (defaults to false). ")
	public void setAutoHeight(Boolean autoHeight) {
		this.autoHeight = autoHeight;
	}

	public Boolean isAutoWidth() {
		return autoWidth;
	}

	@Description("autoWidth : Boolean \n"
			+ "True to use width:'auto', false to use fixed width (defaults to false). ")
	public void setAutoWidth(Boolean autoWidth) {
		this.autoWidth = autoWidth;
	}

	@Variable
	public String getHeight() {
		return height;
	}

	@Description("height : Number \n"
			+ "The height of this component in pixels (defaults to auto). ")
	public void setHeight(String height) {
		this.height = height;
	}

	@Variable
	public String getWidth() {
		return width;
	}

	@Description("width : Number \n"
			+ "The width of this component in pixels (defaults to auto). ")
	public void setWidth(String width) {
		this.width = width;
	}

}
