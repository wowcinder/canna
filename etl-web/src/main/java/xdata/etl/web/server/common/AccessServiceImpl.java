/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import xdata.etl.web.server.annotations.AccessAuthorities;
import xdata.etl.web.server.annotations.AccessAuthority;
import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.service.open.AccountService;
import xdata.etl.web.server.util.AuthorityUtil;
import xdata.etl.web.shared.exception.IllegalMethodException;
import xdata.etl.web.shared.exception.NoLoginException;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
@Service
public class AccessServiceImpl implements AccessService {
	@Autowired
	private AccountService accountService;

	Map<Tuple, HashSet<String>> methodToTokens;
	HashSet<Tuple> openMethods;
	HashSet<Tuple> illegalMethods;

	public AccessServiceImpl() {
		methodToTokens = new HashMap<Tuple, HashSet<String>>();
		openMethods = new HashSet<Tuple>();
		illegalMethods = new HashSet<Tuple>();
	}

	@Override
	public boolean isAccess(JoinPoint jp) {
		if (RequestContextHolder.getRequestAttributes() == null) {
			// NO Session
			return true;
		}
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method m = signature.getMethod();
		Class<?> clazz = jp.getTarget().getClass();
		Tuple t = new Tuple();
		t.method = m;
		t.clazz = clazz;
		if (openMethods.contains(t)) {
			return true;
		}
		if (illegalMethods.contains(t)) {
			throw new IllegalMethodException(m.getName() + " is illegal method");
		}
		if (!methodToTokens.containsKey(t)) {
			fillMap(t);
		}
		HashSet<String> tokens = methodToTokens.get(t);
		if (tokens == null) {
			throw new IllegalMethodException(m.getName() + " is illegal method");
		}
		if (accountService.isOpen(tokens)) {
			return true;
		}
		if (!accountService.isLogin()) {
			throw new NoLoginException();
		}
		if (accountService.isAdmin()) {
			return true;
		}
		if (accountService.isAccessAbled(tokens)) {
			for (String token : tokens) {
				System.out.println("token:\t" + token);
			}
			return true;
		}
		return false;
	}

	protected synchronized void fillMap(Tuple t) {
		if (methodToTokens.containsKey(t)) {
			return;
		}
		AccessAuthority as = t.method.getAnnotation(AccessAuthority.class);
		AccessAuthorities aas = t.method.getAnnotation(AccessAuthorities.class);
		if (as == null && aas == null) {
			methodToTokens.put(t, null);
			illegalMethods.add(t);
			return;
		}
		HashSet<String> tokens = new HashSet<String>();
		AccessAuthorityGroup aag = t.clazz
				.getAnnotation(AccessAuthorityGroup.class);
		String group = "";
		if (aag != null) {
			group = aag.value();
		}
		if (as != null) {
			tokens.add(AuthorityUtil.getToken(as.group().equals("") ? group
					: as.group(), as.value()));
		} else {
			for (AccessAuthority asItem : aas.value()) {
				tokens.add(AuthorityUtil.getToken(
						asItem.group().equals("") ? group : asItem.group(),
						asItem.value()));
			}
		}
		methodToTokens.put(t, tokens);

	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public static class Tuple {
		Method method;
		Class<?> clazz;

		@Override
		public int hashCode() {
			return method.hashCode() + 3 * clazz.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof Tuple)) {
				return false;
			}
			Tuple that = (Tuple) obj;
			return this.method.equals(that.method)
					&& this.clazz.equals(that.clazz);
		}
	}

}
