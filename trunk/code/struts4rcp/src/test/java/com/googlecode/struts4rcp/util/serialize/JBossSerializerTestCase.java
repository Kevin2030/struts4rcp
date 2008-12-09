package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.stream.JbossStreamSerializer;
import com.googlecode.struts4rcp.util.serializer.stream.StreamSerializer;

public class JBossSerializerTestCase extends SerializerTestCase {

	protected StreamSerializer createSerializer() {
		return new JbossStreamSerializer();
	}

}
