package com.voole.gxt.client.service.idc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.idc.IDCLeaseType;
@ServiceConfig(path="rpc/idc/leasetype.do")
public interface IDCLeaseTypeService extends CannaService<IDCLeaseType>,
		RemoteService {

}
