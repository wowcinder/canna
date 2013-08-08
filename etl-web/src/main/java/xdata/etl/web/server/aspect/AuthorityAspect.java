package xdata.etl.web.server.aspect;

import java.lang.reflect.Method;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import xdata.etl.web.shared.annotations.AuthenticationMethod;
import xdata.etl.web.shared.annotations.AuthenticationService;
import xdata.etl.web.shared.exception.IllegalMethodException;
import xdata.etl.web.shared.exception.SharedException;

@Component
@Aspect
public class AuthorityAspect implements Ordered {

	@Before("execution (* xdata.etl.web.client.service..*Service.*(..))")
	public void save(JoinPoint jp) throws Throwable {
		detail(jp);
	}

	public void detail(JoinPoint jp) {
		Class<?> clazz = jp.getTarget().getClass();
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method m = signature.getMethod();

		AuthenticationMethod am = m.getAnnotation(AuthenticationMethod.class);
		AuthenticationService as = clazz
				.getAnnotation(AuthenticationService.class);
		if (am == null || as == null) {
			throw new IllegalMethodException(m.getName() + " is illegal method");
		}
		if (am.isOpen()) {
			System.out.println("-----------open---------------");
		}
		System.out.println("权限组名: " + as.value() + "\t权限名: " + am.value());
		System.out.println("--------------detail------------");
	}

	@AfterThrowing(pointcut = "execution (* xdata.etl.web.client.service..*Service.*(..))", throwing = "ex")
	public void errorInterceptor(Exception ex) throws SharedException {
		if (ex instanceof SharedException) {
			throw (SharedException) ex;
		}
		System.out.println("--------------Exception------------");
		ex.printStackTrace();
		throw new SharedException(ExceptionUtils.getRootCauseMessage(ex));
	}

	@Override
	public int getOrder() {
		return -100;
	}

}
