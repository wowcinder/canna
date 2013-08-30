/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.testweb.client;

import xdata.etl.testweb.shared.entity.menu.MenuNode;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * @author XuehuiHe
 * @date 2013年8月30日
 */
public interface MenuNodeProps extends PropertyAccess<MenuNode> {
	@Path("id")
	ModelKeyProvider<MenuNode> id();

	ValueProvider<MenuNode, String> name();
}
