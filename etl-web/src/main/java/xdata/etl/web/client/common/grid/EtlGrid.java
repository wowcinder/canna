/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.common.grid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xdata.etl.web.client.property.RpcEntityPropertyAccess;
import xdata.etl.web.shared.entity.RpcEntity;

import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public abstract class EtlGrid<K extends Serializable, V extends RpcEntity<K>>
		extends Grid<V> {
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

	private final List<ColumnConfig<V, ?>> columnConfigs;
	protected final RpcEntityPropertyAccess<K, V> props;
	protected final boolean isMultiSelect;

	public EtlGrid(RpcEntityPropertyAccess<K, V> props, boolean isMultiSelect) {
		super(new ListStore<V>(props.key()), new EtlColumnModel<V>(
				new ArrayList<ColumnConfig<V, ?>>()));

		columnConfigs = ((EtlColumnModel<V>) getColumnModel()).getCc();

		this.props = props;
		this.isMultiSelect = isMultiSelect;

		init();
		setLoadMask(true);
	}

	protected abstract void initColumnModel();

	protected void init() {
		initSelectModel();
		initColumnModel();
		getView().setForceFit(true);
	}

	protected void initSelectModel() {
		if (!isMultiSelect()) {
			return;
		}
		IdentityValueProvider<V> identity = new IdentityValueProvider<V>();
		CheckBoxSelectionModel<V> sm = new CheckBoxSelectionModel<V>(identity);
		sm.setSelectionMode(SelectionMode.MULTI);
		setSelectionModel(sm);
		getColumnConfigs().add(sm.getColumn());
	}

	public EtlGrid(RpcEntityPropertyAccess<K, V> props) {
		this(props, true);
	}

	@Override
	public CheckBoxSelectionModel<V> getSelectionModel() {
		return (CheckBoxSelectionModel<V>) super.getSelectionModel();
	}

	public List<ColumnConfig<V, ?>> getColumnConfigs() {
		return columnConfigs;
	}

	public RpcEntityPropertyAccess<K, V> getProps() {
		return props;
	}

	public boolean isMultiSelect() {
		return isMultiSelect;
	}

}
