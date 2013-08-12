package xdata.etl.web.server.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xdata.etl.web.server.util.HibernateBeanUtil;
import xdata.etl.web.shared.annotations.AuthenticationMethod;
import xdata.etl.web.shared.annotations.AuthenticationService;
import xdata.etl.web.shared.exception.IllegalMethodException;
import xdata.etl.web.shared.exception.SharedException;

@Component
@Aspect
public class AuthorityAspect implements Ordered {

	@Around(value = "execution (* xdata.etl.web.client.service..*Service.*(..))")
	public Object dealResult(ProceedingJoinPoint pjp) throws Throwable {
		doAccessCheck(pjp);
		Object retVal = pjp.proceed();
		new HibernateBeanUtil().dealBean(retVal);
		return retVal;

	}

	public void doAccessCheck(JoinPoint jp) {
		if(RequestContextHolder.getRequestAttributes() == null){
			//NO Session
			return ;
		}
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		System.out.println(session);
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
