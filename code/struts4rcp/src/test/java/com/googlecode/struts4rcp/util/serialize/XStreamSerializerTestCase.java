package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.Serializer;
import com.googlecode.struts4rcp.util.serializer.XStreamSerializer;

public class XStreamSerializerTestCase extends SerializerTestCase {

	protected Serializer createSerializer() {
		return new XStreamSerializer();
	}

}
