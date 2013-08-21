/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property.menu;

import xdata.etl.web.client.property.RpcEntityPropertyAccess;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.menu.MenuGroup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public interface MenuProperty extends RpcEntityPropertyAccess<Integer, Menu> {
	public static final MenuProperty INSTANCE = GWT.create(MenuProperty.class);

	@Path("name")
	LabelProvider<Menu> label();

	ValueProvider<Menu, String> name();

	ValueProvider<Menu, String> token();

	ValueProvider<Menu, MenuGroup> menuGroup();

	ValueProvider<Menu, Authority> requireAuthority();

	ValueProvider<Menu, Integer> pos();
}
