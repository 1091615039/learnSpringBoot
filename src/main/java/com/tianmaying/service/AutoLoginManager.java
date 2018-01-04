package com.tianmaying.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.tianmaying.filter.CurrentUserHolder;
import com.tianmaying.model.User;
@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION,proxyMode=ScopedProxyMode.INTERFACES)
public class AutoLoginManager implements CurrentUserHolder{
	@Autowired
    private HttpSession session;
	@Override
	public User get() {
		return currentUser();
	}

	public User currentUser() {	
		return (User) session.getAttribute("currentUser");
	}

}
