package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("An updateable progress bar component. The progress bar supports two different modes: manual and automatic."
		+ "In manual mode, you are responsible for showing, updating (via updateProgress) and clearing the progress bar as needed from your own code. This method is most appropriate when you want to show progress throughout an operation that has predictable points of interest at which you can update the control."
		+ "In automatic mode, you simply call wait and let the progress bar run indefinitely, only clearing it once the operation is complete. You can optionally have the progress bar wait for a specific amount of time and then clear itself. Automatic mode is most appropriate for timed operations or asymchronous operations in which you have no need for indicating intermediate progress.")
public class ProgressBar extends BoxComponent {
	private String baseCls;
	private String text;
	private String textEl;
	private Float value;

	public String getBaseCls() {
		return baseCls;
	}

	@Description("baseCls : String \n"
			+ "The base CSS class to apply to the progress bar's wrapper element (defaults to 'x-progress') ")
	public void setBaseCls(String baseCls) {
		this.baseCls = baseCls;
	}

	public String getText() {
		return text;
	}

	@Description("text : String \n" + "The progress bar text (defaults to '') ")
	public void setText(String text) {
		this.text = text;
	}

	public String getTextEl() {
		return textEl;
	}

	@Description("textEl : Mixed \n"
			+ "The element to render the progress text to (defaults to the progress bar's internal text element) ")
	public void setTextEl(String textEl) {
		this.textEl = textEl;
	}

	public Float getValue() {
		return value;
	}

	@Description("value : Float \n"
			+ "A floating point value between 0 and 1 (e.g., .5, defaults to 0) ")
	public void setValue(Float value) {
		this.value = value;
	}
}
