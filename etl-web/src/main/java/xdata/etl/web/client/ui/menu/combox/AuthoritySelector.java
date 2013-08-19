/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu.combox;

import xdata.etl.web.client.common.editer.EditorWindow;
import xdata.etl.web.client.common.gridcontainer.GridContainer;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.service.ServiceUtil;
import xdata.etl.web.client.ui.authority.grid.AuthorityGrid;
import xdata.etl.web.shared.entity.authority.Authority;

import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class AuthoritySelector extends EditorWindow {

	private GwtCallBack<Authority> callback;

	public AuthoritySelector() {
		setModal(true);

		final GridContainer<Authority> gridContainer = new GridContainer<Authority>(
				new AuthorityGrid(false).create());
		gridContainer.setPagingLoader(ServiceUtil.AuthorityRpcCaller
				.getPagingLoader(gridContainer.getGrid().getStore()), 50);

		gridContainer.getGrid().addCellDoubleClickHandler(
				new CellDoubleClickHandler() {

					@Override
					public void onCellClick(CellDoubleClickEvent event) {
						callback.call(gridContainer.getGrid().getStore()
								.get(event.getRowIndex()));
						hide();
					}
				});
		// gridContainer.setHeight(200);
		// gridContainer.setWidth(400);
		setWidget(gridContainer);
		setHeadingHtml("请双击选择:");
		setHeaderVisible(true);
	}

	public void setCallback(GwtCallBack<Authority> callback) {
		this.callback = callback;
	}

}
