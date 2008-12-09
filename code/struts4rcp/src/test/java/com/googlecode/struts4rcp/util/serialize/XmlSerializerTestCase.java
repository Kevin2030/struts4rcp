package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.stream.StreamSerializer;
import com.googlecode.struts4rcp.util.serializer.stream.XmlStreamSerializer;

public class XmlSerializerTestCase extends SerializerTestCase {

	protected StreamSerializer createSerializer() {
		return new XmlStreamSerializer();
	}

}
