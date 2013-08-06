/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.authority;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.server.util.AuthorityUtil;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;
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
	public Authority saveAndReturn(Authority v) throws SharedException {
		Session sesion = getSession();
		if (v.getGroup() == null || v.getGroup().getId() == null) {
			throw new SharedException("AuthorityGroup can't be null");
		}
		if (v.getGroup().getName() == null) {
			v.setGroup((AuthorityGroup) sesion.load(AuthorityGroup.class, v
					.getGroup().getId()));
		}
		v.setToken(AuthorityUtil.getToken(v.getGroup().getName(), v.getName()));
		return super.saveAndReturn(v);
	}
}
