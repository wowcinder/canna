/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.Hibernate;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年8月11日
 * 
 */
public class HibernateBeanUtil {

	private final static Map<Class<?>, List<Field>> columnFieldMap = new HashMap<Class<?>, List<Field>>();;

	private HibernateBeanUtil() {
	}

	public static void dealBean(Object t) {
		if (t == null) {
			return;
		}
		if (t instanceof Collection) {
			for (Object o : (Collection<?>) t) {
				dealBean(o);
			}
		} else if (t instanceof PagingLoadResult) {
			dealBean(((PagingLoadResult<?>) t).getData());
		} else {
			if (!t.getClass().isAnnotationPresent(Entity.class)) {
				return;
			}
			List<Field> fields = getColumnFields(t.getClass());

			try {
				for (Field field : fields) {
					boolean old = field.isAccessible();
					if (!old) {
						field.setAccessible(true);
					}
					Object o = field.get(t);
					if (o == null || o.getClass().isPrimitive()) {
					} else if (!Hibernate.isInitialized(o)) {
						field.set(t, null);
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	}

	protected static List<Field> getColumnFields(Class<?> clazz) {
		if (!columnFieldMap.containsKey(clazz)) {
			fillMap(clazz);
		}
		return columnFieldMap.get(clazz);
	}

	protected static synchronized void fillMap(Class<?> clazz) {
		if (columnFieldMap.containsKey(clazz)) {
			return;
		}
		if (!clazz.isAnnotationPresent(Entity.class)) {
			columnFieldMap.put(clazz, null);
			return;
		}
		columnFieldMap.put(clazz, getFields(clazz));
	}

	protected static List<Field> getFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		if (clazz.isAnnotationPresent(Entity.class)
				|| clazz.isAnnotationPresent(MappedSuperclass.class)) {
			for (Field field : clazz.getDeclaredFields()) {
				int mod = field.getModifiers();
				if (!Modifier.isStatic(mod) && !Modifier.isTransient(mod)) {
					fields.add(field);
				}
			}
		}
		Class<?> superClazz = clazz.getSuperclass();
		if (!superClazz.equals(Object.class)) {
			fields.addAll(getFields(clazz.getSuperclass()));
		}
		return fields;
	}

}
