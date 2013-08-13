package xdata.etl.web.client.app;

import xdata.etl.web.client.event.CenterVievChangeEvent;
import xdata.etl.web.client.ui.CenterContainer;
import xdata.etl.web.client.ui.MenuView;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.state.client.BorderLayoutStateHandler;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

public class EtlShell extends BorderLayoutContainer {
	@Inject
	private EventBus eventBus;
	@Inject
	private CenterContainer centerContainer;
	@Inject
	private MenuView menuView;

	private ContentPanel west;
	private SimpleContainer center;

	public EtlShell() {
		monitorWindowResize = true;
		Window.enableScrolling(false);
		setPixelSize(Window.getClientWidth(), Window.getClientHeight());

		setStateful(true);
		setStateId("explorerLayout");

		BorderLayoutStateHandler state = new BorderLayoutStateHandler(this);
		state.loadState();

	}

	public void init() {

		eventBus.addHandler(CenterVievChangeEvent.TYPE,
				new CenterVievChangeEvent.Hanlder() {
					@Override
					public void dispatch(CenterVievChangeEvent event) {
						if (event.getFrom().equals(
								CenterVievChangeEvent.From.LEFT)) {
							centerContainer.showCenter(event.getToken());
						} else {
							menuView.showMenu(event.getToken());
						}
					}
				});
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='demo-theme'></div><div id=demo-title>ETL管理系统</div>");

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
		west.add(menuView.asWidget());

		setWestWidget(west, westData);

		MarginData centerData = new MarginData();
		centerData.setMargins(new Margins(5));

		center = new SimpleContainer();
		center.add(centerContainer.asWidget());

		setCenterWidget(center, centerData);
		RootPanel.get().add(this);
		menuView.init();
	}

	@Override
	protected void onWindowResize(int width, int height) {
		setPixelSize(width, height);
	}

}
