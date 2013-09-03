/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta.json;

import java.util.List;

import javax.persistence.DiscriminatorValue;

import xdata.etl.web.shared.entity.businessmeta.Business;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@DiscriminatorValue("json")
public class JsonBusiness extends Business {
	private static final long serialVersionUID = 2418545369320700277L;

	@SuppressWarnings("unchecked")
	public List<JsonBusinessVersion> getVersions() {
		return (List<JsonBusinessVersion>) super.getVersions();
	}
}
