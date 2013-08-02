package com.voole.gxt.client.project.partner;

import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainer;
import com.voole.gxt.client.rpcclient.project.ProjectPartnerRpcClient;
import com.voole.gxt.shared.entity.project.ProjectPartner;

public class PartnerGridContainer extends CannaGridContainer<ProjectPartner> {

	public PartnerGridContainer() {
		super(new PartnerGrid(),new ProjectPartnerRpcClient());
		setEditor(new PartnerEditor());
	}

	@Override
	protected void noFoundDeleteItems() {
		AlertMessageBox d = new AlertMessageBox("删除", "请选择要删除的合作伙伴!");
		d.show();
	}

}
