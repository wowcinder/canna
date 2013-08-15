/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.hbasemeta;

import java.io.Serializable;
import java.util.List;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseData<K extends Serializable> implements Serializable {
	private static final long serialVersionUID = 3596420320505551132L;
	private String table;
	private List<HbaseRecord<K>> data;

	public HbaseData() {
	}

	public HbaseData(String table) {
		this.table = table;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<HbaseRecord<K>> getData() {
		return data;
	}

	public void setData(List<HbaseRecord<K>> data) {
		this.data = data;
	}

}
