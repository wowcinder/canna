/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user.grid;

import xdata.etl.web.client.common.grid.EtlGrid;
import xdata.etl.web.client.property.UserProperty;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;

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
public class UserGrid extends EtlGrid<Integer, User> {

	public UserGrid() {
		super(GWT.<UserProperty> create(UserProperty.class));
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<User, String> emailCC = new ColumnConfig<User, String>(
				getProps().email(), 200, "email");
		ColumnConfig<User, UserGroup> userGroupCC = new ColumnConfig<User, UserGroup>(
				getProps().userGroup(), 200, "用户组");
		userGroupCC.setCell(new SimpleSafeHtmlCell<UserGroup>(
				new AbstractSafeHtmlRenderer<UserGroup>() {
					@Override
					public SafeHtml render(UserGroup ug) {
						if (ug != null && ug.getName() != null) {
							return SafeHtmlUtils.fromString(ug.getName());
						}
						return null;
					}
				}));
		
		getColumnConfigs().add(emailCC);
		getColumnConfigs().add(userGroupCC);

	}

	@Override
	public UserProperty getProps() {
		return (UserProperty) super.getProps();
	}

}
