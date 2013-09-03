/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta.json;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import xdata.etl.web.shared.entity.businessmeta.BusinessVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_version_json")
public class JsonBusinessVersion extends BusinessVersion {
	private static final long serialVersionUID = 7191018783459845393L;

	public JsonBusiness getBusiness() {
		return (JsonBusiness) business;
	}
	
	@SuppressWarnings("unchecked")
	public List<JsonBusinessColumn> getColumns() {
		return (List<JsonBusinessColumn>) super.getColumns();
	}
}
