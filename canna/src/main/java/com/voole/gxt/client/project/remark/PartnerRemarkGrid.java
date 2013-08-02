package com.voole.gxt.client.project.remark;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.gxt.client.canna.grid.AbstractCannaSafeHtmlRenderer;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.canna.grid.CannaSafeHtmlCell;
import com.voole.gxt.client.property.project.PartnerRemarkProperties;
import com.voole.gxt.shared.entity.project.ProjectPartner;
import com.voole.gxt.shared.entity.project.ProjectRemark;

public class PartnerRemarkGrid extends CannaGrid<ProjectRemark> {
	private static final PartnerRemarkProperties props = GWT
			.create(PartnerRemarkProperties.class);

	public PartnerRemarkGrid() {
		super(props);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<ProjectRemark, ProjectPartner> nameCC = new ColumnConfig<ProjectRemark, ProjectPartner>(
				props.partner(), 200, "姓名");
		ColumnConfig<ProjectRemark, ProjectPartner> positionCC = new ColumnConfig<ProjectRemark, ProjectPartner>(
				props.partner(), 200, "职务");
		ColumnConfig<ProjectRemark, ProjectPartner> mobileCC = new ColumnConfig<ProjectRemark, ProjectPartner>(
				props.partner(), 200, "手机");
		ColumnConfig<ProjectRemark, ProjectPartner> emailCC = new ColumnConfig<ProjectRemark, ProjectPartner>(
				props.partner(), 200, "email");
		ColumnConfig<ProjectRemark, ProjectPartner> telphoneCC = new ColumnConfig<ProjectRemark, ProjectPartner>(
				props.partner(), 200, "座机");
		ColumnConfig<ProjectRemark, ProjectPartner> remark1CC = new ColumnConfig<ProjectRemark, ProjectPartner>(
				props.partner(), 200, "备注1");
		ColumnConfig<ProjectRemark, String> remark2CC = new ColumnConfig<ProjectRemark, String>(
				props.remark(), 200, "备注2");
		remark1CC.setToolTip(SafeHtmlUtils.fromString("合作伙伴备注"));
		remark2CC.setToolTip(SafeHtmlUtils.fromString("合作伙伴-项目备注"));
		nameCC.setCell(new CannaSafeHtmlCell<ProjectPartner>(
				new AbstractCannaSafeHtmlRenderer<ProjectPartner>() {
					@Override
					public String getRenderStr(ProjectPartner t) {
						return t.getName();
					}
				}));
		positionCC.setCell(new CannaSafeHtmlCell<ProjectPartner>(
				new AbstractCannaSafeHtmlRenderer<ProjectPartner>() {
					@Override
					public String getRenderStr(ProjectPartner t) {
						return t.getPosition();
					}
				}));
		mobileCC.setCell(new CannaSafeHtmlCell<ProjectPartner>(
				new AbstractCannaSafeHtmlRenderer<ProjectPartner>() {
					@Override
					public String getRenderStr(ProjectPartner t) {
						return t.getMobile();
					}
				}));
		emailCC.setCell(new CannaSafeHtmlCell<ProjectPartner>(
				new AbstractCannaSafeHtmlRenderer<ProjectPartner>() {
					@Override
					public String getRenderStr(ProjectPartner t) {
						return t.getEmail();
					}
				}));
		telphoneCC.setCell(new CannaSafeHtmlCell<ProjectPartner>(
				new AbstractCannaSafeHtmlRenderer<ProjectPartner>() {
					@Override
					public String getRenderStr(ProjectPartner t) {
						return t.getTelphone();
					}
				}));

		remark1CC.setCell(new CannaSafeHtmlCell<ProjectPartner>(
				new AbstractCannaSafeHtmlRenderer<ProjectPartner>() {
					@Override
					public String getRenderStr(ProjectPartner t) {
						return t.getRemark();
					}

					@Override
					public SafeHtml render(ProjectPartner t) {
						String str = getRenderStr(t);
						if (str == null) {
							return null;
						}
						SafeHtmlBuilder sb = new SafeHtmlBuilder();
						sb.appendHtmlConstant("<span qtitle='备注1' qtip='" + str
								+ "'>" + str + "</span>");
						return sb.toSafeHtml();
					}
				}));
		remark2CC.setCell(new SimpleSafeHtmlCell<String>(
				new AbstractSafeHtmlRenderer<String>() {
					@Override
					public SafeHtml render(String str) {
						if (str == null) {
							return null;
						}
						SafeHtmlBuilder sb = new SafeHtmlBuilder();
						sb.appendHtmlConstant("<span qtitle='备注2' qtip='" + str
								+ "'>" + str + "</span>");
						return sb.toSafeHtml();
					}
				}));

		ccList.add(nameCC);
		ccList.add(positionCC);
		ccList.add(mobileCC);
		ccList.add(emailCC);
		ccList.add(telphoneCC);
		ccList.add(remark1CC);
		ccList.add(remark2CC);

	}
}
