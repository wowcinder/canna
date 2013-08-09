/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.init;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xdata.etl.web.client.service.authority.AuthorityGroupService;
import xdata.etl.web.client.service.authority.AuthorityService;
import xdata.etl.web.server.util.ClassScaner;
import xdata.etl.web.server.util.ClassScaner.ClassFilter;
import xdata.etl.web.shared.annotations.AuthenticationMethod;
import xdata.etl.web.shared.annotations.AuthenticationService;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;
import xdata.etl.web.shared.entity.menu.Menu;

/**
 * @author XuehuiHe
 * @date 2013年8月6日
 */
@Component
public class RefreshAuthority implements InitializingBean {
	private ClassScaner scanner;
	@Autowired
	private AuthorityService aService;
	@Autowired
	private AuthorityGroupService agService;
	@Autowired
	private SessionFactory sf;

	private static ClassFilter<Class<?>> filter = new ClassFilter<Class<?>>() {

		@Override
		public List<Class<?>> filte(ClassScaner scaner) {
			List<Class<?>> list = new ArrayList<Class<?>>();
			for (Class<?> clazz : scaner.getClazzes()) {
				AuthenticationService s = clazz
						.getAnnotation(AuthenticationService.class);
				if (s != null) {
					list.add(clazz);
				}
			}
			return list;
		}
	};

	private static ClassFilter<Class<?>> menuFilter = new ClassFilter<Class<?>>() {
		@Override
		public List<Class<?>> filte(ClassScaner scaner) {
			List<Class<?>> list = new ArrayList<Class<?>>();
			for (Class<?> clazz : scaner.getClazzes()) {
				MenuToken s = clazz.getAnnotation(MenuToken.class);
				if (s != null) {
					list.add(clazz);
				}
			}
			return list;
		}
	};

	public RefreshAuthority() {
		try {
			scanner = new ClassScaner("xdata.etl.web.server.service",
					"xdata.etl.web.client.ui");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void initAuthorityConfig() {
		List<Class<?>> list = filter.filte(this.scanner);
		Set<String> agNames = new HashSet<String>();
		Map<String, HashSet<Authority>> map = new HashMap<String, HashSet<Authority>>();

		for (Class<?> clazz : list) {
			AuthenticationService s = clazz
					.getAnnotation(AuthenticationService.class);
			String agName = s.value();
			if (agNames.contains(agName)) {
				throw new Error(agName + "权限组重复");
			}
			agNames.add(agName);
			if (!map.containsKey(agName)) {
				map.put(agName, new HashSet<Authority>());
			}
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				AuthenticationMethod m = method
						.getAnnotation(AuthenticationMethod.class);
				if (m != null) {
					Authority a = new Authority();
					a.setName(m.value());
					a.setIsOpen(m.isOpen());
					map.get(agName).add(a);
				}
			}
		}
		int i = 1;
		for (String agName : agNames) {
			AuthorityGroup ag = new AuthorityGroup();
			ag.setName(agName);
			ag.setDisplayOrder(i);
			Integer agId = agService.queryByName(agName);
			if (agId == null) {
				agService.save(ag);
			} else {
				ag.setId(agId);
				agService.update(ag);
			}
			i++;

			HashSet<Authority> authorities = map.get(agName);
			int j = 1;
			for (Authority authority : authorities) {
				authority.setDisplayOrder(j);
				authority.setGroup(ag);
				Authority old = aService.queryByName(ag.getId(),
						authority.getName());
				if (old != null) {
					authority.setId(old.getId());
					authority.setToken(old.getToken());
					aService.update(authority);
				} else {
					aService.save(authority);
				}
				j++;
			}
		}
	}

	protected void initMenuConfig() {
		List<Class<?>> list = menuFilter.filte(this.scanner);
		sf.getCurrentSession().beginTransaction();
		for (Class<?> clazz : list) {
			MenuToken mt = clazz.getAnnotation(MenuToken.class);
			Menu menu = (Menu) sf.getCurrentSession()
					.createCriteria(Menu.class)
					.add(Restrictions.eq("token", mt.token())).uniqueResult();
			if (menu == null) {
				menu = new Menu();
				menu.setToken(mt.token());
				menu.setName(mt.name());
				sf.getCurrentSession().saveOrUpdate(menu);
				menu.setPos(menu.getId());
			}else{
				menu.setName(mt.name());
				sf.getCurrentSession().saveOrUpdate(menu);
			}
			
		}
		sf.getCurrentSession().getTransaction().commit();

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initAuthorityConfig();
		initMenuConfig();
	}
}
