/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.hbase;

import java.io.Serializable;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseVersionData<K extends Serializable> extends HbaseData<K> {
	private static final long serialVersionUID = -8135088790153847949L;
	private String version;

	public HbaseVersionData() {
	}

	public HbaseVersionData(String table, String version) {
		super(table);
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
