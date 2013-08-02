package com.voole.gxt.server.service.idc;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.service.idc.IDCHostingService;
import com.voole.gxt.server.dao.idc.IDCHostingDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.idc.IDCHosting;
@Service
public class IDCHostingServiceImpl extends BaseService<IDCHosting> implements
		IDCHostingService {

	@Override
	public IDCHostingDao getDao() {
		return idcHostingDao;
	}

}
