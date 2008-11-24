package com.googlecode.struts4rcp.server.view.extjs.property;

import java.util.Collection;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.ListComponentTag;
import com.googlecode.struts4rcp.util.BeanUtils;
import com.googlecode.struts4rcp.util.ClassUtils;

public class Fields extends ListComponentTag {

	private static final long serialVersionUID = 1L;

	// 扩展属性，通过反射填充fields
	private String type;

	@Description("type : java.lang.Class \n 数据类型名, 如：com.sf.domain.User, 此属性将通过类元信息产生对应的fields")
	public void setType(String type) {
		this.type = type;
	}

	@Override
	protected String getComponentBegin() {
		if (type != null && type.trim().length() > 0) {
			type = type.trim();
			try {
				Class<?> typeClass = ClassUtils.forName(type);
				Collection<String> props = BeanUtils.getProperties(typeClass);
				StringBuilder buf = new StringBuilder();
				for (String prop : props) {
					appendComma(buf);
					buf.append("{name: \"" + prop + "\"}");
				}
				return buf.toString();
			} catch (Exception e) {
				// ignore
			}
		}
		return null;
	}

}
