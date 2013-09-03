/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta.c;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import xdata.etl.web.shared.entity.businessmeta.BusinessVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@DiscriminatorValue("vertical_bar_split")
public class VerticalBarSplitBusinessVersion extends BusinessVersion {
	private static final long serialVersionUID = -8334018254059047467L;

	public VerticalBarSplitBusiness getBusiness() {
		return (VerticalBarSplitBusiness) business;
	}

}
