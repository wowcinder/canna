/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.entity.IdentityRpcEntity;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_version")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "btype", length = 20)
public abstract class BusinessVersion extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = -4849509631471684174L;
	@Column(length = 40, nullable = true)
	@Length(min = 1, max = 40)
	private String version = "";

	@ManyToOne(optional = false)
	@JoinColumn(name = "mapping_id")
	private BusinessToHbaseTableMapping mapping;

	@ManyToOne
	@JoinColumn(name = "business_id")
	protected Business business;

	@Enumerated(EnumType.STRING)
	@Column(name = "btype", insertable = false, updatable = false)
	private BusinessType type;

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public BusinessToHbaseTableMapping getMapping() {
		return mapping;
	}

	public void setMapping(BusinessToHbaseTableMapping mapping) {
		this.mapping = mapping;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public BusinessType getType() {
		return type;
	}

	public void setType(BusinessType type) {
		this.type = type;
	}

}
