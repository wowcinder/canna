package com.voole.gxt.server.service.idc;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.service.idc.IDCCabinetService;
import com.voole.gxt.server.dao.idc.IDCCabinetDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.idc.IDCCabinet;
@Service
public class IDCCabinetServiceImpl extends BaseService<IDCCabinet> implements
		IDCCabinetService {

	@Override
	public IDCCabinetDao getDao() {
		return idcCabinetDao;
	}

}
