package com.voole.gxt.server.service.idc;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.service.idc.IDCSwitchService;
import com.voole.gxt.server.dao.idc.IDCSwitchDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.idc.IDCSwitch;

@Service
public class IDCSwitchServiceImpl extends BaseService<IDCSwitch> implements
		IDCSwitchService {

	@Override
	public IDCSwitchDao getDao() {
		return idcSwitchDao;
	}

}
