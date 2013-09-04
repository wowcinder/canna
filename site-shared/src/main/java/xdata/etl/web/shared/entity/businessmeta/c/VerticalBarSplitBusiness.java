/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta.c;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.entity.businessmeta.Business;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@DiscriminatorValue(BusinessType.Names.C_TYPE)
public class VerticalBarSplitBusiness extends Business {
	private static final long serialVersionUID = 6485751341866927907L;

	@SuppressWarnings("unchecked")
	public List<VerticalBarSplitBusinessVersion> getVersions() {
		return (List<VerticalBarSplitBusinessVersion>) super.getVersions();
	}
}
