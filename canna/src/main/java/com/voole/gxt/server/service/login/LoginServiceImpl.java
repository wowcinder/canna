package com.voole.gxt.server.service.login;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voole.gxt.client.service.login.LoginService;
import com.voole.gxt.server.dao.user.UserDao;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserDao dao;

	@Override
	public boolean isLogin() {
		Boolean isLogin = (Boolean) ServletActionContext.getRequest()
				.getSession().getAttribute("isLogin");
		if (isLogin == null || isLogin.equals(false)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean login(String email, String password) {
		email = email.trim();
		password = password.trim();
		boolean isLogin;
		boolean isAdmin = false;
		if (email.equals("admin") && password.equals("admin")) {
			isLogin = true;
			isAdmin = true;
		} else {
			isLogin = dao.login(email, password);
		}
		if (isLogin) {
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			session.setAttribute("isLogin", isLogin);
			session.setAttribute("email", email);
			session.setAttribute("isAdmin", isAdmin);
		}
		return isLogin;
	}

	@Override
	public boolean isPermit(String email, String token) {
		return dao.isPermit(email, token);
	}

	@Override
	public boolean isPermit(String token) {
		String email = (String) ServletActionContext.getRequest().getSession()
				.getAttribute("email");
		if (email.equals("admin")) {
			return true;
		}
		return isPermit(email, token);
	}

}
