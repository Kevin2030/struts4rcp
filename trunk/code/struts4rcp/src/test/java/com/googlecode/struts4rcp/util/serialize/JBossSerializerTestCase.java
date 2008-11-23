package com.googlecode.struts4rcp.util.serialize;

import com.googlecode.struts4rcp.util.serializer.JBossSerializer;
import com.googlecode.struts4rcp.util.serializer.Serializer;

public class JBossSerializerTestCase extends SerializerTestCase {

	protected Serializer createSerializer() {
		return new JBossSerializer();
	}

}
