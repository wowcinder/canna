package com.voole.gxt.server.dao.user.impl;

import java.text.MessageFormat;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoadConfigBean;
import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.user.UserDao;
import com.voole.gxt.server.util.PasswordUtil;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.user.User;

@Repository
public class UserDaoImpl extends AbstractCannaDao<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public void preSave(User t) {
		t.setPassword(PasswordUtil.getMd5Hex(t.getPassword()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<User> get(CannaPagingLoadConfigBean config) {
		PagingLoadResultBean<User> pr = new PagingLoadResultBean<User>();
		Criteria criteria = findPagingCriteria(config);
		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		pr.setOffset(config.getOffset());
		pr.setTotalLength((int) rowCount);

		String sql = "from {0} u inner join fetch u.group";
		sql = MessageFormat.format(sql, getQueryName());
		Query query = getSession().createQuery(sql);
		addPagingConfig(config, query);
		pr.setData(query.list());
		return pr;
	}

	@Override
	public String modifyPassword(Long id, String newPassword) {
		Session s = getSession();
		User u = (User) s.load(User.class, id);
		u.setPassword(PasswordUtil.getMd5Hex(newPassword));
		s.update(u);
		return u.getPassword();
	}

	@Override
	public boolean login(String email, String password) {
		Session s = getSession();
		String sql = "select count(u) from {0} u where u.email =:email and u.password =:password";
		sql = MessageFormat.format(sql, getQueryName());
		Long i = (Long) s.createQuery(sql).setParameter("email", email)
				.setParameter("password", PasswordUtil.getMd5Hex(password))
				.uniqueResult();
		return i > 0;
	}

	@Override
	public boolean isPermit(String email, String token) {
		Session s = getSession();
		AuthorityRpcMethod method = (AuthorityRpcMethod) s
				.createCriteria(AuthorityRpcMethod.class)
				.add(Restrictions.eq("token", token)).uniqueResult();
		if (method.isAlwaysPass()) {
			return true;
		}
		String sql = "select count(am) from {0} u inner join u.group ug inner join ug.authGroups aug inner join aug.authorityRpcMethods am where u.email=:email and am.token =:token";
		sql = MessageFormat.format(sql, getQueryName());
		Long i = (Long) s.createQuery(sql).setParameter("email", email)
				.setParameter("token", token).uniqueResult();
		return i > 0;
	}

}
