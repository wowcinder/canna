package com.voole.gxt.shared.entity.project;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.voole.gxt.shared.entity.CannaEntity;
import com.voole.gxt.shared.entity.Partner;

@Entity
@Table(name = "project_partner")
public class ProjectPartner extends Partner implements Serializable , CannaEntity{

	private static final long serialVersionUID = -7575031498709620558L;

	private List<ProjectRemark> remarks;

	public ProjectPartner() {
	}

	@OneToMany(mappedBy = "partner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<ProjectRemark> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<ProjectRemark> remarks) {
		this.remarks = remarks;
	}

	@Override
	public int hashCode() {
		return Long.toString(getId()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProjectPartner) {
			ProjectPartner p = (ProjectPartner) obj;
			if (p.getId() == getId()) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
}
