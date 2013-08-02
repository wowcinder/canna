package com.voole.gxt.client.service.idc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.idc.IDCPartner;
@ServiceConfig(path="rpc/idc/partner.do")
public interface IDCPartnerService extends CannaService<IDCPartner>,
		RemoteService {

}
