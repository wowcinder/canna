package com.voole.gxt.shared.entity.idc;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
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
@Table(name = "idc")
public class IDC implements Serializable, CannaEntity {
	private static final long serialVersionUID = 6824496687938447867L;
	private long id;
	private int deviceCount = 0;
	private IDCLeaseType idcLeaseType;
	private IDCHosting hosting;
	private IDCNetworkInfo networkInfo;
	private IDCAreaInfo areaInfo;
	private List<IDCPartner> partners;
	private List<IDCSwitch> switchs;
	private List<IDCIP> idcips;
	private List<IDCCabinet> cabinets;
	
	public IDC() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public int getDeviceCount() {
		return deviceCount;
	}

	@ManyToOne
	@JoinColumn(name = "idc_leasetype_id")
	public IDCLeaseType getIdcLeaseType() {
		return idcLeaseType;
	}

	@ManyToOne
	@JoinColumn(name = "hosting_id")
	public IDCHosting getHosting() {
		return hosting;
	}

	@Embedded
	public IDCNetworkInfo getNetworkInfo() {
		return networkInfo;
	}

	@Embedded
	public IDCAreaInfo getAreaInfo() {
		return areaInfo;
	}

	@OneToMany(mappedBy = "idc", cascade = CascadeType.ALL)
	public List<IDCPartner> getPartners() {
		return partners;
	}

	@OneToMany(mappedBy = "idc", cascade = CascadeType.ALL)
	public List<IDCSwitch> getSwitchs() {
		return switchs;
	}

	@OneToMany(mappedBy = "idc", cascade = CascadeType.ALL)
	public List<IDCIP> getIdcips() {
		return idcips;
	}

	@OneToMany(mappedBy = "idc", cascade = CascadeType.ALL)
	public List<IDCCabinet> getCabinets() {
		return cabinets;
	}

	public void setCabinets(List<IDCCabinet> cabinets) {
		this.cabinets = cabinets;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}

	public void setIdcLeaseType(IDCLeaseType idcLeaseType) {
		this.idcLeaseType = idcLeaseType;
	}

	public void setHosting(IDCHosting hosting) {
		this.hosting = hosting;
	}

	public void setNetworkInfo(IDCNetworkInfo networkInfo) {
		this.networkInfo = networkInfo;
	}

	public void setAreaInfo(IDCAreaInfo areaInfo) {
		this.areaInfo = areaInfo;
	}

	public void setPartners(List<IDCPartner> partners) {
		this.partners = partners;
	}

	public void setSwitchs(List<IDCSwitch> switchs) {
		this.switchs = switchs;
	}

	public void setIdcips(List<IDCIP> idcips) {
		this.idcips = idcips;
	}

}
