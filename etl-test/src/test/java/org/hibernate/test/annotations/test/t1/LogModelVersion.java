package org.hibernate.test.annotations.test.t1;

import java.util.List;

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
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class LogModelVersion<Model extends LogModel, Column extends LogModelColumn> {
	private Integer id;
	private String name;
	private Model model;
	private List<Column> columns;
	private LogModelType mtype;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@ManyToOne(targetEntity = LogModel.class)
	@JoinColumns({ @JoinColumn(referencedColumnName = "id"),
			@JoinColumn(name = "mtype", referencedColumnName = "mtype") })
	public Model getModel() {
		return model;
	}

	@OneToMany(mappedBy = "version", targetEntity = LogModelColumn.class)
	public List<Column> getColumns() {
		return columns;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	@Enumerated
	@javax.persistence.Column(insertable = false, updatable = false)
	public LogModelType getMtype() {
		return mtype;
	}

	public void setMtype(LogModelType mtype) {
		this.mtype = mtype;
	}

}
