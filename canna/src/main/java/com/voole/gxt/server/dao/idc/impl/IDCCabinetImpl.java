package com.voole.gxt.server.dao.idc.impl;

import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.idc.IDCCabinetDao;
import com.voole.gxt.shared.entity.idc.IDCCabinet;

@Repository
public class IDCCabinetImpl extends AbstractCannaDao<IDCCabinet> implements
		IDCCabinetDao {
	public IDCCabinetImpl() {
		super(IDCCabinet.class);
	}

}
