package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
public class DatePicker extends Component {
	private String cancelText;
	private Boolean constrainToViewport;
	private String dayNames;
	private String disabledDatesRE;
	private String disabledDatesText;
	private String disabledDays;
	private String disabledDaysText;
	private String format;
	private String maxDate;
	private String maxText;
	private String minDate;
	private String minText;
	private String monthNames;
	private String monthYearText;
	private String nextText;
	private String okText;
	private String prevText;
	private Integer startDay;
	private String todayText;
	private String todayTip;

	public String getCancelText() {
		return cancelText;
	}

	@Description("cancelText : String \n"
			+ "The text to display on the cancel button ")
	public void setCancelText(String cancelText) {
		this.cancelText = cancelText;
	}

	public Boolean isConstrainToViewport() {
		return constrainToViewport;
	}

	@Description("constrainToViewport : Boolean \n"
			+ "True to constrain the date picker to the viewport (defaults to true) ")
	public void setConstrainToViewport(Boolean constrainToViewport) {
		this.constrainToViewport = constrainToViewport;
	}

	@Variable
	public String getDayNames() {
		return dayNames;
	}

	@Description("dayNames : Array \n"
			+ "An array of textual day names which can be overriden for localization support (defaults to Date.dayNames) ")
	public void setDayNames(String dayNames) {
		this.dayNames = dayNames;
	}

	public String getDisabledDatesRE() {
		return disabledDatesRE;
	}

	@Description("disabledDatesRE : RegExp \n"
			+ "JavaScript regular expression used to disable a pattern of dates (defaults to null) ")
	public void setDisabledDatesRE(String disabledDatesRE) {
		this.disabledDatesRE = disabledDatesRE;
	}

	public String getDisabledDatesText() {
		return disabledDatesText;
	}

	@Description("disabledDatesText : String \n"
			+ "The tooltip text to display when the date falls on a disabled date (defaults to '') ")
	public void setDisabledDatesText(String disabledDatesText) {
		this.disabledDatesText = disabledDatesText;
	}

	@Variable
	public String getDisabledDays() {
		return disabledDays;
	}

	@Description("disabledDays : Array \n"
			+ "An array of days to disable, 0-based. For example, [0, 6] disables Sunday and Saturday (defaults to null). ")
	public void setDisabledDays(String disabledDays) {
		this.disabledDays = disabledDays;
	}

	public String getDisabledDaysText() {
		return disabledDaysText;
	}

	@Description("disabledDaysText : String \n"
			+ "The tooltip to display when the date falls on a disabled day (defaults to '') ")
	public void setDisabledDaysText(String disabledDaysText) {
		this.disabledDaysText = disabledDaysText;
	}

	public String getFormat() {
		return format;
	}

	@Description("  format : String \n"
			+ "The default date format string which can be overriden for localization support. The format must be valid according to Date.parseDate (defaults to 'm/d/y').  ")
	public void setFormat(String format) {
		this.format = format;
	}

	@Variable
	public String getMaxDate() {
		return maxDate;
	}

	@Description("maxDate : Date \n"
			+ "Maximum allowable date (JavaScript date object, defaults to null) ")
	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public String getMaxText() {
		return maxText;
	}

	@Description("maxText : String \n"
			+ "The error text to display if the maxDate validation fails (defaults to 'This date is after the maximum date') ")
	public void setMaxText(String maxText) {
		this.maxText = maxText;
	}

	@Variable
	public String getMinDate() {
		return minDate;
	}

	@Description("minDate : Date \n"
			+ "Minimum allowable date (JavaScript date object, defaults to null) ")
	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getMinText() {
		return minText;
	}

	@Description("minText : String \n"
			+ "The error text to display if the minDate validation fails (defaults to 'This date is before the minimum date') ")
	public void setMinText(String minText) {
		this.minText = minText;
	}

	@Variable
	public String getMonthNames() {
		return monthNames;
	}

	@Description("monthNames : Array \n"
			+ "An array of textual month names which can be overriden for localization support (defaults to Date.monthNames) ")
	public void setMonthNames(String monthNames) {
		this.monthNames = monthNames;
	}

	public String getMonthYearText() {
		return monthYearText;
	}

	@Description("monthYearText : String \n"
			+ "The header month selector tooltip (defaults to 'Choose a month (Control+Up/Down to move years)') ")
	public void setMonthYearText(String monthYearText) {
		this.monthYearText = monthYearText;
	}

	public String getNextText() {
		return nextText;
	}

	@Description("nextText : String \n"
			+ "The next month navigation button tooltip (defaults to 'Next Month (Control+Right)') ")
	public void setNextText(String nextText) {
		this.nextText = nextText;
	}

	public String getOkText() {
		return okText;
	}

	@Description("okText : String \n" + "The text to display on the ok button ")
	public void setOkText(String okText) {
		this.okText = okText;
	}

	public String getPrevText() {
		return prevText;
	}

	@Description("prevText : String \n"
			+ "The previous month navigation button tooltip (defaults to 'Previous Month (Control+Left)') ")
	public void setPrevText(String prevText) {
		this.prevText = prevText;
	}

	public Integer getStartDay() {
		return startDay;
	}

	@Description("startDay : Number \n"
			+ "Day index at which the week should begin, 0-based (defaults to 0, which is Sunday) ")
	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}

	public String getTodayText() {
		return todayText;
	}

	@Description("todayText : String \n"
			+ "The text to display on the button that selects the current date (defaults to 'Today')")
	public void setTodayText(String todayText) {
		this.todayText = todayText;
	}

	public String getTodayTip() {
		return todayTip;
	}

	@Description("todayTip : String \n"
			+ "The tooltip to display for the button that selects the current date (defaults to '{current date} (Spacebar)') ")
	public void setTodayTip(String todayTip) {
		this.todayTip = todayTip;
	}
}
