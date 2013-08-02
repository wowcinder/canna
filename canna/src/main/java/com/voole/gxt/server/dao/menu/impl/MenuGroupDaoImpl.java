package com.voole.gxt.server.dao.menu.impl;

import java.text.MessageFormat;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoadConfigBean;
import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.menu.MenuGroupDao;
import com.voole.gxt.shared.entity.menu.MenuGroup;

@Repository
public class MenuGroupDaoImpl extends AbstractCannaDao<MenuGroup> implements
		MenuGroupDao {
	public MenuGroupDaoImpl() {
		super(MenuGroup.class);
	}

	@Override
	public void preSave(MenuGroup t) {
		t.setOrder(getMenuMaxOrder() + 1);
	}

	private Integer getMenuMaxOrder() {
		Session s = getSession();
		String sql = "select max(mg.order) from {0} mg";
		sql = MessageFormat.format(sql, getQueryName());
		Integer max = (Integer) s.createQuery(sql).uniqueResult();
		if (max == null) {
			max = -1;
		}
		return max;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuGroup> get() {
		Session s = getSession();
		String sql = "select new {0}(mg.id,mg.name) from {0} mg order by mg.order ";
		sql = MessageFormat.format(sql, getQueryName());
		return s.createQuery(sql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<MenuGroup> get(CannaPagingLoadConfigBean config) {
		Session s = getSession();
		String countSql = "select count(mg.id) from {0} mg";
		countSql = MessageFormat.format(countSql, getQueryName());
		long rowCount = (Long) s.createQuery(countSql).uniqueResult();

		PagingLoadResultBean<MenuGroup> pr = new PagingLoadResultBean<MenuGroup>();
		pr.setOffset(config.getOffset());
		pr.setTotalLength((int) rowCount);

		String sql = "select new {0}(mg.id,mg.name) from {0} mg order by mg.order ";
		sql = MessageFormat.format(sql, getQueryName());
		Query query = s.createQuery(sql);

		addPagingConfig(config, query);
		pr.setData(query.list());
		return pr;
	}

	@Override
	public void saveOrder(List<Long> ids) {
		Session s = getSession();
		for (int i = 0, ln = ids.size(); i < ln; i++) {
			Long id = ids.get(i);
			MenuGroup mg = (MenuGroup) s.load(MenuGroup.class, id);
			mg.setOrder(i);
			s.update(mg);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuGroup> getMenuGroupsForMenuView() {
		Boolean isAdmin = (Boolean) ServletActionContext.getRequest()
				.getSession().getAttribute("isAdmin");
		Session s = getSession();
		if (isAdmin) {
			String sql = "select distinct mg from {0} mg inner join fetch mg.menus m order by mg.order,m.order";
			sql = MessageFormat.format(sql, getQueryName());
			return s.createQuery(sql).list();
		} else {
			String email = (String) ServletActionContext.getRequest()
					.getSession().getAttribute("email");
			List<Long> mids = s
					.createQuery(
							"select ugam.id from User u inner join u.group ug inner join ug.authGroups uga inner join uga.authorityRpcMethods ugam where u.email=:email ")
					.setParameter("email", email).list();
			String sql = "select distinct mg from {0} mg inner join fetch mg.menus m inner join m.method md ";
			sql += " where md.id in (:ids) ";
			sql += " order by mg.order,m.order ";
			sql = MessageFormat.format(sql, getQueryName());
			return s.createQuery(sql).setParameterList("ids", mids).list();
		}

	}
}
