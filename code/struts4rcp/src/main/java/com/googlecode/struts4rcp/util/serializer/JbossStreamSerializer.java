package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.jboss.serial.io.JBossObjectInputStream;
import org.jboss.serial.io.JBossObjectOutputStream;

/**
 * Java默认的序列化方式实现
 * @see org.jboss.serial.io.JBossObjectInputStream
 * @see org.jboss.serial.io.JBossObjectOutputStream
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class JbossStreamSerializer implements StreamSerializer {

	public String getContentType() {
		return "application/jboss-serialization";
	}

	public Serializable deserialize(InputStream in) throws IOException {
		ObjectInputStream oo = new JBossObjectInputStream(in);
		try {
			return (Serializable)oo.readObject();
		} catch (ClassNotFoundException e) {// 此异常是运行期不可恢复的错误，不应强制调用者捕获
			throw new IOException("序列化类文件找不到：" + e.getMessage());
		}
	}

	public void serialize(Serializable obj, OutputStream out) throws IOException {
		ObjectOutputStream oo = new JBossObjectOutputStream(out);
		oo.writeObject(obj);
		oo.flush();
	}

}
