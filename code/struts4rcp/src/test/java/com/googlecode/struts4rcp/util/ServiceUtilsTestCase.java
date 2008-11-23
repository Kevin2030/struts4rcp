package com.googlecode.struts4rcp.util;

import junit.framework.TestCase;

import com.googlecode.struts4rcp.client.Transporter;
import com.googlecode.struts4rcp.util.serializer.Serializer;

public class ServiceUtilsTestCase extends TestCase {

	public void testSerializerClassServices() throws Exception {
		Class<Serializer>[] serializers = ServiceUtils.getServices(Serializer.class);
		assertEquals(5, serializers.length);
	}

	public void testSerializerStringServices() throws Exception {
		String[] serializers = ServiceUtils.getServices(Serializer.class.getName());
		assertEquals(5, serializers.length);
	}

	public void testTransporterClassServices() throws Exception {
		Class<Transporter>[] transporters = ServiceUtils.getServices(Transporter.class);
		assertEquals(3, transporters.length);
	}

	public void testTransporterStringServices() throws Exception {
		String[] transporters = ServiceUtils.getServices(Transporter.class.getName());
		assertEquals(3, transporters.length);
	}

}
