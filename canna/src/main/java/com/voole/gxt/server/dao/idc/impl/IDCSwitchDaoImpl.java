package com.voole.gxt.server.dao.idc.impl;

import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.idc.IDCSwitchDao;
import com.voole.gxt.shared.entity.idc.IDCSwitch;

@Repository
public class IDCSwitchDaoImpl extends AbstractCannaDao<IDCSwitch> implements
		IDCSwitchDao {
	public IDCSwitchDaoImpl() {
		super(IDCSwitch.class);
	}

}
