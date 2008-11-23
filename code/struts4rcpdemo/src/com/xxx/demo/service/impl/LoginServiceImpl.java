package com.xxx.demo.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.xxx.demo.domain.User;
import com.xxx.demo.exception.LoginException;
import com.xxx.demo.service.LoginService;

public class LoginServiceImpl implements LoginService {

	public User login(String username, String password) throws LoginException {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUsername(username);
		user.setPassword(encrypt(username + password));
		user.setEmail(username + "@163.com");
		return user;
	}

	private String encrypt(String password) {
		try {
			return encode(MessageDigest.getInstance("MD5").digest(password.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			return password;
		}
	}

	private static String encode(byte[] bytes) {
		StringBuffer buffer = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10)
				buffer.append("0");
			buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buffer.toString();
	}
}
