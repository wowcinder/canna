/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.init;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xdata.etl.web.server.annotations.AccessAuthorities;
import xdata.etl.web.server.annotations.AccessAuthority;
import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.dao.menu.MenuNodeDao;
import xdata.etl.web.server.service.authority.AuthorityGroupService;
import xdata.etl.web.server.service.authority.AuthorityService;
import xdata.etl.web.server.util.ClassScaner;
import xdata.etl.web.server.util.ClassScaner.ClassFilter;
import xdata.etl.web.shared.annotations.MenuToken;
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
	@Autowired
	private MenuNodeDao menuDao;

	public static class ScanedAccessAuthority {
		private String group;
		private String value;
		private Boolean isOpen = false;

		public ScanedAccessAuthority() {
		}

		public String getGroup() {
			return group;
		}

		public void setGroup(String group) {
			this.group = group;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Boolean isOpen() {
			return isOpen;
		}

		public void setOpen(Boolean isOpen) {
			this.isOpen = isOpen;
		}

		@Override
		public int hashCode() {
			return getGroup().hashCode() + 3 * getValue().hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof ScanedAccessAuthority)) {
				return false;
			}
			ScanedAccessAuthority that = (ScanedAccessAuthority) obj;
			return that.getGroup().equals(this.getGroup())
					&& that.getValue().equals(this.getValue());
		}

	}

	private static ClassFilter<ScanedAccessAuthority> filter = new ClassFilter<ScanedAccessAuthority>() {

		@Override
		public List<ScanedAccessAuthority> filte(ClassScaner scaner) {
			List<ScanedAccessAuthority> list = new ArrayList<RefreshAuthority.ScanedAccessAuthority>();
			HashSet<ScanedAccessAuthority> set = new HashSet<RefreshAuthority.ScanedAccessAuthority>();
			for (Class<?> clazz : scaner.getClazzes()) {
				AccessAuthorityGroup g = clazz
						.getAnnotation(AccessAuthorityGroup.class);
				String gName = "";
				if (g != null) {
					gName = g.value();
				}
				for (Method method : clazz.getMethods()) {
					AccessAuthorities authorities = method
							.getAnnotation(AccessAuthorities.class);
					AccessAuthority authority = method
							.getAnnotation(AccessAuthority.class);
					if (authorities == null && authority == null) {
						continue;
					}
					if (authority != null) {
						ScanedAccessAuthority a = new ScanedAccessAuthority();
						a.setOpen(authority.isOpen());
						a.setValue(authority.value());
						a.setGroup(authority.group().equals("") ? gName
								: authority.group());
						set.add(a);
					} else {
						for (AccessAuthority authorityItem : authorities
								.value()) {
							ScanedAccessAuthority a = new ScanedAccessAuthority();
							a.setOpen(authorityItem.isOpen());
							a.setValue(authorityItem.value());
							a.setGroup(authorityItem.group().equals("") ? gName
									: authorityItem.group());
							set.add(a);
						}
					}
				}
			}
			list.addAll(set);
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
			scanner = new ClassScaner("xdata.etl.web.server.rpc",
					"xdata.etl.web.client.ui");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void initAuthorityConfig() {
		List<ScanedAccessAuthority> list = filter.filte(this.scanner);
		HashMap<String, List<ScanedAccessAuthority>> map = new HashMap<String, List<ScanedAccessAuthority>>();

		for (ScanedAccessAuthority scanedAccessAuthority : list) {
			String group = scanedAccessAuthority.getGroup();
			if (!map.containsKey(group)) {
				map.put(group,
						new ArrayList<RefreshAuthority.ScanedAccessAuthority>());
			}
			map.get(group).add(scanedAccessAuthority);
		}
		for (String group : map.keySet()) {
			if (group == null || group.equals("")) {
				continue;
			}
			List<ScanedAccessAuthority> items = map.get(group);
			Integer gid = agService.queryByName(group);
			AuthorityGroup ag = new AuthorityGroup();
			ag.setName(group);
			if (gid != null) {
				ag.setId(gid);
			} else {
				ag.setId(agService.save(ag));
			}

			for (ScanedAccessAuthority scanedAccessAuthority : items) {
				Authority old = aService.queryByName(ag.getId(),
						scanedAccessAuthority.getValue());
				if (old == null) {
					Authority authority = new Authority();
					authority.setGroup(ag);
					authority.setIsOpen(scanedAccessAuthority.isOpen());
					authority.setName(scanedAccessAuthority.value);
					aService.save(authority);
				}
			}
		}
	}

	protected void initMenuConfig() {

		List<Class<?>> list = menuFilter.filte(this.scanner);
		List<MenuToken> tokens = new ArrayList<MenuToken>();
		for (Class<?> clazz : list) {
			MenuToken mt = clazz.getAnnotation(MenuToken.class);
			tokens.add(mt);

		}
		menuDao.insertMenuConfig(tokens);

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initAuthorityConfig();
		initMenuConfig();
	}
}
