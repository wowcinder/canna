package xdata.etl.web.server.controller.rpc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.server.util.BeanFinder;
import xdata.etl.web.server.util.ClassScaner;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

public class SpringMVCGwtRpcProxyUtil implements
		SpringMVCGwtRpcProxyUtilInterface {
	@Autowired
	private ServletContext context;

	private final ConcurrentHashMap<Class<? extends RemoteService>, SpringMVCGwtRpcProxy> serviceMap;
	private final Map<String, Class<?>> pathToClass;

	public SpringMVCGwtRpcProxyUtil() {
		serviceMap = new ConcurrentHashMap<Class<? extends RemoteService>, SpringMVCGwtRpcProxy>();
		pathToClass = new HashMap<String, Class<?>>();
		initPathToClass();
	}

	protected void initPathToClass() {
		try {
			ClassScaner scaner = new ClassScaner("xdata.etl.web.shared.service");
			for (Class<?> clazz : scaner.getClazzes()) {
				if (clazz.isAnnotationPresent(RemoteServiceRelativePath.class)) {
					RemoteServiceRelativePath path = clazz
							.getAnnotation(RemoteServiceRelativePath.class);
					pathToClass.put(getPath(path.value()), clazz);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getPath(String rawPath) {
		return rawPath.substring(rawPath.lastIndexOf('/') + 1);
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

	@SuppressWarnings("unchecked")
	@Override
	public SpringMVCGwtRpcProxy getService(String rpcPath) {
		if (!pathToClass.containsKey(rpcPath)) {
			return null;
		}
		return getService((Class<? extends RemoteService>) pathToClass
				.get(rpcPath));

	}
}
