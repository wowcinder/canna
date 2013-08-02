package com.voole.gxt.client.project.partner;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.property.project.PartnerProperties;
import com.voole.gxt.shared.entity.project.ProjectPartner;

public class PartnerGrid extends CannaGrid<ProjectPartner> {
	private static final PartnerProperties props = GWT
			.create(PartnerProperties.class);

	public PartnerGrid() {
		super(props);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<ProjectPartner, String> nameCC = new ColumnConfig<ProjectPartner, String>(
				props.name(), 200, "姓名");
		ColumnConfig<ProjectPartner, String> positionCC = new ColumnConfig<ProjectPartner, String>(
				props.position(), 200, "职务");
		ColumnConfig<ProjectPartner, String> mobileCC = new ColumnConfig<ProjectPartner, String>(
				props.mobile(), 200, "手机");
		ColumnConfig<ProjectPartner, String> emailCC = new ColumnConfig<ProjectPartner, String>(
				props.email(), 200, "email");
		ColumnConfig<ProjectPartner, String> telphoneCC = new ColumnConfig<ProjectPartner, String>(
				props.telphone(), 200, "座机");
		ColumnConfig<ProjectPartner, String> remarkCC = new ColumnConfig<ProjectPartner, String>(
				props.remark(), 200, "备注");
		ccList.add(nameCC);
		ccList.add(positionCC);
		ccList.add(mobileCC);
		ccList.add(telphoneCC);
		ccList.add(emailCC);
		ccList.add(remarkCC);
	}

}
