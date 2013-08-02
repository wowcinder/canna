package xdata.etl.web.server.util;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gwt.user.client.rpc.RemoteService;

@Service
public class SpringMVCGwtRpcProxyUtil {
	@Autowired
	private ServletContext context;

	private final ConcurrentHashMap<Class<? extends RemoteService>, SpringMVCGwtRpcProxy> serviceMap;

	public SpringMVCGwtRpcProxyUtil() {
		serviceMap = new ConcurrentHashMap<Class<? extends RemoteService>, SpringMVCGwtRpcProxy>();
	}

	public SpringMVCGwtRpcProxy getService(Class<? extends RemoteService> clazz) {
		if (!serviceMap.contains(clazz)) {
			createServiceProxy(clazz);
		}
		return serviceMap.get(clazz);
	}

	private synchronized void createServiceProxy(
			Class<? extends RemoteService> clazz) {
		if (serviceMap.contains(clazz)) {
			return;
		}
		serviceMap.put(clazz,
				new SpringMVCGwtRpcProxy(BeanFinder.getBean(clazz), context));
	}
}
