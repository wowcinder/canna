package org.hibernate.test.annotations.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
//@Table(name = "t1", uniqueConstraints = { @UniqueConstraint(name = "u_index", columnNames = {
//		"one", "two" }) })
public class T1 implements Serializable {
	private Integer id;
	private String one;
	private String two;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 20)
	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		this.one = one;
	}

	@Column(length = 20)
	public String getTwo() {
		return two;
	}

	public void setTwo(String two) {
		this.two = two;
	}

}
