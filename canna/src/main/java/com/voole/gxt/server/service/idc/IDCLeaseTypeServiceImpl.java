package com.voole.gxt.server.service.idc;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.service.idc.IDCLeaseTypeService;
import com.voole.gxt.server.dao.idc.IDCLeaseTypeDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.idc.IDCLeaseType;
@Service
public class IDCLeaseTypeServiceImpl extends BaseService<IDCLeaseType>
		implements IDCLeaseTypeService {

	@Override
	public IDCLeaseTypeDao getDao() {
		return idcLeaseTypeDao;
	}

}
