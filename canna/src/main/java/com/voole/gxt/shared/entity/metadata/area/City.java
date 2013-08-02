package com.voole.gxt.shared.entity.metadata.area;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "meta_area_city")
public class City implements Serializable {
	private static final long serialVersionUID = 8081255150352357325L;
	@Id
	private int id;
	@Column(length = 20, nullable = false)
	private String name;
	private int lat = 0;
	private int lon = 0;
	@ManyToOne
	@JoinColumn(name = "pid")
	private Province province;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
		this.lon = lon;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
}
