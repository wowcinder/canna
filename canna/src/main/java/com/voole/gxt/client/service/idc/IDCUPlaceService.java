package com.voole.gxt.client.service.idc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.idc.IDCUPlace;

@ServiceConfig(path = "rpc/idc/uplace.do")
public interface IDCUPlaceService extends CannaService<IDCUPlace>,
		RemoteService {

}
