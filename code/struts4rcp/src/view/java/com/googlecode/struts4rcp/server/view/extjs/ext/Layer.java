package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("An extended Ext.Element object that supports a shadow and shim, constrain to viewport and automatic maintaining of shadow/shim positions. ")
public class Layer extends Element {
	private String cls;
	private Boolean constrain;
	private String dh;
	private String shadow;
	private Integer shadowOffset;
	private Boolean shim;
	private Integer zindex;

	public String getCls() {
		return cls;
	}

	@Description("cls : String \n" + "CSS class to add to the element ")
	public void setCls(String cls) {
		this.cls = cls;
	}

	public Boolean isConstrain() {
		return constrain;
	}

	@Description("constrain : Boolean \n"
			+ "False to disable constrain to viewport (defaults to true) ")
	public void setConstrain(Boolean constrain) {
		this.constrain = constrain;
	}

	public String getDh() {
		return dh;
	}

	@Description("dh : Object \n"
			+ "DomHelper object config to create element with (defaults to {tag: 'div', cls: 'x-layer'}). ")
	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getShadow() {
		return shadow;
	}

	@Description("shadow : String/Boolean \n"
			+ "True to create a shadow element with default class 'x-layer-shadow', or you can pass a string with a CSS class name. False turns off the shadow. ")
	public void setShadow(String shadow) {
		this.shadow = shadow;
	}

	public Integer getShadowOffset() {
		return shadowOffset;
	}

	@Description("shadowOffset : Number \n"
			+ "Number of pixels to offset the shadow (defaults to 3) ")
	public void setShadowOffset(Integer shadowOffset) {
		this.shadowOffset = shadowOffset;
	}

	public Boolean isShim() {
		return shim;
	}

	@Description("shim : Boolean \n"
			+ "False to disable the iframe shim in browsers which need one (defaults to true) ")
	public void setShim(Boolean shim) {
		this.shim = shim;
	}

	public Integer getZindex() {
		return zindex;
	}

	@Description("zindex : Number \n" + "Starting z-index (defaults to 11000) ")
	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}

}
