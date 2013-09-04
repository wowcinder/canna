/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta;

import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public interface SimpleBusinessColumn {
	public HbaseTableColumn getHbaseTableColumn();

	public void setHbaseTableColumn(HbaseTableColumn hbaseTableColumn);
}
