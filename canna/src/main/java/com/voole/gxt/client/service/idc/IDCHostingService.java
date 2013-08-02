package com.voole.gxt.client.service.idc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.idc.IDCHosting;
@ServiceConfig(path="rpc/idc/hosting.do")
public interface IDCHostingService extends CannaService<IDCHosting>,
		RemoteService {

}
