/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.authority;

import java.util.Set;

import xdata.etl.web.server.dao.IRpcDao;
import xdata.etl.web.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
public interface AuthorityDao extends IRpcDao<Integer, Authority> {
	Authority queryByName(Integer agId, String name);

	Set<String> getOpenAuthorities();
}
