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

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xdata.etl.web.client.service.authority.AuthorityGroupService;
import xdata.etl.web.client.service.authority.AuthorityService;
import xdata.etl.web.server.util.ClassScaner;
import xdata.etl.web.server.util.ClassScaner.ClassFilter;
import xdata.etl.web.shared.annotations.AuthenticationMethod;
import xdata.etl.web.shared.annotations.AuthenticationService;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

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

	public RefreshAuthority() {
		try {
			scanner = new ClassScaner("xdata.etl.web.server.service");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
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
					a.setOpen(m.isOpen());
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
				Integer aId = aService.queryByName(ag.getId(),
						authority.getName());
				if (aId != null) {
					authority.setId(aId);
					aService.update(authority);
				} else {
					aService.save(authority);
				}
				j++;
			}
		}
	}
}
