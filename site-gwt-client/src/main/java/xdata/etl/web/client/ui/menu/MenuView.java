/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu;

import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.menu.tree.MenuTree;
import xdata.etl.web.shared.annotations.MenuToken;

import com.google.gwt.dom.client.Style.TextAlign;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
@MenuToken(name = "菜单管理", token = "menu_manager", group = "菜单管理")
public class MenuView extends HorizontalLayoutContainer implements CenterView {
	private CenterViewConfig centerViewConfig;

	public MenuView() {
		MenuTree tree = new MenuTree();
		tree.getElement().getStyle().setTextAlign(TextAlign.LEFT);
		tree.setWidth(300);
		add(tree, new HorizontalLayoutData(-1, 1, new Margins(10)));
	}

	@Override
	public CenterViewConfig getCenterViewConfig() {
		return centerViewConfig;
	}

	@Override
	public void setCenterViewConfig(CenterViewConfig centerViewConfig) {
		this.centerViewConfig = centerViewConfig;
	}

}
