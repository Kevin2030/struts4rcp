package com.googlecode.struts4rcp.server.map;

import java.util.Enumeration;
import java.util.Vector;

import com.googlecode.struts4rcp.util.BeanUtils;

/**
 * Bean对象的属性Map封装，识别getter方法作为集合.
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ModelMap extends EnumerationMap<String, Object> {

	private static final long serialVersionUID = 1L;

	private Vector<String> vet = new Vector<String>();

	private final Object model;

	public ModelMap(Object model) {
		if (model == null)
			throw new NullPointerException("model == null");
		this.model = model;
		this.vet.addAll(BeanUtils.getProperties(model.getClass()));
	}

	protected Enumeration<String> getKeys() {
		return vet.elements();
	}

	protected Object getValue(String key) {
		try {
			return BeanUtils.getProperty(model, key);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void putValue(String key, Object value) {
		throw new UnsupportedOperationException();
	}

	protected void removeValue(String key) {
		throw new UnsupportedOperationException();
	}

}
