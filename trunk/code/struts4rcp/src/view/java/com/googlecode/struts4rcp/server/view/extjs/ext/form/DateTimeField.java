package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 * 日期与时间的组合字段. (此类为扩展实现, 不属于EXT2.0, 需要添加ext-datetime.js)
 *
 * @author Achievo: LiangFei
 *
 */
public class DateTimeField extends TriggerField {

	private static final long serialVersionUID = 1L;

	private String format;
	private String altFormats;
	private String disabledDays;
	private String disabledDaysText;
	private String disabledDates;
	private String disabledDatesText;
	private String minValue;
	private String maxValue;
	private String minText;
	private String maxText;
	private String invalidText;
	private String triggerClass;
	private String defaultAutoCreate;

	public String getFormat() {
		if (format == null)
			format = "Y-m-d H:i";
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getAltFormats() {
		return altFormats;
	}

	public void setAltFormats(String altFormats) {
		this.altFormats = altFormats;
	}

	@Variable
	public String getDisabledDays() {
		return disabledDays;
	}

	public void setDisabledDays(String disabledDays) {
		this.disabledDays = disabledDays;
	}

	public String getDisabledDaysText() {
		return disabledDaysText;
	}

	public void setDisabledDaysText(String disabledDaysText) {
		this.disabledDaysText = disabledDaysText;
	}

	@Variable
	public String getDisabledDates() {
		return disabledDates;
	}

	public void setDisabledDates(String disabledDates) {
		this.disabledDates = disabledDates;
	}

	public String getDisabledDatesText() {
		return disabledDatesText;
	}

	public void setDisabledDatesText(String disabledDatesText) {
		this.disabledDatesText = disabledDatesText;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinText() {
		return minText;
	}

	public void setMinText(String minText) {
		this.minText = minText;
	}

	public String getMaxText() {
		return maxText;
	}

	public void setMaxText(String maxText) {
		this.maxText = maxText;
	}

	public String getInvalidText() {
		return invalidText;
	}

	public void setInvalidText(String invalidText) {
		this.invalidText = invalidText;
	}

	public String getTriggerClass() {
		return triggerClass;
	}

	public void setTriggerClass(String triggerClass) {
		this.triggerClass = triggerClass;
	}

	public String getDefaultAutoCreate() {
		return defaultAutoCreate;
	}

	public void setDefaultAutoCreate(String defaultAutoCreate) {
		this.defaultAutoCreate = defaultAutoCreate;
	}

}
