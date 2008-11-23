package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.Serializer;
import com.googlecode.struts4rcp.util.serializer.XmlSerializer;

public class XmlSerializerTestCase extends SerializerTestCase {

	protected Serializer createSerializer() {
		return new XmlSerializer();
	}

}
