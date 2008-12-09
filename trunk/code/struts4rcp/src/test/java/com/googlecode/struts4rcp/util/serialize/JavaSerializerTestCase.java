package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.stream.JavaStreamSerializer;
import com.googlecode.struts4rcp.util.serializer.stream.StreamSerializer;

public class JavaSerializerTestCase extends SerializerTestCase {

	protected StreamSerializer createSerializer() {
		return new JavaStreamSerializer();
	}

}
