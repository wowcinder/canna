package com.voole.gxt.client.app;

import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.state.client.BorderLayoutStateHandler;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.voole.gxt.client.app.ui.CenterContainerView;
import com.voole.gxt.client.app.ui.MenuListView;

public class CannaShell extends BorderLayoutContainer {

	public CannaShell() {
		monitorWindowResize = true;
		Window.enableScrolling(false);
		setPixelSize(Window.getClientWidth(), Window.getClientHeight());

		setStateful(true);
		setStateId("explorerLayout");

		BorderLayoutStateHandler state = new BorderLayoutStateHandler(this);
		state.loadState();
	}

	private ContentPanel west;
	private SimpleContainer center;

	@Inject
	public CannaShell(MenuListView listView, CenterContainerView centerContainer) {
		this();

		StringBuffer sb = new StringBuffer();
		sb.append("<div id='demo-theme'></div><div id=demo-title>运维管理系统</div>");

		HtmlLayoutContainer northPanel = new HtmlLayoutContainer(sb.toString());
		northPanel.setStateful(false);
		northPanel.setId("demo-header");
		northPanel.addStyleName("x-small-editor");

		BorderLayoutData northData = new BorderLayoutData(35);
		setNorthWidget(northPanel, northData);

		BorderLayoutData westData = new BorderLayoutData(200);
		westData.setMargins(new Margins(5, 0, 5, 5));
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setCollapseHidden(true);
		westData.setCollapseMini(true);

		west = new ContentPanel();
		west.setHeadingText("菜单");
		west.setBodyBorder(true);
		west.add(listView.asWidget());

		setWestWidget(west, westData);

		MarginData centerData = new MarginData();
		centerData.setMargins(new Margins(5));

		center = new SimpleContainer();
		center.add(centerContainer.asWidget());

		setCenterWidget(center, centerData);
	}

	@Override
	protected void onWindowResize(int width, int height) {
		setPixelSize(width, height);
	}

}
