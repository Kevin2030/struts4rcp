package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Java默认的序列化方式实现
 * @see java.io.ObjectInputStream
 * @see java.io.ObjectOutputStream
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class JavaSerializer extends AbstractSerializer {

	public String getContentType() {
		return "application/java-serialization";
	}

	public Serializable deserialize(InputStream in) throws IOException {
		ObjectInputStream oo = new ObjectInputStream(in);
		try {
			return (Serializable)oo.readObject();
		} catch (ClassNotFoundException e) {// 此异常是运行期不可恢复的错误，不应强制调用者捕获
			throw new IOException("序列化类文件找不到：" + e.getMessage());
		}
	}

	public void serialize(Serializable obj, OutputStream out)
			throws IOException {
		ObjectOutputStream oo = new ObjectOutputStream(out);
		oo.writeObject(obj);
		oo.flush();
	}

}
