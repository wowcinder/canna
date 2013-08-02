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
@Table(name = "idc_hosting")
public class IDCHosting implements Serializable, CannaEntity {
	private static final long serialVersionUID = 2314216804268376653L;

	private long id;
	private String name;
	private List<IDC> idcs;
	public IDCHosting() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@Column(length = 50, nullable = false)
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "hosting")
	public List<IDC> getIdcs() {
		return idcs;
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
