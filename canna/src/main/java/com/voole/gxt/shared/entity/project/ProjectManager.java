package com.voole.gxt.shared.entity.project;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.voole.gxt.shared.entity.CannaEntity;

@Entity
@Table(name = "project_manager")
public class ProjectManager implements Serializable , CannaEntity{

	private static final long serialVersionUID = -6117787125732711509L;
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
	@Column(length = 20)
	private String email;
	@OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
	private Set<Project> projects;

	public ProjectManager() {
	}

	public ProjectManager(Long id, String name) {
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
