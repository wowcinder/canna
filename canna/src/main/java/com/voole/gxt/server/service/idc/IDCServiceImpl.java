package com.voole.gxt.server.service.idc;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.service.idc.IDCService;
import com.voole.gxt.server.dao.idc.IDCDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.idc.IDC;
@Service
public class IDCServiceImpl extends BaseService<IDC> implements IDCService {

	@Override
	public IDCDao getDao() {
		return idcDao;
	}

}
