package com.voole.gxt.client.service.idc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.idc.IDC;
@ServiceConfig(path="rpc/idc.do")
public interface IDCService extends CannaService<IDC>, RemoteService {

}
