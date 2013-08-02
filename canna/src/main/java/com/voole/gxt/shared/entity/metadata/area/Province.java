package com.voole.gxt.shared.entity.metadata.area;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "meta_area_province")
public class Province implements Serializable {
	private static final long serialVersionUID = 3636887671281430860L;
	@Id
	private int id;
	@Column(length = 20, nullable = false)
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "province")
	@Fetch(FetchMode.SUBSELECT)
	private List<City> cities;

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

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}
