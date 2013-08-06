/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.authority;

import xdata.etl.web.server.dao.IRpcDao;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 *
 */
public interface AuthorityGroupDao extends IRpcDao<Integer, AuthorityGroup> {
	Integer queryByName(String name);
}
