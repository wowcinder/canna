package com.voole.gxt.server.dao.user.impl;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.user.UserGroupDao;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.user.UserGroup;

@Repository
public class UserGroupDaoImpl extends AbstractCannaDao<UserGroup> implements
		UserGroupDao {

	public UserGroupDaoImpl() {
		super(UserGroup.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AuthorityRpcGroup> getAuthorityRpcGroups(Long id) {
		String sql = "select new {0}(ag.id,ag.name) from {1} ug inner join ug.authGroups ag where ug.id = ? ";
		sql = MessageFormat.format(sql, AuthorityRpcGroup.class.getName(),
				getQueryName());
		return getSession().createQuery(sql).setParameter(0, id).list();
	}
}
