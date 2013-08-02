package com.voole.gxt.client.service;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.shared.GWT;

public class ClientServiceFactory {

	private static final ClientServiceFinder finder = GWT
			.create(ClientServiceFinder.class);

	private static Map<Class<?>, Object> maps = new HashMap<Class<?>, Object>();

	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<?> clazz) {
		if (!maps.containsKey(clazz)) {
			createService(clazz);
		}
		return (T) maps.get(clazz);
	}

	private static void createService(Class<?> clazz) {
		Object service = finder.createService(clazz);
		maps.put(clazz, service);
	}

}
