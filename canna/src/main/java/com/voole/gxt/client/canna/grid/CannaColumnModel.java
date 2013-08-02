package com.voole.gxt.client.canna.grid;

import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

public class CannaColumnModel<T> extends ColumnModel<T> {

	private List<ColumnConfig<T, ?>> originalList;

	public CannaColumnModel(List<ColumnConfig<T, ?>> list) {
		super(list);
		this.originalList = list;
	}

	public List<ColumnConfig<T, ?>> getOriginalList() {
		return originalList;
	}

}
