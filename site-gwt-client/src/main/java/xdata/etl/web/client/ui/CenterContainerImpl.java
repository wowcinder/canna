/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui;

import xdata.etl.web.client.app.CenterViewFinder;
import xdata.etl.web.client.event.CenterVievChangeEvent;
import xdata.etl.web.client.ui.CenterView.CenterViewConfig;

import com.google.gwt.dom.client.Style.TextAlign;
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
public class CenterContainerImpl extends TabPanel implements CenterContainer {

	@Inject
	private EventBus eventBus;

	@Inject
	private CenterViewFinder viewFinder;

	public CenterContainerImpl() {
		setBodyBorder(true);
		setTabScroll(true);
		setResizeTabs(true);
		setMinTabWidth(130);
		setCloseContextMenu(true);
		getElement().getStyle().setTextAlign(TextAlign.CENTER);
	}

	protected void close(Widget item) {
		super.close(item);
		afterClosedSubPanel();
	}

	@Override
	public void showCenter(String token) {
		CenterView exists = findExistsTabItem(token);
		if (exists != null) {
			setActiveWidget(exists);
			return;
		}
		CenterView newView = create(token);
		if (newView == null) {
			Info.display("WARN", "没有找到页面!");
			return;
		}
		CenterViewConfig centerViewConfig = newView.getCenterViewConfig();
		TabItemConfig config = new TabItemConfig(centerViewConfig.getLabel(),
				centerViewConfig.isCloseAble());
		add(newView, config);
		setActiveWidget(newView);
	}

	protected CenterView create(String token) {
		return viewFinder.findCenterView(token);
	}

	@Override
	public void afterClosedSubPanel() {
		CenterView active = (CenterView) getActiveWidget();
		if (active != null) {
			eventBus.fireEvent(new CenterVievChangeEvent(
					CenterVievChangeEvent.From.RIGHT, active
							.getCenterViewConfig().getToken()));
		} else {
			eventBus.fireEvent(new CenterVievChangeEvent(
					CenterVievChangeEvent.From.RIGHT, null));
		}

	}

	protected CenterView findExistsTabItem(String token) {
		for (int i = 0; i < getWidgetCount(); i++) {
			CenterView item = (CenterView) getWidget(i);
			if (token.equals(item.getCenterViewConfig().getToken())) {
				return item;
			}
		}
		return null;
	}

}
