package com.voole.gxt.shared.entity.project;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.voole.gxt.shared.entity.CannaEntity;

@Entity
@Table(name = "project_leader")
public class ProjectLeader implements Serializable , CannaEntity{
	private static final long serialVersionUID = -2543715405937346962L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 20)
	@NotNull
	private String name;
	@Column(length = 11)
	private String mobile;
	@Column(length = 10)
	private String extNum;
	@Column(length = 30)
	private String email;
	@OneToMany(mappedBy = "leader", fetch = FetchType.LAZY)
	private Set<Project> projects;

	public ProjectLeader() {
	}

	public ProjectLeader(long id, String name) {
		this.id = id;
		this.name = name;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getExtNum() {
		return extNum;
	}

	public void setExtNum(String extNum) {
		this.extNum = extNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

}
