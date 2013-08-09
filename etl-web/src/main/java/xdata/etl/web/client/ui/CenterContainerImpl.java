/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui;

import xdata.etl.web.client.event.CenterVievChangeEvent;

import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
public class CenterContainerImpl extends Composite implements CenterContainer {
	private TabPanel tabPanel;

	@Inject
	private EventBus eventBus;

	public CenterContainerImpl() {
		tabPanel = new TabPanel();
		tabPanel.setBodyBorder(true);
		tabPanel.setTabScroll(true);
		tabPanel.setResizeTabs(true);
		tabPanel.setMinTabWidth(130);
		tabPanel.setCloseContextMenu(true);
		tabPanel.getElement().getStyle().setTextAlign(TextAlign.CENTER);
		// showCenterView(new OverView());
	}

	@Override
	public Widget asWidget() {
		return tabPanel;
	}

	@Override
	public void showCenter(String token) {
		CenterView exists = findExistsTabItem(token);
		if (exists != null) {
			tabPanel.setActiveWidget(exists);
			return;
		}
		CenterView newView = create(token);
		if (newView == null) {
			Info.display("WARN", "没有找到页面!");
			return;
		}
		TabItemConfig config = new TabItemConfig(newView.getLabel(),
				newView.isCloseAble());
		tabPanel.add(newView, config);
		tabPanel.setActiveWidget(newView);
	}

	protected CenterView create(String token) {
		// TODO
		return null;
	}

	@Override
	public void afterClosedSubPanel() {
		CenterView active = (CenterView) tabPanel.getActiveWidget();
		eventBus.fireEvent(new CenterVievChangeEvent(
				CenterVievChangeEvent.From.RIGHT, active.getToken()));
	}

	protected CenterView findExistsTabItem(String token) {
		for (int i = 0; i < tabPanel.getWidgetCount(); i++) {
			CenterView item = (CenterView) tabPanel.getWidget(i);
			if (token.equals(item.getToken())) {
				return item;
			}
		}
		return null;
	}

}
