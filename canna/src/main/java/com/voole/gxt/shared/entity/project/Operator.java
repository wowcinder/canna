package com.voole.gxt.shared.entity.project;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.voole.gxt.shared.entity.CannaEntity;

@Entity
@Table(name = "operator")
public class Operator implements Serializable , CannaEntity{
	private static final long serialVersionUID = -6711480272675137462L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 20)
	@NotNull
	private String name;

	public Operator() {
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

}
