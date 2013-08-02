package com.voole.gxt.shared.entity.idc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.voole.gxt.shared.entity.CannaEntity;
import com.voole.gxt.shared.entity.device.SuperDevice;

@Entity
@Table(name = "idc_u_place")
public class IDCUPlace implements Serializable, CannaEntity {
	private static final long serialVersionUID = -985722128405058111L;
	private long id;
	private String uname;
	private IDCCabinet cabinet;
	private SuperDevice device;

	public IDCUPlace() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@Column(length = 20, nullable = false)
	public String getUname() {
		return uname;
	}

	@ManyToOne
	@JoinColumn(name = "cabinet_id")
	public IDCCabinet getCabinet() {
		return cabinet;
	}

	@OneToOne
	@JoinColumn(name = "device_id")
	public SuperDevice getDevice() {
		return device;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setCabinet(IDCCabinet cabinet) {
		this.cabinet = cabinet;
	}

	public void setDevice(SuperDevice device) {
		this.device = device;
	}

}
