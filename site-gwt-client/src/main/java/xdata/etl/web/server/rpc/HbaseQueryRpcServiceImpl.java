/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.ui.hbasequery.HbaseQueryPagingLoadResultBean;
import xdata.etl.web.server.annotations.AccessAuthority;
import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.dao.hbasemeta.HbaseTableDao;
import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.common.paging.HbaseQueryPagingCondition;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn.HbaseTableColumnType;
import xdata.etl.web.shared.exception.SharedException;
import xdata.etl.web.shared.hbasequery.HbaseRecord;
import xdata.etl.web.shared.service.hbasequery.HbaseQueryRpcService;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年8月16日
 */
@Service
@AccessAuthorityGroup("hbase_query")
public class HbaseQueryRpcServiceImpl implements HbaseQueryRpcService {
	private static final int TOTAL_ITEMS = 5000;

	public static final Configuration config = HBaseConfiguration.create();
	@Autowired
	private HbaseTableDao metaDao;

	protected Set<String> getAllColumns(
			Map<String, Set<HbaseTableColumn>> versionToColumnMeta) {
		Set<String> result = new HashSet<String>();
		for (String version : versionToColumnMeta.keySet()) {
			Set<HbaseTableColumn> columns = versionToColumnMeta.get(version);
			for (HbaseTableColumn hbaseTableColumn : columns) {
				if (!result.contains(hbaseTableColumn.getName())) {
					result.add(hbaseTableColumn.getName());
				}
			}
		}
		return result;
	}

	protected HbaseRecord<String> getRecord(
			Map<String, Set<HbaseTableColumn>> versionToColumnMeta,
			Result result) {
		HbaseRecord<String> record = new HbaseRecord<String>();
		Set<HbaseTableColumn> columnMeta = versionToColumnMeta.get("");
		record.setKey(Bytes.toString(result.getRow()));

		for (HbaseTableColumn hbaseTableColumn : columnMeta) {
			byte[] value = result.getValue(Bytes.toBytes("d"),
					Bytes.toBytes(hbaseTableColumn.getName()));

			record.getData().put(hbaseTableColumn.getName(),
					getValue(value, hbaseTableColumn.getType()));
		}
		return record;
	}

	protected Object getValue(byte[] b, HbaseTableColumnType type) {
		if (b == null) {
			return null;
		}
		Class<?> clazz = type.getClazz();
		if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
			return Bytes.toBoolean(b);
		} else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
			return Bytes.toLong(b);
		} else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
			return Bytes.toShort(b);
		} else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
			return Bytes.toDouble(b);
		} else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
			return Bytes.toFloat(b);
		} else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
			return Bytes.toInt(b);
		} else if (clazz.equals(String.class)) {
			return Bytes.toString(b);
		} else if (clazz.equals(BigDecimal.class)) {
			return Bytes.toBigDecimal(b);
		} else if (clazz.equals(Date.class)) {
			Date d = new Date();
			d.setTime(Bytes.toLong(b));
			return d;
		} else if (clazz.equals(BigInteger.class)) {
			return new BigInteger(b);
		}
		return null;
	}

	public HbaseTableDao getMetaDao() {
		return metaDao;
	}

	public void setMetaDao(HbaseTableDao metaDao) {
		this.metaDao = metaDao;
	}

	public static class VersionComparatorHelper {
		private List<Filter> filters;
		private String[] versions;
		private boolean isContainsEmptyVersion = false;

		public VersionComparatorHelper(String[] versions) {
			this.versions = versions;
			filters = new ArrayList<Filter>();
			init();
		}

		private void init() {
			for (String version : versions) {
				if (version.length() == 0) {
					isContainsEmptyVersion = true;
					break;
				}
			}
			for (String version : versions) {
				if (version.length() != 0) {
					SingleColumnValueFilter f = new SingleColumnValueFilter(
							Bytes.toBytes("d"), Bytes.toBytes("version"),
							CompareOp.EQUAL, Bytes.toBytes(version));
					f.setFilterIfMissing(!isContainsEmptyVersion);
					filters.add(f);
				}
			}
		}

		public FilterList getFilters() {
			FilterList list = new FilterList(FilterList.Operator.MUST_PASS_ONE,
					filters);
			return list;
		}
	}

	@Override
	public Integer dummyInteger() {
		return null;
	}

	@Override
	public Short dummyShort() {
		return null;
	}

	@Override
	public Long dummyLong() {
		return null;
	}

	@Override
	public Boolean dummyBoolean() {
		return null;
	}

	@Override
	public Date dummyDate() {
		return null;
	}

	@Override
	public Character dummyCharacter() {
		return null;
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

	@Override
	@AccessAuthority("查询")
	public PagingLoadResult<HbaseRecord<String>> get(
			EtlPagingLoadConfigBean config) throws SharedException {
		HbaseQueryPagingLoadResultBean<HbaseRecord<String>> pr = new HbaseQueryPagingLoadResultBean<HbaseRecord<String>>();

		pr.setOffset(config.getOffset());
		pr.setTotalLength(TOTAL_ITEMS);

		HbaseQueryPagingCondition condition = (HbaseQueryPagingCondition) config
				.getCondition();
		queryData(condition, pr, config);
		return pr;
	}

	/**
	 * @param condition
	 * @param pr
	 */
	private void queryData(HbaseQueryPagingCondition condition,
			HbaseQueryPagingLoadResultBean<HbaseRecord<String>> pr,
			EtlPagingLoadConfigBean loadConfig) {
		int limit = loadConfig.getLimit();
		String tableName = condition.getTableName();
		String[] versions = condition.getVersions();

		Map<String, Set<HbaseTableColumn>> versionToColumnMeta = metaDao
				.getMetaForQuery(tableName, versions);
		Set<String> allColumns = getAllColumns(versionToColumnMeta);
		List<HbaseRecord<String>> list = new ArrayList<HbaseRecord<String>>();
		HTable table = null;
		try {
			table = new HTable(config, tableName);

			Scan scan = new Scan();
			scan.setCaching(limit);
			scan.setBatch(allColumns.size());
			for (String column : allColumns) {
				scan.addColumn(Bytes.toBytes("d"), Bytes.toBytes(column));
			}
			if (versions != null && versions.length > 0) {
				VersionComparatorHelper helper = new VersionComparatorHelper(
						versions);
				scan.setFilter(helper.getFilters());
			}
			if (condition.getLastRow() != null) {
				System.out.println("start row :" + condition.getLastRow());
				scan.setStartRow(Bytes.toBytes(condition.getLastRow() + "a"));
			} else {
				System.out.println("empty start row");
			}

			ResultScanner rs = table.getScanner(scan);

			int i = 1;
			for (Result result : rs) {
				HbaseRecord<String> record = getRecord(versionToColumnMeta,
						result);
				if (record != null) {
					list.add(record);
				}
				if (i >= limit) {
					break;
				}
				i++;
			}
			if (i < limit) {
				pr.setTotalLength(loadConfig.getOffset() + i - 1);
			}
		} catch (IOException e) {
			throw new SharedException("hbase query error", e);
		} finally {
			if (table != null) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		if (list.size() > 0) {
			pr.setLastRow(list.get(list.size() - 1).getKey());
		}
		pr.setData(list);
	}

	@Override
	public Double dummyDouble() {
		// TODO Auto-generated method stub
		return null;
	}
}
