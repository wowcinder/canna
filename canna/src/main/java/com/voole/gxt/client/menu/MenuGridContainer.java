package com.voole.gxt.client.menu;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainer;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainerConfig;
import com.voole.gxt.client.rpcclient.callback.SaveOrderCallback;
import com.voole.gxt.client.rpcclient.menu.MenuRpcClient;
import com.voole.gxt.shared.entity.menu.Menu;

public class MenuGridContainer extends CannaGridContainer<Menu> implements
		SaveOrderCallback {
	protected TextButton saveOrderBt;
	private static CannaGridContainerConfig gridConfig;
	static{
		gridConfig = new CannaGridContainerConfig();
		gridConfig.isPaging = false;
	}

	public MenuGridContainer() {
		super(new MenuGrid(),new MenuRpcClient(),gridConfig);
		setEditor(new MenuEditor());
		saveOrderBt = new TextButton("保存菜单顺序");
		bar.add(saveOrderBt);
		saveOrderBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				saveOrder();
			}
		});
	}

	protected void saveOrder() {
		List<Menu> list = getStore().getAll();
		List<Long> ids = new ArrayList<Long>();
		for (Menu menu : list) {
			ids.add(menu.getId());
		}
		saveOrderBt.disable();
		getRpc().saveOrder(ids, this);
	}

	@Override
	public void postSaveOrder() {
		saveOrderBt.enable();
	}

	@Override
	protected void noFoundDeleteItems() {
		AlertMessageBox d = new AlertMessageBox("删除", "请选择需要删除的菜单!");
		d.show();
	}

	@Override
	public MenuRpcClient getRpc() {
		return (MenuRpcClient) super.getRpc();
	}

	@Override
	public int getPageSize() {
		return 3;
	}
}
