package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("Simple color palette class for choosing colors. The palette can be rendered to any container."
		+ "Here's an example of typical usage: "
		+ "var cp = new Ext.ColorPalette({value:'993300'});  // initial selected color "
		+ "cp.render('my-div'); "
		+ "cp.on('select', function(palette, selColor){ "
		+ "    // do something with selColor " + "});")
public class ColorPalette extends Component {
	private Boolean allowReselect;
	private String itemCls;
	private String value;

	public Boolean isAllowReselect() {
		return allowReselect;
	}

	@Description("allowReselect : Boolean \n"
			+ "If set to true then reselecting a color that is already selected fires the selection event ")
	public void setAllowReselect(Boolean allowReselect) {
		this.allowReselect = allowReselect;
	}

	public String getItemCls() {
		return itemCls;
	}

	@Description("itemCls : String \n"
			+ "The CSS class to apply to the containing element (defaults to 'x-color-palette') ")
	public void setItemCls(String itemCls) {
		this.itemCls = itemCls;
	}

	public String getValue() {
		return value;
	}

	@Description("value : String \n"
			+ "The initial color to highlight (should be a valid 6-digit color hex code without the # symbol). Note that the hex codes are case-sensitive. ")
	public void setValue(String value) {
		this.value = value;
	}

}
