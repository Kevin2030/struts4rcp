package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XStream序列化方式实现
 * @see com.thoughtworks.xstream.XStream
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class XstreamStreamSerializer implements StreamSerializer {

	private XStream xstream = new XStream(new DomDriver());

	public String getContentType() {
		return "text/xstream";
	}

	public Serializable deserialize(Class<? extends Serializable> baseClass, InputStream in) throws IOException {
		return (Serializable) xstream.fromXML(in);
	}

	public void serialize(Serializable obj, OutputStream out)
			throws IOException {
		xstream.toXML(obj, out);
	}

}
