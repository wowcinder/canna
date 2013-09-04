/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.BusinessType.BusinessColumnType;
import xdata.etl.web.shared.entity.IdentityRpcEntity;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_column")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BusinessColumn extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = 1930104423164074284L;

	@ManyToOne
	@JoinColumn(name = "mapping_id")
	private BusinessToHbaseTableMapping mapping;

	public BusinessColumn() {
	}

	public BusinessToHbaseTableMapping getMapping() {
		return mapping;
	}

	public void setMapping(BusinessToHbaseTableMapping mapping) {
		this.mapping = mapping;
	}

	public abstract BusinessColumnType getColumnType();
	public abstract BusinessType getBusinessType();

}
