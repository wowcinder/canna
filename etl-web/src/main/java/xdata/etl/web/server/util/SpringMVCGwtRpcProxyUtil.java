package xdata.etl.web.server.util;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gwt.user.client.rpc.RemoteService;

public class SpringMVCGwtRpcProxyUtil implements
		SpringMVCGwtRpcProxyUtilInterface {
	@Autowired
	private ServletContext context;

	private final ConcurrentHashMap<Class<? extends RemoteService>, SpringMVCGwtRpcProxy> serviceMap;

	public SpringMVCGwtRpcProxyUtil() {
		serviceMap = new ConcurrentHashMap<Class<? extends RemoteService>, SpringMVCGwtRpcProxy>();
	}

	@Override
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
