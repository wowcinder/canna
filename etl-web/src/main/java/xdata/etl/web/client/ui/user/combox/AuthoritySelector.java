/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user.combox;

import java.util.List;

import xdata.etl.web.client.common.editer.EditorWindow;
import xdata.etl.web.client.common.gridcontainer.GridContainer;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.gwt.GwtCallBack.DelayCallback;
import xdata.etl.web.client.service.ServiceUtil;
import xdata.etl.web.client.ui.authority.grid.AuthorityGrid;
import xdata.etl.web.shared.entity.authority.Authority;

import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 * 
 */
public class AuthoritySelector extends EditorWindow {
	private GwtCallBack<List<Authority>> callback;

	public AuthoritySelector() {
		setModal(true);

		final GridContainer<Authority> gridContainer = new GridContainer<Authority>(
				new AuthorityGrid().create());
		ToolBar footBar = new ToolBar();
		TextButton tb = new TextButton("添加");
		footBar.add(tb);
		gridContainer.addToAfterGrid(footBar);

		tb.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				List<Authority> authorities = gridContainer.getGrid()
						.getSelectionModel().getSelectedItems();
				callback.call(authorities);
				hide();
			}
		});

		gridContainer
				.setInitDataDelayCallback(new DelayCallback<List<Authority>>() {
					@Override
					public void run(GwtCallBack<List<Authority>> callback) {
						ServiceUtil.AuthorityRpcCaller.get(callback);
					}
				});

		gridContainer.setHeight(200);
		gridContainer.setWidth(400);
		setWidget(gridContainer);

	}

	public void setCallback(GwtCallBack<List<Authority>> callback) {
		this.callback = callback;
	}
}
