/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.user;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Repository
public class UserGroupDaoImpl extends RpcDao<Integer, UserGroup> implements
		UserGroupDao {
	@Override
	public void delete(Integer k) throws SharedException {
		UserGroup ug = (UserGroup) getSession().load(UserGroup.class, k);
		try {
			for (User u : ug.getUsers()) {
				u.setUserGroup(null);
			}
			getSession().delete(ug);
		} catch (ObjectNotFoundException e) {
		}
	}

	@Override
	public void delete(List<Integer> ids) throws SharedException {
		for (Integer k : ids) {
			this.delete(k);
		}
	}
}
