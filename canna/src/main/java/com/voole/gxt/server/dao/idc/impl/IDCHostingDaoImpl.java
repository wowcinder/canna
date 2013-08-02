package com.voole.gxt.server.dao.idc.impl;

import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.idc.IDCHostingDao;
import com.voole.gxt.shared.entity.idc.IDCHosting;

@Repository
public class IDCHostingDaoImpl extends AbstractCannaDao<IDCHosting> implements
		IDCHostingDao {
	public IDCHostingDaoImpl() {
		super(IDCHosting.class);
	}

}
