package com.xxx.demo.domain;

import java.io.Serializable;

/**
 * 帐号信息
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private long delay;

	private boolean exception;

	public Account() {}

	public Account(String username, String password, long delay, boolean exception) {
		this.username = username;
		this.password = password;
		this.delay = delay;
		this.exception = exception;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public boolean isException() {
		return exception;
	}

	public void setException(boolean exception) {
		this.exception = exception;
	}

}
