package xdata.etl.web.server.aspect;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import xdata.etl.web.server.common.AccessService;
import xdata.etl.web.server.util.HibernateBeanUtil;
import xdata.etl.web.shared.exception.SharedException;

@Component
@Aspect
public class AuthorityAspect implements Ordered {

	@Autowired
	private AccessService accessService;

	@Around(value = "execution (* xdata.etl.web.client.service..*Service.*(..))")
	public Object dealResult(ProceedingJoinPoint pjp) throws Throwable {
		doAccessCheck(pjp);
		Object retVal = pjp.proceed();
		new HibernateBeanUtil().dealBean(retVal);
		return retVal;

	}

	public static class Tuple {
		String group;
		String name;

		@Override
		public int hashCode() {
			return group.hashCode() + 3 * name.hashCode();
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
			return this.group.equals(that.group) && this.name.equals(that.name);
		}
	}

	public void doAccessCheck(JoinPoint jp) {
		if (accessService.isAccess(jp)) {
			// TODO
		} else {
			// TODO
		}
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
