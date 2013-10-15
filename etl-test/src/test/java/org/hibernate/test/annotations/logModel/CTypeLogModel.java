package org.hibernate.test.annotations.logModel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
@Entity
public class CTypeLogModel extends LogModelBase {
	private static final long serialVersionUID = 5087339932449259266L;
	private String name;
	private List<CTypeLogModelVersion> versions;

	@NotNull
	@Length(min = 1, max = 100)
	@Column(nullable = false, unique = true, length = 100)
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "model")
	public List<CTypeLogModelVersion> getVersions() {
		return versions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersions(List<CTypeLogModelVersion> versions) {
		this.versions = versions;
	}

}
