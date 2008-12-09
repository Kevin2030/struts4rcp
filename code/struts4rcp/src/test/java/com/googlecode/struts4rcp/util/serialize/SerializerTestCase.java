package com.googlecode.struts4rcp.util.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import junit.framework.TestCase;

import com.googlecode.struts4rcp.util.serializer.stream.StreamSerializer;

public abstract class SerializerTestCase extends TestCase {

	protected StreamSerializer serializer = createSerializer();

	protected abstract StreamSerializer createSerializer();

	public void testSerializeNull() throws Exception {
		System.out.println(String.valueOf(Integer.MAX_VALUE).length());
		byte[] bytes = serialize(null);
		Object result = deserialize(bytes);
		assertNull(result);
	}

	public void testSerializeBoolean() throws Exception {
		byte[] bytes = serialize(true);
		Boolean result = (Boolean)deserialize(bytes);
		assertEquals(Boolean.TRUE, result);
	}

	public void testSerializeChar() throws Exception {
		byte[] bytes = serialize('A');
		Character result = (Character)deserialize(bytes);
		assertEquals(Character.valueOf('A'), result);
	}

	public void testSerializeString() throws Exception {
		byte[] bytes = serialize("liangfei");
		String result = (String)deserialize(bytes);
		assertEquals("liangfei", result);
	}

	public void testSerializeInteger() throws Exception {
		byte[] bytes = serialize(13);
		Integer result = (Integer)deserialize(bytes);
		assertEquals(Integer.valueOf(13), result);
	}

	public void testSerializeLong() throws Exception {
		byte[] bytes = serialize(1111111111111111111L);
		Long result = (Long)deserialize(bytes);
		assertEquals(Long.valueOf(1111111111111111111L), result);
	}

	public void testSerializeFloat() throws Exception {
		byte[] bytes = serialize(111111111.11F);
		Float result = (Float)deserialize(bytes);
		assertEquals(Float.valueOf(111111111.11F), result);
	}

	public void testSerializeDouble() throws Exception {
		byte[] bytes = serialize(1111111111111111111.11);
		Double result = (Double)deserialize(bytes);
		assertEquals(Double.valueOf(1111111111111111111.11), result);
	}

	public void testSerializeObject() throws Exception {
		User user = new User();
		user.setId(123L);
		user.setUsername("liangfei");
		user.setPassword("123456");
		user.setEmail("liangfei0201@gmail.com");
		byte[] bytes = serialize(user);
		User result = (User)deserialize(bytes);
		assertEquals(Long.valueOf(123L), result.getId());
		assertEquals("liangfei", result.getUsername());
		assertEquals("123456", result.getPassword());
		assertEquals("liangfei0201@gmail.com", result.getEmail());
	}

	private byte[] serialize(Serializable obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		serializer.serialize(obj, out);
		return out.toByteArray();
	}

	private Serializable deserialize(byte[] obj) throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(obj);
		return serializer.deserialize(Serializable.class, in);
	}

}
