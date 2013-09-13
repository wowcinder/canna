package org.hibernate.test.annotations.test.t1;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class LogModel<Version extends LogModelVersion> {
	private Integer id;
	private LogModelType mtype;
	private String name;
	private List<Version> versions;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Enumerated
	public LogModelType getMtype() {
		return mtype;
	}

	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "model", targetEntity = LogModelVersion.class)
	public List<Version> getVersions() {
		return versions;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMtype(LogModelType mtype) {
		this.mtype = mtype;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

}
