package com.voole.gxt.server.dao.idc.impl;

import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.idc.IDCUPlaceDao;
import com.voole.gxt.shared.entity.idc.IDCUPlace;

@Repository
public class IDCUPlaceDaoImpl extends AbstractCannaDao<IDCUPlace> implements
		IDCUPlaceDao {

	public IDCUPlaceDaoImpl() {
		super(IDCUPlace.class);
	}

}
