/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.authority;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.server.util.AuthorityUtil;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
@Repository
public class AuthorityGroupDaoImpl extends RpcDao<Integer, AuthorityGroup>
		implements AuthorityGroupDao {
	public AuthorityGroupDaoImpl() {
	}

	@Override
	public void update(AuthorityGroup v) throws SharedException {
		Session session = getSession();
		AuthorityGroup old = (AuthorityGroup) session.load(
				AuthorityGroup.class, v.getId());
		if (!old.getName().equals(v.getName())) {
			for (Authority authority : old.getAuthorities()) {
				authority.setToken(AuthorityUtil.getToken(v.getName(),
						authority.getName()));
				session.update(authority);
			}
		}
		session.merge(v);
	}

	@Override
	public Integer queryByName(String name) {
		Session s = getSession();
		AuthorityGroup ag = (AuthorityGroup) s
				.createCriteria(AuthorityGroup.class)
				.add(Restrictions.eq("name", name)).uniqueResult();
		if (ag != null) {
			return ag.getId();
		}
		return null;
	}
}
