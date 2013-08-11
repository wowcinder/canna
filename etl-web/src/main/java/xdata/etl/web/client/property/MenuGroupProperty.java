/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property;

import java.util.List;

import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.menu.MenuGroup;

import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author XuehuiHe
 * @date 2013年8月11日
 * 
 */
public interface MenuGroupProperty extends
		RpcEntityPropertyAccess<Integer, MenuGroup> {

	ValueProvider<MenuGroup, String> name();

	ValueProvider<MenuGroup, List<Menu>> menus();

	ValueProvider<MenuGroup, Integer> pos();

}
