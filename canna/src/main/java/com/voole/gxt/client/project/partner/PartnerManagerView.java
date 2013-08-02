package com.voole.gxt.client.project.partner;

import com.voole.gxt.client.app.MenuToken;
import com.voole.gxt.client.app.ui.MenuCenterView;

@MenuToken(token = "projectPartnerManager", name = "项目合作伙伴管理")
public class PartnerManagerView extends MenuCenterView {
	PartnerGridContainer gridCon;

	public PartnerManagerView() {
		setLabel("合作伙伴管理");
		gridCon = new PartnerGridContainer();
		con.setWidget(gridCon);
	}

}
