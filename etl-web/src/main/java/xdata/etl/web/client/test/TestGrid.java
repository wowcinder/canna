/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xdata.etl.web.shared.hbasemeta.HbaseRecord;
import xdata.etl.web.shared.hbasemeta.HbaseRecordValueProvider;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class TestGrid extends Grid<HbaseRecord<Integer>> {

	private static class EtlColumnModel<M> extends ColumnModel<M> {
		private List<ColumnConfig<M, ?>> cc;

		public EtlColumnModel(final List<ColumnConfig<M, ?>> list) {
			super(list);
			cc = list;
		}

		public List<ColumnConfig<M, ?>> getCc() {
			return cc;
		}
	}

	public TestGrid() {
		super(new ListStore<HbaseRecord<Integer>>(
				new ModelKeyProvider<HbaseRecord<Integer>>() {

					@Override
					public String getKey(HbaseRecord<Integer> item) {
						return item.getKey().toString();
					}
				}), new EtlColumnModel<HbaseRecord<Integer>>(
				new ArrayList<ColumnConfig<HbaseRecord<Integer>, ?>>()));

		initView();
		initData();
	}

	private void initData() {
		List<HbaseRecord<Integer>> list = new ArrayList<HbaseRecord<Integer>>();
		HbaseRecord<Integer> record = new HbaseRecord<Integer>();
		record.setKey(1);
		record.getData().put("kkk", "this is KKK");
		list.add(record);

		record = new HbaseRecord<Integer>();
		record.setKey(2);
		record.getData().put("kkk", new Integer(2));
		list.add(record);

		record = new HbaseRecord<Integer>();
		record.setKey(3);
		record.getData().put("kkk", new Short((short) 1));
		list.add(record);

		record = new HbaseRecord<Integer>();
		record.setKey(4);
		record.getData().put("kkk", new Long((long) 1));
		list.add(record);

		record = new HbaseRecord<Integer>();
		record.setKey(5);
		record.getData().put("kkk", new Float(1f));
		list.add(record);

		record = new HbaseRecord<Integer>();
		record.setKey(6);
		record.getData().put("kkk", new Double(1d));
		list.add(record);

		record = new HbaseRecord<Integer>();
		record.setKey(7);
		record.getData().put("kkk", new Character('c'));
		list.add(record);

		record = new HbaseRecord<Integer>();
		record.setKey(8);
		record.getData().put("kkk", new Date());
		list.add(record);

		getStore().addAll(list);
	}

	private void initView() {
		ColumnConfig<HbaseRecord<Integer>, Object> cc = new ColumnConfig<HbaseRecord<Integer>, Object>(
				new HbaseRecordValueProvider("kkk"), 200, "kkk");
		cc.setCell(new SimpleSafeHtmlCell<Object>(
				new AbstractSafeHtmlRenderer<Object>() {

					@Override
					public SafeHtml render(Object object) {
						if (object != null) {
							return SafeHtmlUtils.fromSafeConstant(object
									.toString());
						}
						return null;
					}
				}));
		getColumnModel().getCc().add(cc);
	}

	@Override
	public EtlColumnModel<HbaseRecord<Integer>> getColumnModel() {
		return (EtlColumnModel<HbaseRecord<Integer>>) super.getColumnModel();
	}
}
