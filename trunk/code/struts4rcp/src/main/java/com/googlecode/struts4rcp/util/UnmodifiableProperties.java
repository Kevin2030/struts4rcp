package com.googlecode.struts4rcp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 不可修改Properties代理类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class UnmodifiableProperties extends Properties {

	private static final long serialVersionUID = 1L;

	private final Properties properties;

	public UnmodifiableProperties(Properties properties) {
		this.properties = properties;
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public Object clone() {
		return properties.clone();
	}

	public boolean contains(Object value) {
		return properties.contains(value);
	}

	public boolean containsKey(Object key) {
		return properties.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return properties.containsValue(value);
	}

	public Enumeration<Object> elements() {
		return properties.elements();
	}

	public Set<Entry<Object, Object>> entrySet() {
		return properties.entrySet();
	}

	public boolean equals(Object o) {
		return properties.equals(o);
	}

	public Object get(Object key) {
		return properties.get(key);
	}

	public String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public int hashCode() {
		return properties.hashCode();
	}

	public boolean isEmpty() {
		return properties.isEmpty();
	}

	public Enumeration<Object> keys() {
		return properties.keys();
	}

	public Set<Object> keySet() {
		return properties.keySet();
	}

	public void list(PrintStream out) {
		properties.list(out);
	}

	public void list(PrintWriter out) {
		properties.list(out);
	}

	public void load(InputStream inStream) throws IOException {
		throw new UnsupportedOperationException();
	}

	public void loadFromXML(InputStream in) throws IOException,
			InvalidPropertiesFormatException {
		throw new UnsupportedOperationException();
	}

	public Enumeration<?> propertyNames() {
		return properties.propertyNames();
	}

	public Object put(Object key, Object value) {
		throw new UnsupportedOperationException();
	}

	public void putAll(Map<? extends Object, ? extends Object> t) {
		throw new UnsupportedOperationException();
	}

	public Object remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	public void save(OutputStream out, String comments) {
		properties.save(out, comments);
	}

	public Object setProperty(String key, String value) {
		throw new UnsupportedOperationException();
	}

	public int size() {
		return properties.size();
	}

	public void store(OutputStream out, String comments) throws IOException {
		properties.store(out, comments);
	}

	public void storeToXML(OutputStream os, String comment, String encoding)
			throws IOException {
		properties.storeToXML(os, comment, encoding);
	}

	public void storeToXML(OutputStream os, String comment) throws IOException {
		properties.storeToXML(os, comment);
	}

	public String toString() {
		return properties.toString();
	}

	public Collection<Object> values() {
		return properties.values();
	}

}
