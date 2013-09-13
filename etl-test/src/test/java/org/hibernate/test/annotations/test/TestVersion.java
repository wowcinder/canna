package org.hibernate.test.annotations.test;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TestVersion<T extends TestModel> implements Serializable {
	private Integer id;
	private T model;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@ManyToOne(targetEntity = TestModel.class)
	public T getModel() {
		return model;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setModel(T model) {
		this.model = model;
	}

}
