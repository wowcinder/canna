package com.voole.gxt.client.menu.menugroup;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.property.menu.MenuGroupProperties;
import com.voole.gxt.shared.entity.menu.MenuGroup;

public class MenuGroupGrid extends CannaGrid<MenuGroup> {
	private static final MenuGroupProperties props = GWT
			.create(MenuGroupProperties.class);

	public MenuGroupGrid() {
		super(props);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<MenuGroup, String> cc1 = new ColumnConfig<MenuGroup, String>(
				props.name(), 200, "name");
		ccList.add(cc1);

		new GridDragSource<MenuGroup>(this);
		GridDropTarget<MenuGroup> target1 = new GridDropTarget<MenuGroup>(this);
		target1.setFeedback(Feedback.INSERT);
		target1.setAllowSelfAsSource(true);
	}

}
