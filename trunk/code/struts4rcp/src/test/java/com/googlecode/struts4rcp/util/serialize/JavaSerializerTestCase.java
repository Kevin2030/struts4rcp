package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.JavaSerializer;
import com.googlecode.struts4rcp.util.serializer.Serializer;

public class JavaSerializerTestCase extends SerializerTestCase {

	protected Serializer createSerializer() {
		return new JavaSerializer();
	}

}
