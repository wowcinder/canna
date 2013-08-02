package com.voole.gxt.shared.entity.metadata.network;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meta_network_type")
public class NetworkType implements Serializable {
	private static final long serialVersionUID = -361309469976927115L;
	private int id;
	private String name;

	@Id
	public int getId() {
		return id;
	}

	@Column(length = 20, nullable = false)
	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
