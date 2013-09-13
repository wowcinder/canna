package org.hibernate.test.annotations.test;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class T2IdClass implements Serializable {
	private String version;
	private Integer id;
	private String two;

	public String getVersion() {
		return version;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTwo() {
		return two;
	}

	public void setTwo(String two) {
		this.two = two;
	}

}
