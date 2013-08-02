package com.voole.gxt.shared.entity.idc;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.voole.gxt.shared.entity.CannaEntity;

@Entity
@Table(name = "idc_lease_type")
public class IDCLeaseType implements Serializable, CannaEntity {
	private static final long serialVersionUID = -4668107397541525974L;
	private long id;
	private String name;
	private int value;
	private List<IDC> idcs;

	public IDCLeaseType() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@Column(length = 20, nullable = false)
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "idcLeaseType")
	public List<IDC> getIdcs() {
		return idcs;
	}

	@Column(unique = true)
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setIdcs(List<IDC> idcs) {
		this.idcs = idcs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}

}
