package com.voole.gxt.server.dao.idc.impl;

import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.idc.IDCLeaseTypeDao;
import com.voole.gxt.shared.entity.idc.IDCLeaseType;

@Repository
public class IDCLeaseTypeDaoImpl extends AbstractCannaDao<IDCLeaseType> implements
		IDCLeaseTypeDao {
	public IDCLeaseTypeDaoImpl() {
		super(IDCLeaseType.class);
	}

}
