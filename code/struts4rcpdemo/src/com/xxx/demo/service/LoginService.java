package com.xxx.demo.service;

import com.xxx.demo.domain.User;
import com.xxx.demo.exception.LoginException;

public interface LoginService {

	User login(String username, String password) throws LoginException;

}
