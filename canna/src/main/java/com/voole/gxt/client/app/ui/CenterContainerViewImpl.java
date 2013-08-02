package com.voole.gxt.client.app.ui;

import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.voole.gxt.client.app.MenuViewFinder;

public class CenterContainerViewImpl extends Composite implements
		CenterContainerView {
	
	@Inject
	private MenuViewFinder menuViewFinder;
	
	private TabPanel tabPanel;

	public CenterContainerViewImpl() {
		tabPanel = new TabPanel();
		tabPanel.setBodyBorder(true);
		tabPanel.setTabScroll(true);
		tabPanel.setResizeTabs(true);
		tabPanel.setMinTabWidth(130);
		tabPanel.setCloseContextMenu(true);
		tabPanel.getElement().getStyle().setTextAlign(TextAlign.CENTER);
		showCenterView(new OverView());
	}

	@Override
	public Widget asWidget() {
		return tabPanel;
	}
	
	
	public void showCenterView(CenterView view){
		TabItemConfig config = new TabItemConfig(view.getLabel(),
				view.isCloseAble());
		tabPanel.add(view, config);
	}

	@Override
	public void showCenterView(String token) {
		CenterView oldView = findTabItem(token);
		if (oldView == null) {
			oldView = menuViewFinder.findMenuView(token);
			if(oldView == null){
				Window.alert("没找到页面!");
				return;
			}
			showCenterView(oldView);
		}
		tabPanel.setActiveWidget(oldView);
	}

	public CenterView findTabItem(String token) {
		for (int i = 0; i < tabPanel.getWidgetCount(); i++) {
			CenterView item = (CenterView) tabPanel.getWidget(i);
			if (token.equals(item.getToken())) {
				return item;
			}
		}
		return null;

	}

}
