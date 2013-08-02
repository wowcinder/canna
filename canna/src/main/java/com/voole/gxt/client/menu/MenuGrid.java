package com.voole.gxt.client.menu;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.gxt.client.canna.grid.AbstractCannaSafeHtmlRenderer;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.canna.grid.CannaSafeHtmlCell;
import com.voole.gxt.client.property.menu.MenuProperties;
import com.voole.gxt.shared.entity.menu.Menu;
import com.voole.gxt.shared.entity.menu.MenuGroup;

public class MenuGrid extends CannaGrid<Menu> {
	private final static MenuProperties props = GWT
			.create(MenuProperties.class);

	public MenuGrid() {
		super(props);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<Menu, String> nameCC = new ColumnConfig<Menu, String>(
				props.name(), 200, "name");
		ColumnConfig<Menu, String> tokenCC = new ColumnConfig<Menu, String>(
				props.token(), 200, "token");
		ColumnConfig<Menu, MenuGroup> mgCC = new ColumnConfig<Menu, MenuGroup>(
				props.menuGroup(), 200, "菜单组");
		mgCC.setCell(new CannaSafeHtmlCell<MenuGroup>(
				new AbstractCannaSafeHtmlRenderer<MenuGroup>() {
					@Override
					public String getRenderStr(MenuGroup t) {
						return t.getName();
					}
				}));
		ccList.add(nameCC);
		ccList.add(tokenCC);
		ccList.add(mgCC);

		new GridDragSource<Menu>(this);
		GridDropTarget<Menu> target1 = new GridDropTarget<Menu>(this);
		target1.setFeedback(Feedback.INSERT);
		target1.setAllowSelfAsSource(true);

	}
}
