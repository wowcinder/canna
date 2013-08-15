/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasequery;

import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.shared.annotations.MenuToken;

import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 * 
 */
@MenuToken(name = "hbase查询", token = "hbase_query")
public class HbaseQueryView extends CenterView {

	public HbaseQueryView() {
		super();

		final VerticalLayoutContainer v = new VerticalLayoutContainer();
		HbaseQueryGridBuild build = new HbaseQueryGridBuild("hbase_test",
				"1.0", "2.0");
		build.create(new GwtCallBack<HbaseQueryGrid>() {

			@Override
			public void call(HbaseQueryGrid grid) {
				v.add(grid);
			}
		});
		
		con.setWidget(v);
	}
}
