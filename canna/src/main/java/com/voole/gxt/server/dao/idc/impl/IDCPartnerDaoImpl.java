package com.voole.gxt.server.dao.idc.impl;

import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.idc.IDCPartnerDao;
import com.voole.gxt.shared.entity.idc.IDCPartner;

@Repository
public class IDCPartnerDaoImpl extends AbstractCannaDao<IDCPartner> implements
		IDCPartnerDao {
	public IDCPartnerDaoImpl() {
		super(IDCPartner.class);
	}

}
