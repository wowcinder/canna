package com.voole.gxt.client.app.ui;

import java.util.List;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.user.client.ui.Composite;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.voole.gxt.client.app.Presenter;
import com.voole.gxt.client.app.place.CenterPlace;
import com.voole.gxt.client.property.menu.MenuProperties;
import com.voole.gxt.client.service.CannaAsyncCallback;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.menu.MenuGroupService;
import com.voole.gxt.client.service.menu.MenuGroupServiceAsync;
import com.voole.gxt.shared.entity.menu.Menu;
import com.voole.gxt.shared.entity.menu.MenuGroup;

public class MenuListViewImpl extends Composite implements MenuListView {
	private static final MenuGroupServiceAsync service = ClientServiceFactory
			.getService(MenuGroupService.class);

	final MenuProperties props = GWT.create(MenuProperties.class);
	private Presenter presenter;
	private Viewport c;

	public MenuListViewImpl() {
		c = new Viewport();
		initWidget(c);
	}

	public void refresh() {
		service.getMenuGroupsForMenuView(new CannaAsyncCallback<List<MenuGroup>>() {
			@Override
			public void onSuccess(List<MenuGroup> result) {
				AccordionLayoutContainer con = new AccordionLayoutContainer();
				AccordionLayoutAppearance appearance = GWT
						.<AccordionLayoutAppearance> create(AccordionLayoutAppearance.class);
				int size = result.size();
				for (int i = 0; i < size; i++) {
					MenuGroup mg = result.get(i);
					ContentPanel cp = new ContentPanel(appearance);
					cp.setHeadingText(mg.getName());
					cp.add(getList(mg));
					con.add(cp);
					if (i == 0) {
						con.setActiveWidget(cp);
					}
				}
				c.add(con);
				con.onResize();
			}
		});
	}

	public ListView<Menu, Menu> getList(MenuGroup mg) {
		ListView<Menu, Menu> menuList;
		menuList = new ListView<Menu, Menu>(new ListStore<Menu>(props.key()),
				props.self());
		SimpleSafeHtmlCell<Menu> cell2 = new SimpleSafeHtmlCell<Menu>(
				new SafeHtmlRenderer<Menu>() {
					@Override
					public SafeHtml render(Menu obj) {
						return SimpleSafeHtmlRenderer.getInstance().render(
								obj.getName());
					}

					@Override
					public void render(Menu obj, SafeHtmlBuilder builder) {
						SimpleSafeHtmlRenderer.getInstance().render(
								obj.getName(), builder);
					}

				}, "click") {

			@Override
			public void onBrowserEvent(
					com.google.gwt.cell.client.Cell.Context context,
					Element parent, Menu value, NativeEvent event,
					ValueUpdater<Menu> valueUpdater) {
				super.onBrowserEvent(context, parent, value, event,
						valueUpdater);
				if ("click".equals(event.getType())) {
					CenterPlace cp = new CenterPlace();
					cp.setToken(value.getToken());
					presenter.showCenter(cp);
				}
			}

		};

		menuList.setCell(cell2);
		menuList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		menuList.getStore().addAll(mg.getMenus());
		return menuList;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
