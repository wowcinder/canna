package com.voole.gxt.server.dao.idc.impl;

import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.idc.IDCIPDao;
import com.voole.gxt.shared.entity.idc.IDCIP;

@Repository
public class IDCIPDaoImpl extends AbstractCannaDao<IDCIP> implements
		IDCIPDao {
	public IDCIPDaoImpl() {
		super(IDCIP.class);
	}

}
