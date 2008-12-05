package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.StreamSerializer;
import com.googlecode.struts4rcp.util.serializer.XStreamSerializer;

public class XStreamSerializerTestCase extends SerializerTestCase {

	protected StreamSerializer createSerializer() {
		return new XStreamSerializer();
	}

}
