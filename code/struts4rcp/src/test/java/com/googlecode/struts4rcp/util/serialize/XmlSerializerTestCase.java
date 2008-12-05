package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.StreamSerializer;
import com.googlecode.struts4rcp.util.serializer.XmlStreamSerializer;

public class XmlSerializerTestCase extends SerializerTestCase {

	protected StreamSerializer createSerializer() {
		return new XmlStreamSerializer();
	}

}
