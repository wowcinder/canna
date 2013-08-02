package com.voole.gxt.shared.entity.project;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.voole.gxt.shared.entity.CannaEntity;

@Entity
@Table(name = "project_remark")
public class ProjectRemark implements Serializable, CannaEntity {
	private static final long serialVersionUID = 5839621402869151700L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 150, name = "project_remark")
	private String remark;

	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;
	@ManyToOne
	@JoinColumn(name = "partner_id", nullable = false)
	private ProjectPartner partner;

	public ProjectRemark() {
	}

	public ProjectRemark(Long id) {
		this.id = id;
	}

	public ProjectRemark(Long id, String remark, Long pid, String pname,
			String pposition, String mobile, String pemail, String premark,
			Long jid) {
		this.id = id;
		this.remark = remark;
		if (pid != null) {
			ProjectPartner p = new ProjectPartner();
			p.setId(pid);
			p.setName(pname);
			p.setPosition(pposition);
			p.setMobile(mobile);
			p.setEmail(pemail);
			p.setRemark(premark);
			this.partner = p;
		}
		if (jid != null) {
			Project p = new Project();
			p.setId(jid);
			this.project = p;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectPartner getPartner() {
		return partner;
	}

	public void setPartner(ProjectPartner partner) {
		this.partner = partner;
	}

	@Override
	public int hashCode() {
		return Long.toString(getId()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProjectRemark) {
			return ((ProjectRemark) obj).getId() == getId();
		}
		return false;
	}

}
