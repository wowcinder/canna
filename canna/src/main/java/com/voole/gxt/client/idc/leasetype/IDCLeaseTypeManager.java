package com.voole.gxt.client.idc.leasetype;

import com.voole.gxt.client.app.MenuToken;
import com.voole.gxt.client.app.ui.MenuCenterView;

@MenuToken(token = "idcLeaseTypeManager", name = "IDC租借方式管理")
public class IDCLeaseTypeManager extends MenuCenterView {
	IDCLeaseTypeGridContainer gridCon;

	public IDCLeaseTypeManager() {
		setLabel("租赁方式管理");
		gridCon = new IDCLeaseTypeGridContainer();
		con.setWidget(gridCon);
	}
}
