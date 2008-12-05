package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.JavaStreamSerializer;
import com.googlecode.struts4rcp.util.serializer.StreamSerializer;

public class JavaSerializerTestCase extends SerializerTestCase {

	protected StreamSerializer createSerializer() {
		return new JavaStreamSerializer();
	}

}
