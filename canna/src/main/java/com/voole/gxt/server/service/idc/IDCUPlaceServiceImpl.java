package com.voole.gxt.server.service.idc;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.service.idc.IDCUPlaceService;
import com.voole.gxt.server.dao.idc.IDCUPlaceDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.idc.IDCUPlace;

@Service
public class IDCUPlaceServiceImpl extends BaseService<IDCUPlace> implements
		IDCUPlaceService {

	@Override
	public IDCUPlaceDao getDao() {
		return idcUPlaceDao;
	}

}
