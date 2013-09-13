package org.hibernate.test.annotations.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class T2 implements Serializable {

	private String version;
	private Integer id;
	private String two;

	private T1 t1;

	@Id
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(insertable = false, updatable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(insertable = false, updatable = false)
	public String getTwo() {
		return two;
	}

	public void setTwo(String two) {
		this.two = two;
	}

	@ManyToOne
	@JoinColumns({ @JoinColumn(referencedColumnName = "id", name = "id"),
			@JoinColumn(referencedColumnName = "two", name = "two") })
	@NotFound(action = NotFoundAction.IGNORE)
	public T1 getT1() {
		return t1;
	}

	public void setT1(T1 t1) {
		this.t1 = t1;
	}

}
