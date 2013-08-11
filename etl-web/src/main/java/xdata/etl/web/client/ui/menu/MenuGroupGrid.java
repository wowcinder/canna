/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu;

import xdata.etl.web.client.common.grid.EtlGrid;
import xdata.etl.web.client.property.MenuGroupProperty;
import xdata.etl.web.shared.entity.menu.MenuGroup;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年8月11日
 * 
 */
public class MenuGroupGrid extends EtlGrid<Integer, MenuGroup> {
	public MenuGroupGrid() {
		super((MenuGroupProperty) GWT.create(MenuGroupProperty.class));
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<MenuGroup, String> nameCC = new ColumnConfig<MenuGroup, String>(
				getProps().name(), 200, "name");
		getColumnConfigs().add(nameCC);

		new GridDragSource<MenuGroup>(this);
		GridDropTarget<MenuGroup> target1 = new GridDropTarget<MenuGroup>(this);
		target1.setFeedback(Feedback.INSERT);
		target1.setAllowSelfAsSource(true);
	}

	public MenuGroupProperty getProps() {
		return (MenuGroupProperty) props;
	}

}
