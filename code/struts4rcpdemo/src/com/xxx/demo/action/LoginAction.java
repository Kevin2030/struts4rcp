package com.xxx.demo.action;

import com.googlecode.struts4rcp.Action;
import com.xxx.demo.domain.Account;
import com.xxx.demo.domain.User;
import com.xxx.demo.exception.LoginException;
import com.xxx.demo.service.LoginService;

/**
 * 登录控制器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class LoginAction implements Action<Account, User> {

	private LoginService loginService;

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public User execute(Account account) throws Exception {
		if (account.getDelay() > 0)
			Thread.sleep(account.getDelay());
		if (account.isException())
			throw new LoginException("模拟登录失败！");
		return loginService.login(account.getUsername(), account.getPassword());
	}

}
