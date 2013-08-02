package com.voole.gxt.client.canna.grid;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.voole.gxt.client.property.CannaPropertyAccess;

public abstract class CannaGrid<T> extends Grid<T> {

	protected List<ColumnConfig<T, ?>> ccList;
	protected CannaPropertyAccess<T> props;
	protected boolean isMultiSelect = true;

	public CannaGrid(CannaPropertyAccess<T> props, boolean isMultiSelect) {
		super(new ListStore<T>(props.key()), new CannaColumnModel<T>(
				new ArrayList<ColumnConfig<T, ?>>()));
		this.isMultiSelect = isMultiSelect;
		ccList = ((CannaColumnModel<T>) getColumnModel()).getOriginalList();
		init();
		setLoadMask(true);
	}

	public CannaGrid(CannaPropertyAccess<T> props) {
		this(props, true);
	}

	protected abstract void initColumnModel();

	public void init() {
		initSelectModel();
		initColumnModel();
		getView().setForceFit(true);
	}

	protected void initSelectModel() {
		if (!isMultiSelect) {
			return;
		}
		IdentityValueProvider<T> identity = new IdentityValueProvider<T>();
		CheckBoxSelectionModel<T> sm = new CheckBoxSelectionModel<T>(identity);
		sm.setSelectionMode(SelectionMode.MULTI);
		setSelectionModel(sm);
		ccList.add(sm.getColumn());
	}

	@Override
	public CheckBoxSelectionModel<T> getSelectionModel() {
		return (CheckBoxSelectionModel<T>) super.getSelectionModel();
	}

	public List<ColumnConfig<T, ?>> getCcList() {
		return ccList;
	}

	public void setCcList(List<ColumnConfig<T, ?>> ccList) {
		this.ccList = ccList;
	}

	public CannaPropertyAccess<T> getProps() {
		return props;
	}

	public void setProps(CannaPropertyAccess<T> props) {
		this.props = props;
	}

	public boolean isMultiSelect() {
		return isMultiSelect;
	}

	public void setMultiSelect(boolean isMultiSelect) {
		this.isMultiSelect = isMultiSelect;
	}

}
