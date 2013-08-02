package com.voole.gxt.client.property.menu;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.menu.MenuGroup;

public interface MenuGroupProperties extends CannaPropertyAccess<MenuGroup> {

	ValueProvider<MenuGroup, String> name();
}
