/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.account;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xdata.etl.web.server.dao.authority.AuthorityDao;
import xdata.etl.web.server.dao.user.UserDao;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 */
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private UserDao userDao;
	@Autowired
	public AuthorityDao authorityDao;

	public AccountServiceImpl() {
	}

	@Override
	public Integer login(String username, String password) {
		if (isLogin()) {
			return null;
		}
		HttpSession session = getSession();
		if (username.equals("admin") && password.equals("admin")) {
			// TODO
			session.setAttribute("isLogin", Boolean.TRUE);
			session.setAttribute("isAdmin", Boolean.TRUE);
			session.setAttribute("userId", 0);
			return 0;
		}
		Integer uid = userDao.login(username, password);
		if (uid == null) {
			return null;
		}
		session.setAttribute("isLogin", Boolean.TRUE);
		session.setAttribute("userId", uid);
		Set<String> authorities = userDao.getUserAuthorities(uid);
		getSession().setAttribute("authorities", authorities);

		return uid;
	}

	protected HttpSession getSession() {
		if (RequestContextHolder.currentRequestAttributes() != null) {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			return attr.getRequest().getSession();
		}
		return null;
	}

	@Override
	public void logout() {
		HttpSession session = getSession();
		@SuppressWarnings({ "unchecked" })
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			session.removeAttribute(name);
		}
	}

	@Override
	public Boolean isLogin() {
		return Boolean.TRUE.equals(getSession().getAttribute("isLogin"));
	}

	@Override
	public Boolean isAdmin() {
		return Boolean.TRUE.equals(getSession().getAttribute("isAdmin"));
	}

	@Override
	public Integer getUserId() {
		return (Integer) getSession().getAttribute("userId");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getUserAuthorities() {
		return (Set<String>) getSession().getAttribute("authorities");
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean isAccessAbled(Collection<String> tokens) {
		Set<String> authorities = getUserAuthorities();
		for (String token : tokens) {
			if (authorities.contains(token)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean isOpen(Set<String> tokens) {
		Set<String> set = authorityDao.getOpenAuthorities();
		for (String token : tokens) {
			if (set.contains(token)) {
				return true;
			}
		}

		return false;
	}

}
