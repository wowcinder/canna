/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta.json;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_column_json_sub_mapping")
public class SubMappingJsonBusinessColumn extends JsonBusinessColumn {
	private static final long serialVersionUID = -1420027564993173337L;
	@ManyToOne
	@JoinColumn(name = "sub_mapping_id")
	private BusinessToHbaseTableMapping subMapping;

	public BusinessToHbaseTableMapping getSubMapping() {
		return subMapping;
	}

	public void setSubMapping(BusinessToHbaseTableMapping subMapping) {
		this.subMapping = subMapping;
	}

}
