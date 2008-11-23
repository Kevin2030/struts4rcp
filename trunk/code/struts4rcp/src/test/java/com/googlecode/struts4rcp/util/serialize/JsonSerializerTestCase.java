package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.JsonSerializer;
import com.googlecode.struts4rcp.util.serializer.Serializer;

public class JsonSerializerTestCase extends SerializerTestCase {

	protected Serializer createSerializer() {
		return new JsonSerializer();
	}

	public void testSerializeChar() throws Exception {
		byte[] bytes = serializer.serialize('A');
		String result = (String)serializer.deserialize(bytes); // JSON的Char类型将被转换成String
		assertEquals("A", result);
	}

	public void testSerializeFloat() throws Exception {
		byte[] bytes = serializer.serialize(111111111.11F);
		Double result = (Double)serializer.deserialize(bytes); // JSON的Float类型将被转换成Double
		assertEquals(Double.valueOf(111111111.11F), result);
	}

}
