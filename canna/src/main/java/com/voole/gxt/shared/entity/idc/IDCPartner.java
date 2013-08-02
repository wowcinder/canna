package com.voole.gxt.shared.entity.idc;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.voole.gxt.shared.entity.CannaEntity;
import com.voole.gxt.shared.entity.Partner;

@Entity
@Table(name = "idc_partner")
public class IDCPartner extends Partner implements Serializable , CannaEntity{
	private static final long serialVersionUID = -5587210737393346702L;
	private IDC idc;
	
	public IDCPartner() {
	}

	@ManyToOne
	@JoinColumn(name = "idc_id")
	public IDC getIdc() {
		return idc;
	}

	public void setIdc(IDC idc) {
		this.idc = idc;
	}

}
