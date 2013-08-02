package com.voole.gxt.shared.entity.idc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Type;

import com.voole.gxt.shared.entity.metadata.network.NetworkType;

@Embeddable
@AccessType("property")
public class IDCNetworkInfo implements Serializable{
	private static final long serialVersionUID = -1011583350529281905L;
	private int bandwidth;
	private NetworkType networkType;
	private boolean isBGP = false;

	public IDCNetworkInfo() {
	}

	public int getBandwidth() {
		return bandwidth;
	}

	@ManyToOne
	@JoinColumn(name = "network_type_id")
	public NetworkType getNetworkType() {
		return networkType;
	}

	@Column(name = "is_bgp")
	@Type(type = "boolean")
	public boolean isBGP() {
		return isBGP;
	}

	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}

	public void setNetworkType(NetworkType networkType) {
		this.networkType = networkType;
	}

	public void setBGP(boolean isBGP) {
		this.isBGP = isBGP;
	}

}
