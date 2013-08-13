/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.authority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.server.util.AuthorityUtil;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@Repository
public class AuthorityDaoImpl extends RpcDao<Integer, Authority> implements
		AuthorityDao {

	public AuthorityDaoImpl() {
	}

	@Override
	protected void criteriaLimit(Criteria criteria) {
		super.criteriaLimit(criteria);
		criteria.createAlias("group", "g").addOrder(Order.asc("g.name"))
				.addOrder(Order.asc("name"));

	}

	@Override
	public void delete(Integer k) throws SharedException {
		Session s = getSession();
		Authority a = (Authority) s.load(Authority.class, k);
		try {
			for (User u : a.getUsers()) {
				u.getExtraAuthorities().remove(a);
			}
			for (UserGroup userGroup : a.getUserGroups()) {
				userGroup.getAuthorities().remove(a);
			}
			for (Menu menu : a.getMenus()) {
				menu.setRequireAuthority(null);
			}
			s.delete(a);
		} catch (ObjectNotFoundException e) {
		}

	}

	@Override
	public void delete(List<Integer> ids) throws SharedException {
		for (Integer k : ids) {
			this.delete(k);
		}
	}

	@Override
	public Authority saveAndReturn(Authority v) throws SharedException {
		refreshToken(v);
		return super.saveAndReturn(v);
	}

	@Override
	public Authority update(Authority v) throws SharedException {
		Session s = getSession();
		Authority old = (Authority) s.load(Authority.class, v.getId());
		if (!old.getName().equals(v.getName())) {
			refreshToken(v);
		}
		s.merge(v);
		return v;
	}

	private void refreshToken(Authority v) {
		Session sesion = getSession();
		if (v.getGroup() == null || v.getGroup().getId() == null) {
			throw new SharedException("AuthorityGroup can't be null");
		}
		if (v.getGroup().getName() == null) {
			v.setGroup((AuthorityGroup) sesion.load(AuthorityGroup.class, v
					.getGroup().getId()));
		}
		v.setToken(AuthorityUtil.getToken(v.getGroup().getName(), v.getName()));
	}

	@Override
	public Authority queryByName(Integer agId, String name) {
		Session s = getSession();
		Authority a = (Authority) s.createCriteria(Authority.class)
				.add(Restrictions.eq("name", name)).createAlias("group", "ag")
				.add(Restrictions.eq("ag.id", agId)).uniqueResult();
		if (a != null) {
			return a;
		}
		return null;
	}

	@Override
	public Set<String> getOpenAuthorities() {
		@SuppressWarnings("unchecked")
		List<Authority> list = getSession().createCriteria(Authority.class)
				.add(Restrictions.eq("isOpen", Boolean.TRUE))
				.setResultTransformer(CriteriaSpecification.ROOT_ENTITY).list();
		Set<String> set = new HashSet<String>();
		for (Authority authority : list) {
			set.add(authority.getToken());
		}
		return set;
	}
}
