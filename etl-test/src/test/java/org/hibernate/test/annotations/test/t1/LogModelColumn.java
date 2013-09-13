package org.hibernate.test.annotations.test.t1;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class LogModelColumn<Version extends LogModelVersion, Content extends LogModelColumnContent>
		extends LogModelSuperColumn<Content> {

	private String name;
	private Version version;

	public String getName() {
		return name;
	}

	@ManyToOne(targetEntity = LogModelVersion.class)
	@JoinColumns({ @JoinColumn(referencedColumnName = "id"),
			@JoinColumn(referencedColumnName = "mtype", name = "mtype") })
	public Version getVersion() {
		return version;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	private LogModelType mtype;

	@Enumerated
	@javax.persistence.Column(insertable = false, updatable = false)
	public LogModelType getMtype() {
		return mtype;
	}

	public void setMtype(LogModelType mtype) {
		this.mtype = mtype;
	}

}
