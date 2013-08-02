package com.voole.gxt.server.dao.menu.impl;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoadConfigBean;
import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.menu.MenuDao;
import com.voole.gxt.shared.entity.menu.Menu;

@Repository
public class MenuDaoImpl extends AbstractCannaDao<Menu> implements MenuDao {
	public MenuDaoImpl() {
		super(Menu.class);
	}

	@Override
	public void saveOrder(List<Long> ids) {
		Session s = getSession();
		for (int i = 0, ln = ids.size(); i < ln; i++) {
			Long id = ids.get(i);
			Menu m = (Menu) s.load(Menu.class, id);
			m.setOrder(i);
			s.update(m);
		}
	}

	public Criteria findGetCriteria() {
		return getSession().createCriteria(clazz)
				.setFetchMode("menuGroup", FetchMode.SELECT)
				.setFetchMode("method", FetchMode.SELECT)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	}

	// @Override
	// @SuppressWarnings("unchecked")
	// public List<Menu> get() {
	// Session s = getSession();
	// String sql =
	// "select new {0}(m.id, m.name, m.token, m.order, mg.id, mg.name) from {0} m left join m.menuGroup mg order by mg.order,m.order";
	// sql = MessageFormat.format(sql, getQueryName());
	// return s.createQuery(sql).list();
	// }

	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<Menu> get(CannaPagingLoadConfigBean config) {
		Session s = getSession();
		String countSql = "select count(m.id) from {0} m inner join m.menuGroup mg ";
		countSql = MessageFormat.format(countSql, getQueryName());
		long rowCount = (Long) s.createQuery(countSql).uniqueResult();

		PagingLoadResultBean<Menu> pr = new PagingLoadResultBean<Menu>();
		pr.setOffset(config.getOffset());
		pr.setTotalLength((int) rowCount);

		String sql = "select new {0}(m.id, m.name, m.token, m.order, mg.id, mg.name) from {0} m inner join m.menuGroup mg order by mg.order,m.order";
		sql = MessageFormat.format(sql, getQueryName());
		Query query = s.createQuery(sql);

		addPagingConfig(config, query);
		pr.setData(query.list());
		return pr;
	}

	@Override
	public void preSave(Menu t) {
		t.setOrder(getMenuMaxOrder() + 1);
	}

	@Override
	public void preUpdate(Menu t) {
		if (t.getMenuGroup() != null) {
			Integer max = getMenuMaxOrder();
			t.setOrder(max + 1);
		}
	}

	private Integer getMenuMaxOrder() {
		Session s = getSession();
		String sql = "select max(menu.order) from {0} menu";
		sql = MessageFormat.format(sql, getQueryName());
		Integer max = (Integer) s.createQuery(sql).uniqueResult();
		if (max == null) {
			max = -1;
		}
		return max;
	}
}
