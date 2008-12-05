package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.JbossStreamSerializer;
import com.googlecode.struts4rcp.util.serializer.StreamSerializer;

public class JBossSerializerTestCase extends SerializerTestCase {

	protected StreamSerializer createSerializer() {
		return new JbossStreamSerializer();
	}

}
