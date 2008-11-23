package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XStream序列化方式实现
 * @see com.thoughtworks.xstream.XStream
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class XStreamSerializer extends AbstractTextSerializer {

	private XStream xstream = new XStream(new DomDriver());

	public String getContentType() {
		return "text/xstream";
	}

	public Serializable deserialize(Reader reader) throws IOException {
		return (Serializable) xstream.fromXML(reader);
	}

	public void serialize(Serializable obj, Writer writer) throws IOException {
		xstream.toXML(obj, writer);
	}

}
