package xdata.etl.testweb.server.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import xdata.etl.testweb.server.util.HibernateBeanUtil;

import com.google.gwt.user.client.rpc.RemoteService;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@Component
@Aspect
public class RpcAspect implements Ordered {

	@Around(value = "execution(* xdata.etl.testweb.server.rpc..*(..))")
	public Object dealResult(ProceedingJoinPoint pjp) throws Throwable {
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

	@Override
	public int getOrder() {
		return -100;
	}

}
