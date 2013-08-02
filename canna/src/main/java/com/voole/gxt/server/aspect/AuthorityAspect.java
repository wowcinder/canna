package com.voole.gxt.server.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.voole.gxt.client.canna.exception.JDBCException;
import com.voole.gxt.client.canna.exception.NoLoginException;
import com.voole.gxt.client.canna.exception.PermissionException;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.client.service.login.LoginService;

@Component
@Aspect
public class AuthorityAspect {
	@Autowired
	LoginService service;

	@Before("execution (* com.voole.gxt.client.service..*Service.save*(..))")
	public void save(JoinPoint jp) throws Throwable {
		detail(jp);
	}

	@Before("execution (* com.voole.gxt.client.service..*Service.del*(..))")
	public void delete(JoinPoint jp) throws Throwable {
		detail(jp);
	}

	@Before("execution (* com.voole.gxt.client.service..*Service.update*(..))")
	public void update(JoinPoint jp) throws Throwable {
		detail(jp);
	}

	@Before("execution (* com.voole.gxt.client.service..*Service.mod*(..))")
	public void modify(JoinPoint jp) throws Throwable {
		detail(jp);
	}

	public void detail(JoinPoint jp) throws PermissionException {
		Class<?> clazz = jp.getTarget().getClass().getInterfaces()[0];
		if (clazz.equals(LoginService.class)) {
			return;
		}
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method m = signature.getMethod();
		String token = generateToken(clazz, m);
		System.out.println("token:" + token);
		String method = jp.getTarget().getClass().getInterfaces()[0].getName()
				+ "." + jp.getSignature().getName() + "()";
		if (!service.isLogin()) {
			throw new NoLoginException("登录超时!");
		}
		boolean isPermit = service.isPermit(token);
		if (isPermit) {
			System.out.println(method + "被调用   通过!");
			return;
		} else {
			System.out.println(method + "被调用    阻止!");
			throw new PermissionException("权限不足!");
		}

	}

	@AfterThrowing(pointcut = "execution (* com.voole.gxt.client.service..*Service.*(..))", throwing = "ex")
	public void errorInterceptor(Exception ex) throws JDBCException {
		throw new JDBCException(ExceptionUtils.getRootCauseMessage(ex));
	}

	private String getMethodParsStr(Method m) {
		Class<?>[] pTypes = m.getParameterTypes();
		List<String> parStr = new ArrayList<String>();
		for (int i = 0; i < pTypes.length; i++) {
			parStr.add((pTypes[i]).getSimpleName());
		}
		return StringUtils.join(parStr, ",");
	}

	public String generateToken(Class<?> clazz, Method m) {
		StringBuffer sb = new StringBuffer();
		String path = clazz.getAnnotation(ServiceConfig.class).path();
		sb.append(path).append(m.getName()).append(getMethodParsStr(m));
		return DigestUtils.md5Hex(sb.toString());
	}

}
