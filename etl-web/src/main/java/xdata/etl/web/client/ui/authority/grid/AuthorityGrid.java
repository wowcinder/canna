/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.authority.grid;

import xdata.etl.web.client.common.grid.EtlGrid;
import xdata.etl.web.client.property.AuthorityProperty;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class AuthorityGrid extends EtlGrid<Integer, Authority> {

	public AuthorityGrid() {
		super(GWT.<AuthorityProperty> create(AuthorityProperty.class));
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<Authority, String> nameCC = new ColumnConfig<Authority, String>(
				getProps().name(), 200, "name");
		ColumnConfig<Authority, AuthorityGroup> groupCC = new ColumnConfig<Authority, AuthorityGroup>(
				getProps().group(), 200, "group");
		groupCC.setCell(new SimpleSafeHtmlCell<AuthorityGroup>(
				new AbstractSafeHtmlRenderer<AuthorityGroup>() {

					@Override
					public SafeHtml render(AuthorityGroup ag) {
						if (ag == null) {
							return null;
						}
						String str = ag.getName();
						if (str == null) {
							return null;
						}
						return SafeHtmlUtils.fromString(str);
					}
				}));
		getColumnConfigs().add(nameCC);
		getColumnConfigs().add(groupCC);
	}

	public AuthorityProperty getProps() {
		return (AuthorityProperty) props;
	}

}
