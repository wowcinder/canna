/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xdata.etl.web.client.common.grid.RpcEntityGridBuilder;
import xdata.etl.web.client.property.businessmeta.BusinessColumnProperty;
import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.BusinessType.BusinessColumnType;
import xdata.etl.web.shared.entity.businessmeta.BusinessColumn;
import xdata.etl.web.shared.entity.businessmeta.SimpleBusinessColumn;
import xdata.etl.web.shared.entity.businessmeta.SubMappingBusinessColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public class BusinessColumnGrid<T extends BusinessColumn, P extends BusinessColumnProperty<T>>
		extends RpcEntityGridBuilder<Integer, T, P> {

	public BusinessColumnGrid(P props) {
		super(props);
	}

	@Override
	protected BusinessColGird newGrid() {
		return this.new BusinessColGird(new ListStore<T>(keyProvider),
				new ColumnModel<T>(columnConfigs));
	}

	@Override
	protected void initColumnModel() {
		super.initColumnModel();
		ColumnConfig<T, BusinessColumnType> columnTypeCC = new ColumnConfig<T, BusinessColumnType>(
				getProps().columnType(), 200, "columnType");
		ColumnConfig<T, BusinessType> businessTypeCC = new ColumnConfig<T, BusinessType>(
				getProps().businessType(), 200, "businessType");
		ColumnConfig<T, T> selfCC = new ColumnConfig<T, T>(getProps().self(),
				200, "hbase_mapping");
		selfCC.setCell(new SimpleSafeHtmlCell<T>(
				new AbstractSafeHtmlRenderer<T>() {
					@Override
					public SafeHtml render(T object) {
						if (object instanceof SimpleBusinessColumn) {
							SimpleBusinessColumn simple = (SimpleBusinessColumn) object;
							return SafeHtmlUtils.fromString(simple
									.getHbaseTableColumn().getName());
						} else if (object instanceof SubMappingBusinessColumn) {
							SubMappingBusinessColumn<?> subMapping = (SubMappingBusinessColumn<?>) object;
							HbaseTableVersion version = subMapping
									.getSubMapping().getHbaseTableVersion();
							return SafeHtmlUtils.fromString(version.getTable()
									.getName() + "#" + version.getVersion());
						}
						return null;
					}
				}));
		ColumnConfig<T, String> descCC = new ColumnConfig<T, String>(getProps()
				.desc(), 200, "desc");

		getColumnConfigs().add(selfCC);
		getColumnConfigs().add(businessTypeCC);
		getColumnConfigs().add(columnTypeCC);
		getColumnConfigs().add(descCC);

	}

	public class BusinessColGird extends Grid<T> {
		private Map<Integer, List<T>> hbaseVersionToColumns;
		private HbaseTableVersion oldVersion;

		public BusinessColGird(ListStore<T> listStore,
				ColumnModel<T> columnModel) {
			super(listStore, columnModel);
			hbaseVersionToColumns = new HashMap<Integer, List<T>>();
		}

		public void changeHbaseTableVersion(HbaseTableVersion version) {
			if (isVersionChange(version)) {
				saveOldVersionSetting();
				loadNewVersionSetting(version);
				oldVersion = version;
			}
		}

		public void init() {
			hbaseVersionToColumns.clear();
			oldVersion = null;
		}

		private boolean isVersionChange(HbaseTableVersion version) {
			if (version == null && oldVersion == null) {
				return false;
			} else if (version == null || oldVersion == null) {
				return true;
			} else {
				return !oldVersion.getId().equals(version.getId());
			}
		}

		private void saveOldVersionSetting() {
			if (oldVersion == null || getStore().size() == 0) {
				return;
			}
			hbaseVersionToColumns.put(oldVersion.getId(), getStore().getAll());
		}

		private void loadNewVersionSetting(HbaseTableVersion version) {
			getStore().clear();
			if (version != null
					&& hbaseVersionToColumns.containsKey(version.getId())) {
				getStore().addAll(hbaseVersionToColumns.get(version.getId()));
			}
		}
	}

}
