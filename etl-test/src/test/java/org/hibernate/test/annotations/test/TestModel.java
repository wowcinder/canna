package org.hibernate.test.annotations.test;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TestModel<T extends TestVersion> implements Serializable {
	private Integer id;
	private List<T> versions;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "model", targetEntity = TestVersion.class)
	public List<T> getVersions() {
		return versions;
	}

	public void setVersions(List<T> versions) {
		this.versions = versions;
	}

}
