/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta.json;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import xdata.etl.web.shared.entity.businessmeta.BusinessVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@DiscriminatorValue("json")
public class JsonBusinessVersion extends BusinessVersion {
	private static final long serialVersionUID = 7191018783459845393L;

	public JsonBusiness getBusiness() {
		return (JsonBusiness) business;
	}

}
