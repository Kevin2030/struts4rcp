package com.googlecode.struts4rcp.server.view.extjs.ext.util;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("A wrapper class which can be applied to any element. Fires a 'click' event while the mouse is pressed. The interval between firings may be specified in the config but defaults to 20 milliseconds. Optionally, a CSS class may be applied to the element during the time it is pressed. ")
public class ClickRepeater extends Observable {
	private Boolean accelerate;
	private Integer delay;
	private String el;
	private String interval;
	private String pressClass;
	private Boolean preventDefault;
	private Boolean stopDefault;

	public Boolean isAccelerate() {
		return accelerate;
	}

	@Description("accelerate : Boolean \n"
			+ "True if autorepeating should start slowly and accelerate. 'interval' and 'delay' are ignored. ")
	public void setAccelerate(Boolean accelerate) {
		this.accelerate = accelerate;
	}

	public Integer getDelay() {
		return delay;
	}

	@Description("delay : Number \n"
			+ "The initial delay before the repeating event begins firing. Similar to an autorepeat key delay. ")
	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	public String getEl() {
		return el;
	}

	@Description("el : Mixed \n" + "The element to act as a button. ")
	public void setEl(String el) {
		this.el = el;
	}

	public String getInterval() {
		return interval;
	}

	@Description("interval : Number \n"
			+ "The interval between firings of the 'click' event. Default 20 ms. ")
	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getPressClass() {
		return pressClass;
	}

	@Description("pressClass : String \n"
			+ "A CSS class name to be applied to the element while pressed.")
	public void setPressClass(String pressClass) {
		this.pressClass = pressClass;
	}

	public Boolean isPreventDefault() {
		return preventDefault;
	}

	@Description("preventDefault : Boolean \n"
			+ "True to prevent the default click event ")
	public void setPreventDefault(Boolean preventDefault) {
		this.preventDefault = preventDefault;
	}

	public Boolean isStopDefault() {
		return stopDefault;
	}

	@Description("stopDefault : Boolean \n"
			+ "True to stop the default click event ")
	public void setStopDefault(Boolean stopDefault) {
		this.stopDefault = stopDefault;
	}
}
