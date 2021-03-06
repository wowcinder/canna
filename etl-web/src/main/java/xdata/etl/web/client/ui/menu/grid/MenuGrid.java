/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu.grid;

import xdata.etl.web.client.common.grid.RpcEntityGridBuilder;
import xdata.etl.web.client.property.menu.MenuProperty;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.menu.MenuGroup;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public class MenuGrid extends RpcEntityGridBuilder<Integer, Menu, MenuProperty> {

	public MenuGrid() {
		super(MenuProperty.INSTANCE);
	}

	@Override
	public Grid<Menu> create() {
		Grid<Menu> grid = super.create();
		new GridDragSource<Menu>(grid);
		GridDropTarget<Menu> target1 = new GridDropTarget<Menu>(grid);
		target1.setFeedback(Feedback.INSERT);
		target1.setAllowSelfAsSource(true);
		return grid;
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<Menu, String> nameCC = new ColumnConfig<Menu, String>(
				getProps().name(), 200, "name");
		ColumnConfig<Menu, String> tokenCC = new ColumnConfig<Menu, String>(
				getProps().token(), 200, "token");
		ColumnConfig<Menu, MenuGroup> mgCC = new ColumnConfig<Menu, MenuGroup>(
				getProps().menuGroup(), 200, "菜单组");
		mgCC.setCell(new SimpleSafeHtmlCell<MenuGroup>(
				new AbstractSafeHtmlRenderer<MenuGroup>() {

					@Override
					public SafeHtml render(MenuGroup mg) {
						if (mg == null) {
							return null;
						}
						String str = mg.getName();
						if (str == null) {
							return null;
						}
						return SafeHtmlUtils.fromString(str);
					}
				}));

		ColumnConfig<Menu, Authority> auCC = new ColumnConfig<Menu, Authority>(
				getProps().requireAuthority(), 200, "所需权限");
		auCC.setCell(new SimpleSafeHtmlCell<Authority>(
				new AbstractSafeHtmlRenderer<Authority>() {

					@Override
					public SafeHtml render(Authority auth) {
						if (auth == null) {
							return null;
						}
						return SafeHtmlUtils.fromString(auth.getGroup()
								.getName() + "_" + auth.getName());
					}
				}));

		getColumnConfigs().add(nameCC);
		getColumnConfigs().add(tokenCC);
		getColumnConfigs().add(mgCC);
		getColumnConfigs().add(auCC);
	}

}
