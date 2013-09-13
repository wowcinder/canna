package org.hibernate.test.annotations.test.t1;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LogModelSuperColumn<Content extends LogModelColumnContent> {
	private Integer id;

	private Content content;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@OneToOne(targetEntity = LogModelColumnContent.class)
	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Transient
	public abstract LogModelType getMtype();

}
