package xdata.etl.web.server.service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.server.EtlSpringTestCase;
import xdata.etl.web.server.dao.menu.MenuGroupDao;
import xdata.etl.web.server.util.HibernateBeanUtil;
import xdata.etl.web.shared.entity.menu.MenuGroup;

public class MenuGroupDaoTest extends EtlSpringTestCase {
	@Autowired
	private MenuGroupDao dao;

	@Test
	public void test() {
		MenuGroup mg = new MenuGroup();
		mg.setName("sdjflsjldj");
		mg.setPos(1);

		dao.save(mg);

		List<MenuGroup> list = dao.get();
		mg = list.get(0);

		HibernateBeanUtil.dealBean(list);

		for (Field field : get(mg.getClass())) {
			System.out.println(field.getName());
		}

		System.out.println(Hibernate.isInitialized(mg.getName()));
		System.out.println(Hibernate.isInitialized(mg.getMenus()));
		System.out.println(list.size());
	}

	private List<Field> get(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		for (Field field : clazz.getDeclaredFields()) {
			int mod = field.getModifiers();
			if (!Modifier.isStatic(mod) && !Modifier.isTransient(mod)) {
				fields.add(field);
			}
		}
		if (!clazz.getSuperclass().equals(Object.class)) {
			fields.addAll(get(clazz.getSuperclass()));
		}
		return fields;
	}
}