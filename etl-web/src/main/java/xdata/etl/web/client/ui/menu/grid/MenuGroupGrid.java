/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu.grid;

import xdata.etl.web.client.common.grid.RpcEntityGridBuilder;
import xdata.etl.web.client.property.menu.MenuGroupProperty;
import xdata.etl.web.shared.entity.menu.MenuGroup;

import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年8月11日
 * 
 */
public class MenuGroupGrid extends
		RpcEntityGridBuilder<Integer, MenuGroup, MenuGroupProperty> {
	public MenuGroupGrid() {
		super(MenuGroupProperty.INSTANCE);
	}

	@Override
	public Grid<MenuGroup> create() {
		Grid<MenuGroup> grid = super.create();
		new GridDragSource<MenuGroup>(grid);
		GridDropTarget<MenuGroup> target1 = new GridDropTarget<MenuGroup>(grid);
		target1.setFeedback(Feedback.INSERT);
		target1.setAllowSelfAsSource(true);
		return grid;
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<MenuGroup, String> nameCC = new ColumnConfig<MenuGroup, String>(
				getProps().name(), 200, "name");
		getColumnConfigs().add(nameCC);
	}
}
