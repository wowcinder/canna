/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu.combox;

import xdata.etl.web.client.common.editer.EditorWindow;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainerBuilder;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.service.authority.AuthorityService;
import xdata.etl.web.client.service.authority.AuthorityServiceAsync;
import xdata.etl.web.client.ui.authority.grid.AuthorityGrid;
import xdata.etl.web.shared.entity.authority.Authority;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class AuthoritySelector {

	private final EditorWindow root;
	private GwtCallBack<Authority> callback;

	public AuthoritySelector() {
		root = new EditorWindow();
		root.setModal(true);

		final EtlGridContainer<Integer, Authority> gridContainer = new EtlGridContainer<Integer, Authority>(
				new AuthorityGrid(false),
				GWT.<AuthorityServiceAsync> create(AuthorityService.class));

		EtlGridContainerBuilder<Integer, Authority> builder = new EtlGridContainerBuilder<Integer, Authority>(
				gridContainer);
		builder.setShowAddBt(false);
		builder.setShowRemoveBt(false);

		builder.setUpdateHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				callback._call(gridContainer.getGrid().getStore()
						.get(event.getRowIndex()));
				root.hide();
				root.removeFromParent();
			}
		});
		builder.build();
		gridContainer.setHeight(200);
		gridContainer.setWidth(400);
		root.setWidget(gridContainer);
		root.setHeadingHtml("请双击选择:");
		root.setHeaderVisible(true);
	}

	public void show() {
		root.forceLayout();
		root.show();
	}

	public void setCallback(GwtCallBack<Authority> callback) {
		this.callback = callback;
	}

}
