/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui;

import xdata.etl.web.client.event.CenterVievChangeEvent;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * @author XuehuiHe
 * @date 2013年8月19日
 */
public class MenuViewImpl2 extends VerticalLayoutContainer implements MenuView {
	@Inject
	private EventBus eventBus;

	public MenuViewImpl2() {
		TextButton tb = new TextButton("测试");
		this.add(tb);

		tb.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				eventBus.fireEvent(new CenterVievChangeEvent("hbase_query"));
			}
		});
	}

	@Override
	public void init() {

	}

	@Override
	public void showMenu(String token) {
		// TODO Auto-generated method stub

	}

}
