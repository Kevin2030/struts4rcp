package com.googlecode.struts4rcp.util.serializer;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * XML序列化方式实现
 * @see java.beans.XMLEncoder
 * @see java.beans.XMLDecoder
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class XmlStreamSerializer implements StreamSerializer {

	public String getContentType() {
		return "text/xml";
	}

	public Serializable deserialize(Class<? extends Serializable> baseClass, InputStream in) throws IOException {
		XMLDecoder xd = new XMLDecoder(in);
		return (Serializable)xd.readObject();
	}

	public void serialize(Serializable obj, OutputStream out)
			throws IOException {
		XMLEncoder xe = new XMLEncoder(out);
		xe.writeObject(obj);
		xe.flush();
	}

}
