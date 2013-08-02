package com.voole.gxt.client.service.project;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.project.ProjectPartner;
@ServiceConfig(path = "rpc/project/partner.do", name = "项目合作伙伴管理")
public interface ProjectPartnerService extends CannaService<ProjectPartner>,
		RemoteService {

}
