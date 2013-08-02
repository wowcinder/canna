package com.voole.gxt.client.service;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.ServiceDefTarget;

public abstract class ClientServiceFinder {

	protected Map<Class<?>, String> pathMaps = new HashMap<Class<?>, String>();

	public abstract Object gwtCreate(Class<?> clazz);

	public Object createService(Class<?> clazz) {
		String path = pathMaps.get(clazz);
		Object service = gwtCreate(clazz);
		((ServiceDefTarget) service).setServiceEntryPoint(path);
		return service;
	}

}
