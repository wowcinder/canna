package com.voole.gxt.shared.entity.device;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.voole.gxt.shared.entity.CannaEntity;
import com.voole.gxt.shared.entity.idc.IDCIP;

@Entity
@Table(name = "device_super")
public class SuperDevice implements Serializable, CannaEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3070307991631581616L;
	private long id;
	private String name;
	private IDCIP idcip;

	public SuperDevice() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@Column(length = 20)
	public String getName() {
		return name;
	}

	@OneToOne(mappedBy = "device", fetch = FetchType.LAZY)
	public IDCIP getIdcip() {
		return idcip;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdcip(IDCIP idcip) {
		this.idcip = idcip;
	}

}
