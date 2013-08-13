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
import org.springframework.web.context.request.RequestContextHolder;

import xdata.etl.web.server.common.AccessService;
import xdata.etl.web.server.service.open.OpenService;
import xdata.etl.web.server.util.HibernateBeanUtil;
import xdata.etl.web.shared.exception.PermissionException;
import xdata.etl.web.shared.exception.SharedException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@Component
@Aspect
public class AuthorityAspect implements Ordered {

	@Autowired
	private AccessService accessService;

	@Around(value = "execution(* xdata.etl.web.server.service..*(..))")
	public Object dealResult(ProceedingJoinPoint pjp) throws Throwable {
		doAccessCheck(pjp);
		Object retVal = pjp.proceed();
		if (retVal != null
				&& RequestContextHolder.getRequestAttributes() != null
				&& pjp.getTarget() instanceof RemoteService) {
			if (retVal instanceof PagingLoadResult) {
				new HibernateBeanUtil().dealBean(((PagingLoadResult<?>) retVal)
						.getData());
			} else {
				new HibernateBeanUtil().dealBean(retVal);
			}
		}

		return retVal;

	}

	public void doAccessCheck(JoinPoint jp) {
		if (jp.getTarget() instanceof OpenService) {
			return;
		}
		if (!accessService.isAccess(jp)) {
			throw new PermissionException();
		}
	}

	@AfterThrowing(pointcut = "execution(* xdata.etl.web.server.service..*(..))", throwing = "ex")
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
