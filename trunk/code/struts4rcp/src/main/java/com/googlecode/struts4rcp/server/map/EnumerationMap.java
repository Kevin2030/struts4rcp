package com.googlecode.struts4rcp.server.map;

import java.io.Serializable;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 适配于Enumeration的Map基类
 * (功能与java.util.AbstractMap类似, 提供另一种模板方法基类)
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class EnumerationMap<K,V> implements Map<K,V> {

	public boolean containsKey(Object key) {
		return keySet().contains(key);
	}

	public boolean containsValue(Object value) {
		return values().contains(value);
	}

	@SuppressWarnings("unchecked")
	public V get(Object key) {
		return getValue((K)key);
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> entrySet = new HashSet<Map.Entry<K, V>>();
		for (Enumeration<K> keys = getKeys(); keys.hasMoreElements();) {
			K name = keys.nextElement();
			entrySet.add(new MapEntry<K, V>(name, getValue(name)));
		}
		return entrySet;
	}

	public Set<K> keySet() {
		Set<K> keySet = new HashSet<K>();
		for (Enumeration<K> keys = getKeys(); keys.hasMoreElements();) {
			keySet.add(keys.nextElement());
		}
		return keySet;
	}

	public Collection<V> values() {
		Set<V> values = new HashSet<V>();
		for (Enumeration<K> keys = getKeys(); keys.hasMoreElements();) {
			values.add(getValue(keys.nextElement()));
		}
		return values;
	}

	public int size() {
		return keySet().size();
	}

	public boolean isEmpty() {
		return keySet().size() == 0;
	}

	public V put(K key, V value) {
		V old = getValue(key);
		putValue(key, value);
		return old;
	}

	public void putAll(Map<? extends K, ? extends V> t) {
		for (Iterator<? extends Map.Entry<? extends K, ? extends V>> i = t.entrySet().iterator(); i.hasNext();) {
            Map.Entry<? extends K, ? extends V> e = i.next();
            put(e.getKey(), e.getValue());
        }
	}

	@SuppressWarnings("unchecked")
	public V remove(Object key) {
		V old = getValue((K)key);
		removeValue((K)key);
		return old;
	}

	public void clear() {
		for (Iterator<K> i = keySet().iterator(); i.hasNext();) {
			removeValue(i.next());
		}
	}

	@Override
	public String toString() {
		return entrySet().toString();
	}

	protected abstract Enumeration<K> getKeys();

	protected abstract V getValue(K key);

	protected abstract void putValue(K key, V value);

	protected abstract void removeValue(K key);

	/**
	 * Map.Entry的实现类
	 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
	 * @param <K> 索引
	 * @param <V> 值
	 */
	private static class MapEntry<K, V> implements Map.Entry<K, V>, Serializable {

		private static final long serialVersionUID = 1L;

		private final K key;

		private V value;

		public MapEntry(K key, V value) {
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
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		@Override
		public String toString() {
			return key + ":" + value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final MapEntry other = (MapEntry) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

	}
}
