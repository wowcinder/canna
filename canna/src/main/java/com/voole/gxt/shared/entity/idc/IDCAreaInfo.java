package com.voole.gxt.shared.entity.idc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.AccessType;

import com.voole.gxt.shared.entity.metadata.area.City;
import com.voole.gxt.shared.entity.metadata.area.Province;

@Embeddable
@AccessType("property")
public class IDCAreaInfo implements Serializable {
	private static final long serialVersionUID = -6783902688725950766L;
	private Province province;
	private City city;
	private String address;

	public IDCAreaInfo() {
	}

	@ManyToOne
	@JoinColumn(name = "province_id")
	public Province getProvince() {
		return province;
	}

	@ManyToOne
	@JoinColumn(name = "city_id")
	public City getCity() {
		return city;
	}

	@Column(length = 200)
	public String getAddress() {
		return address;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
