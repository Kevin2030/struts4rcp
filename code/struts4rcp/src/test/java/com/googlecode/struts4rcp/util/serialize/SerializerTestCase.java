package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.Serializer;

import junit.framework.TestCase;

public abstract class SerializerTestCase extends TestCase {

	protected Serializer serializer = createSerializer();

	protected abstract Serializer createSerializer();

	public void testSerializeNull() throws Exception {
		System.out.println(String.valueOf(Integer.MAX_VALUE).length());
		byte[] bytes = serializer.serialize(null);
		Object result = serializer.deserialize(bytes);
		assertNull(result);
	}

	public void testSerializeBoolean() throws Exception {
		byte[] bytes = serializer.serialize(true);
		Boolean result = (Boolean)serializer.deserialize(bytes);
		assertEquals(Boolean.TRUE, result);
	}

	public void testSerializeChar() throws Exception {
		byte[] bytes = serializer.serialize('A');
		Character result = (Character)serializer.deserialize(bytes);
		assertEquals(Character.valueOf('A'), result);
	}

	public void testSerializeString() throws Exception {
		byte[] bytes = serializer.serialize("liangfei");
		String result = (String)serializer.deserialize(bytes);
		assertEquals("liangfei", result);
	}

	public void testSerializeInteger() throws Exception {
		byte[] bytes = serializer.serialize(13);
		Integer result = (Integer)serializer.deserialize(bytes);
		assertEquals(Integer.valueOf(13), result);
	}

	public void testSerializeLong() throws Exception {
		byte[] bytes = serializer.serialize(1111111111111111111L);
		Long result = (Long)serializer.deserialize(bytes);
		assertEquals(Long.valueOf(1111111111111111111L), result);
	}

	public void testSerializeFloat() throws Exception {
		byte[] bytes = serializer.serialize(111111111.11F);
		Float result = (Float)serializer.deserialize(bytes);
		assertEquals(Float.valueOf(111111111.11F), result);
	}

	public void testSerializeDouble() throws Exception {
		byte[] bytes = serializer.serialize(1111111111111111111.11);
		Double result = (Double)serializer.deserialize(bytes);
		assertEquals(Double.valueOf(1111111111111111111.11), result);
	}

	public void testSerializeObject() throws Exception {
		User user = new User();
		user.setId(123L);
		user.setUsername("liangfei");
		user.setPassword("123456");
		user.setEmail("liangfei0201@gmail.com");
		byte[] bytes = serializer.serialize(user);
		User result = (User)serializer.deserialize(bytes);
		assertEquals(Long.valueOf(123L), result.getId());
		assertEquals("liangfei", result.getUsername());
		assertEquals("123456", result.getPassword());
		assertEquals("liangfei0201@gmail.com", result.getEmail());
	}

}
