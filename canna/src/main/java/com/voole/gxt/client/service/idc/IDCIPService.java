package com.voole.gxt.client.service.idc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.idc.IDCIP;
@ServiceConfig(path="rpc/idc/ip.do")
public interface IDCIPService extends CannaService<IDCIP>, RemoteService {

}
