package com.voole.gxt.shared.entity.idc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.voole.gxt.shared.entity.CannaEntity;
@Entity
@Table(name = "idc_cabinet")
public class IDCCabinet implements Serializable , CannaEntity{
	private static final long serialVersionUID = -1515223362249542104L;
	private long id;
	private String name;
	private IDC idc;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@Column(length = 20)
	public String getName() {
		return name;
	}

	@ManyToOne
	@JoinColumn(name = "idc_id")
	public IDC getIdc() {
		return idc;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdc(IDC idc) {
		this.idc = idc;
	}
}
