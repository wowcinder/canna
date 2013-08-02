package com.voole.gxt.server.dao.authority.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.authority.AuthorityGroupRpcDao;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;

@Repository
public class AuthorityGroupRpcDaoImpl extends
		AbstractCannaDao<AuthorityRpcGroup> implements AuthorityGroupRpcDao {

	public AuthorityGroupRpcDaoImpl() {
		super(AuthorityRpcGroup.class);
	}

	@Override
	public List<AuthorityRpcMethod> get(AuthorityRpcGroup g) {
		return get(g.getId());
	}

	@Override
	public List<AuthorityRpcMethod> get(Long gid) {
		Session s = getSession();
		String sql = "select g from {0} g inner join fetch g.authorityRpcMethods gm where g.id = ? and gm.isAlwaysPass = ?";
		sql = MessageFormat.format(sql, getQueryName());

		AuthorityRpcGroup g = (AuthorityRpcGroup) s.createQuery(sql)
				.setParameter(0, gid).setParameter(1, false).uniqueResult();

		List<AuthorityRpcMethod> list = new ArrayList<AuthorityRpcMethod>();
		list.addAll(g.getAuthorityRpcMethods());
		return list;
	}

	@Override
	public void update(AuthorityRpcGroup g) {
		Session s = getSession();
		AuthorityRpcGroup old = (AuthorityRpcGroup) s.load(
				AuthorityRpcGroup.class, g.getId());
		g.setUserGroups(old.getUserGroups());
		s.merge(g);
	}

}
