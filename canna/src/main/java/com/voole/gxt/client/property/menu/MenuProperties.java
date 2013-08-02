package com.voole.gxt.client.property.menu;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.menu.Menu;
import com.voole.gxt.shared.entity.menu.MenuGroup;

public interface MenuProperties extends CannaPropertyAccess<Menu> {

	ValueProvider<Menu, String> name();

	ValueProvider<Menu, String> token();

	ValueProvider<Menu, AuthorityRpcMethod> method();

	ValueProvider<Menu, MenuGroup> menuGroup();

	ValueProvider<Menu, Menu> self();

}
