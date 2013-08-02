package com.voole.gxt.server.dao.idc.impl;

import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.idc.IDCDao;
import com.voole.gxt.shared.entity.idc.IDC;

@Repository
public class IDCDaoImpl extends AbstractCannaDao<IDC> implements
		IDCDao {
	public IDCDaoImpl() {
		super(IDC.class);
	}

}
