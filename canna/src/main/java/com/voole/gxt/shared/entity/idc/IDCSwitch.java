package com.voole.gxt.shared.entity.idc;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.voole.gxt.shared.entity.CannaEntity;

@Entity
@Table(name = "idc_switch")
public class IDCSwitch implements Serializable, CannaEntity {
	private static final long serialVersionUID = -1150565319708878422L;
	private long id;
	private String name;
	private IDC idc;
	private List<IDCIP> idcips;
	public IDCSwitch() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@Column(length = 10, nullable = false)
	public String getName() {
		return name;
	}

	@ManyToOne
	@JoinColumn(name = "idc_id", nullable = false)
	public IDC getIdc() {
		return idc;
	}

	@OneToMany(mappedBy = "switzh")
	public List<IDCIP> getIdcips() {
		return idcips;
	}

	public void setIdcips(List<IDCIP> idcips) {
		this.idcips = idcips;
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
