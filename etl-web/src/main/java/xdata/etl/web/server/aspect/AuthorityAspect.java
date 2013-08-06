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

import xdata.etl.web.shared.exception.SharedException;

@Component
@Aspect
public class AuthorityAspect implements Ordered {

	@Before("execution (* xdata.etl.web.client.service..*Service.*(..))")
	public void save(JoinPoint jp) throws Throwable {
		detail(jp);
	}

	public void detail(JoinPoint jp) {
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method m = signature.getMethod();
		System.out.println(m.getDeclaringClass().getName());
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
