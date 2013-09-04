/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta.json;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@DiscriminatorValue(BusinessType.Names.JSON_TYPE)
public class JsonBusinessToHbaseTableMapping extends
		BusinessToHbaseTableMapping {
	private static final long serialVersionUID = 6878342659450824345L;

	@SuppressWarnings("unchecked")
	public List<JsonBusinessColumn> getColumns() {
		return (List<JsonBusinessColumn>) super.getColumns();
	}
}
