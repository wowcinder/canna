package com.voole.gxt.client.menu.combox;

import com.google.gwt.core.shared.GWT;
import com.voole.gxt.client.canna.combox.CannaComBox;
import com.voole.gxt.client.canna.editor.CannaEditor;
import com.voole.gxt.client.menu.menugroup.MenuGroupEditor;
import com.voole.gxt.client.property.menu.MenuGroupProperties;
import com.voole.gxt.client.rpcclient.menu.MenuGroupRpcClient;
import com.voole.gxt.shared.entity.menu.MenuGroup;

public class MenuGroupComBox extends CannaComBox<MenuGroup> {

	public MenuGroupComBox() {
		this(true, true);
	}

	public MenuGroupComBox(boolean isAuto, boolean isAddabled) {
		super(isAuto, isAddabled, props.label());
	}

	private static final MenuGroupProperties props = GWT
			.create(MenuGroupProperties.class);
	private static final MenuGroupRpcClient rpc = new MenuGroupRpcClient();

	@Override
	public void autoGetStore() {
		rpc.get(this);
	}

	@Override
	public MenuGroup getAddItem() {
		MenuGroup mg = new MenuGroup();
		mg.setId(ADD_ID);
		mg.setName(ADD_TEXT);
		return mg;
	}

	@Override
	public CannaEditor<MenuGroup> getEditor() {
		return new MenuGroupEditor();
	}

	@Override
	public boolean isAddItem(MenuGroup t) {
		return t.getId() == ADD_ID;
	}

}
