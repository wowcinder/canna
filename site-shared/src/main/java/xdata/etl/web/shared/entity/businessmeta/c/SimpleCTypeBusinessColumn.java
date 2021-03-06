/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta.c;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xdata.etl.web.shared.BusinessType.BusinessColumnType;
import xdata.etl.web.shared.entity.businessmeta.SimpleBusinessColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_column_c_simple")
public class SimpleCTypeBusinessColumn extends
		CTypeBusinessColumn implements SimpleBusinessColumn {
	private static final long serialVersionUID = 4577673684471512216L;
	@ManyToOne
	@JoinColumn(name = "hbase_table_column_id")
	private HbaseTableColumn hbaseTableColumn;

	public HbaseTableColumn getHbaseTableColumn() {
		return hbaseTableColumn;
	}

	public void setHbaseTableColumn(HbaseTableColumn hbaseTableColumn) {
		this.hbaseTableColumn = hbaseTableColumn;
	}

	@Override
	public BusinessColumnType getColumnType() {
		return BusinessColumnType.SIMPLE;
	}
}
