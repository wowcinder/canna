package com.voole.gxt.server.service.idc;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.service.idc.IDCIPService;
import com.voole.gxt.server.dao.idc.IDCIPDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.idc.IDCIP;
@Service
public class IDCIPServiceImpl extends BaseService<IDCIP> implements
		IDCIPService {

	@Override
	public IDCIPDao getDao() {
		return idcIPDao;
	}

}
