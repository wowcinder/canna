package com.voole.gxt.shared.entity.project;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.voole.gxt.shared.entity.CannaEntity;

@Entity
@Table(name = "project")
public class Project implements Serializable , CannaEntity{
	private static final long serialVersionUID = 1605477561057913215L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 20)
	@NotNull
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lid")
	private ProjectLeader leader;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mid")
	private ProjectManager manager;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oid")
	private Operator operator;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	private List<ProjectRemark> remarks;

	public Project() {
	}

	public Project(long id, String name, Long lid, String lName, Long mid,
			String mName) {
		this.id = id;
		this.name = name;
		if (lid != null) {
			this.leader = new ProjectLeader();
			this.leader.setId(lid);
			this.leader.setName(lName);
		}
		if (mid != null) {
			this.manager = new ProjectManager();
			this.manager.setId(mid);
			this.manager.setName(mName);
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProjectLeader getLeader() {
		return leader;
	}

	public void setLeader(ProjectLeader leader) {
		this.leader = leader;
	}

	public ProjectManager getManager() {
		return manager;
	}

	public void setManager(ProjectManager manager) {
		this.manager = manager;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<ProjectRemark> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<ProjectRemark> remarks) {
		this.remarks = remarks;
	}

}
