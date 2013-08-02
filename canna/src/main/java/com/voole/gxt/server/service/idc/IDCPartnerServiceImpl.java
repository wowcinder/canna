package com.voole.gxt.server.service.idc;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.service.idc.IDCPartnerService;
import com.voole.gxt.server.dao.idc.IDCPartnerDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.idc.IDCPartner;
@Service
public class IDCPartnerServiceImpl extends BaseService<IDCPartner> implements
		IDCPartnerService {

	@Override
	public IDCPartnerDao getDao() {
		return idcPartnerDao;
	}

}
