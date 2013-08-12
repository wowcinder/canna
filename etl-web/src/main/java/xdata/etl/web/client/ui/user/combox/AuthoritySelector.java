/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user.combox;

import java.util.List;

import xdata.etl.web.client.common.editer.EditorWindow;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainerBuilder;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.service.authority.AuthorityService;
import xdata.etl.web.client.service.authority.AuthorityServiceAsync;
import xdata.etl.web.client.ui.authority.grid.AuthorityGrid;
import xdata.etl.web.shared.entity.authority.Authority;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 * 
 */
public class AuthoritySelector {
	private final EditorWindow root;
	private GwtCallBack<List<Authority>> callback;
	private boolean isInited = false;

	public AuthoritySelector() {
		root = new EditorWindow();
		root.setModal(true);

		final EtlGridContainer<Integer, Authority> gridContainer = new EtlGridContainer<Integer, Authority>(
				new AuthorityGrid(),
				GWT.<AuthorityServiceAsync> create(AuthorityService.class));

		EtlGridContainerBuilder<Integer, Authority> builder = new EtlGridContainerBuilder<Integer, Authority>(
				gridContainer);
		builder.setShowRemoveBt(false);
		builder.setUpdateEnabled(false);
		builder.setInitData(false);
		builder.setRealAddBtHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				List<Authority> authorities = gridContainer.getGrid()
						.getSelectionModel().getSelectedItems();
				callback.call(authorities);
				root.hide();
			}
		});
		builder.build();
		gridContainer.setHeight(200);
		gridContainer.setWidth(400);
		root.setWidget(gridContainer);

		root.addShowHandler(new ShowHandler() {
			@Override
			public void onShow(ShowEvent event) {
				if(!isInited){
					gridContainer.initData();
					isInited = true;
				}
			}
		});
	}

	public void show() {
		root.forceLayout();
		root.show();
	}

	public void setCallback(GwtCallBack<List<Authority>> callback) {
		this.callback = callback;
	}
}
