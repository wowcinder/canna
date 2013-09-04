package xdata.etl.web.client.common.grid;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class GridBuilder<M> {
	protected boolean isMultiSelect;
	protected ModelKeyProvider<M> keyProvider;
	protected List<ColumnConfig<M, ?>> columnConfigs;

	public GridBuilder() {
		this(true);
	}

	public GridBuilder(boolean isMultiSelect) {
		this.isMultiSelect = isMultiSelect;
		columnConfigs = new ArrayList<ColumnConfig<M, ?>>();
	}

	public Grid<M> create() {
		CheckBoxSelectionModel<M> sm = null;
		if (isMultiSelect) {
			IdentityValueProvider<M> identity = new IdentityValueProvider<M>();
			sm = new CheckBoxSelectionModel<M>(identity);
			sm.setSelectionMode(SelectionMode.MULTI);
			columnConfigs.add(0, sm.getColumn());
		}
		initColumnModel();
		Grid<M> grid = newGrid();
		if (isMultiSelect) {
			grid.setSelectionModel(sm);
		}
		grid.setLoadMask(true);
		return grid;
	}

	protected Grid<M> newGrid() {
		return new Grid<M>(new ListStore<M>(keyProvider), new ColumnModel<M>(
				columnConfigs));
	}

	protected void initColumnModel() {

	}

	public ModelKeyProvider<M> getKeyProvider() {
		return keyProvider;
	}

	public void setKeyProvider(ModelKeyProvider<M> keyProvider) {
		this.keyProvider = keyProvider;
	}

	public List<ColumnConfig<M, ?>> getColumnConfigs() {
		return columnConfigs;
	}

	public void setColumnConfigs(List<ColumnConfig<M, ?>> columnConfigs) {
		this.columnConfigs = columnConfigs;
	}

	public boolean isMultiSelect() {
		return isMultiSelect;
	}

	public void addColumnConfig(ColumnConfig<M, ?> config) {
		getColumnConfigs().add(config);
	}

	public void setMultiSelect(boolean isMultiSelect) {
		this.isMultiSelect = isMultiSelect;
	}
}
