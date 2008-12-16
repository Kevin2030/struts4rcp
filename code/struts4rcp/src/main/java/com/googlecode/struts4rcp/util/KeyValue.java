package com.googlecode.struts4rcp.util;

import java.io.Serializable;
import java.util.Map;

/**
 * 键值对
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <K> 键类型
 * @param <V> 值类型
 */
public class KeyValue<K extends Serializable, V extends Serializable> implements Map.Entry<K, V>, Serializable {

	private static final long serialVersionUID = 8263705171443714691L;

	private final K key;

	private final V value;

	public KeyValue(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public V setValue(V value) {
		throw new UnsupportedOperationException();
	}

}
