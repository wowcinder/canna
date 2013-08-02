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

import org.hibernate.annotations.Type;

import com.voole.gxt.shared.entity.CannaEntity;
import com.voole.gxt.shared.entity.device.SuperDevice;
import com.voole.gxt.shared.entity.metadata.network.NetworkType;

@Entity
@Table(name = "idc_ip")
public class IDCIP implements Serializable , CannaEntity{
	private static final long serialVersionUID = 2660956176851685144L;
	private long id;
	private String ip;
	private String mark;
	private NetworkType networkType;
	private boolean status;
	private SuperDevice device;
	private IDCSwitch switzh;
	private IDC idc;

	public IDCIP() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@Column(length = 20)
	public String getIp() {
		return ip;
	}

	@Column(length = 40)
	public String getMark() {
		return mark;
	}

	@ManyToOne
	@JoinColumn(name = "networktype_id")
	public NetworkType getNetworkType() {
		return networkType;
	}

	@Type(type = "boolean")
	public boolean isStatus() {
		return status;
	}

	@OneToOne
	@JoinColumn(name = "device_id")
	public SuperDevice getDevice() {
		return device;
	}

	@ManyToOne
	@JoinColumn(name = "switch_id")
	public IDCSwitch getSwitzh() {
		return switzh;
	}

	@ManyToOne
	@JoinColumn(name = "idc_id")
	public IDC getIdc() {
		return idc;
	}

	public void setIdc(IDC idc) {
		this.idc = idc;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public void setNetworkType(NetworkType networkType) {
		this.networkType = networkType;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setDevice(SuperDevice device) {
		this.device = device;
	}

	public void setSwitzh(IDCSwitch switzh) {
		this.switzh = switzh;
	}
}
