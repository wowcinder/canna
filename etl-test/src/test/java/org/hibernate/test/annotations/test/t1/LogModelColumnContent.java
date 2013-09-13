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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class LogModelColumnContent<Column extends LogModelSuperColumn> {
	private Integer id;
	private Column column;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@OneToOne(targetEntity = LogModelSuperColumn.class)
	// @JoinColumns({ @JoinColumn(referencedColumnName = "id"),
	// @JoinColumn(referencedColumnName = "mtype", name = "mtype") })
	public Column getColumn() {
		return column;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setColumn(Column column) {
		this.column = column;
	}
	
	@Transient
	public LogModelType getMtype() {
		if (column != null) {
			return column.getMtype();
		}
		return null;
	}

}
