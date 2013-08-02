package com.voole.gxt.server.util;

import javax.persistence.Entity;
import javax.persistence.Table;

public class HibernateUtil {
	public static String getTableName(Class<?> clazz) {
		Table table = clazz.getAnnotation(Table.class);
		if (table != null && table.name() != null) {
			return table.name();
		} else {
			return clazz.getName();
		}
	}

	public static String getQueryName(Class<?> clazz) {
		Entity entity = clazz.getAnnotation(Entity.class);
		if (entity != null && entity.name() != null
				&& !"".equals(entity.name())) {
			return entity.name();
		} else {
			return clazz.getName();
		}
	}
}
