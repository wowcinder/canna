/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.authority;

import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
@Repository
public class AuthorityGroupImpl extends RpcDao<Integer, AuthorityGroup>
		implements AuthorityGroupDao {
	
	@Override
	public Integer save(AuthorityGroup v) throws SharedException {
		return super.save(v);
	}

}
