package com.googlecode.struts4rcp.util;

import java.util.Collection;

import junit.framework.TestCase;

import com.googlecode.struts4rcp.client.Transferrer;
import com.googlecode.struts4rcp.util.serializer.Serializer;

public class ServiceUtilsTestCase extends TestCase {

	@SuppressWarnings("unchecked")
	public void testSerializerInstancesServices() throws Exception {
		Collection<Serializer> serializers = ServiceUtils.getServiceInstances(Serializer.class);
		assertEquals(5, serializers.size());
	}

	@SuppressWarnings("unchecked")
	public void testSerializerClassServices() throws Exception {
		Collection<Class<Serializer>> serializers = ServiceUtils.getServiceClasses(Serializer.class);
		assertEquals(5, serializers.size());
	}

	public void testSerializerStringServices() throws Exception {
		Collection<String> serializers = ServiceUtils.getServiceClassNames(Serializer.class.getName());
		assertEquals(5, serializers.size());
	}

	public void testTransporterInstancesServices() throws Exception {
		Collection<Transferrer> transporters = ServiceUtils.getServiceInstances(Transferrer.class);
		assertEquals(3, transporters.size());
	}

	public void testTransporterClassServices() throws Exception {
		Collection<Class<Transferrer>> transporters = ServiceUtils.getServiceClasses(Transferrer.class);
		assertEquals(3, transporters.size());
	}

	public void testTransporterStringServices() throws Exception {
		Collection<String> transporters = ServiceUtils.getServiceClassNames(Transferrer.class.getName());
		assertEquals(3, transporters.size());
	}

}
