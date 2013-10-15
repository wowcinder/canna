package org.hibernate.test.annotations.logModel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "version",
		"model_id" }) })
public class CTypeLogModelVersion extends LogModelBase {
	private static final long serialVersionUID = 2719663842833442034L;
	private String version;
	private CTypeLogModel model;
	private CTypeLogModelGroupColumn rootNode;

	public CTypeLogModelVersion() {
		rootNode = new CTypeLogModelGroupColumn();
		rootNode.setPos(0);
	}

	@Length(min = 1, max = 50)
	@NotNull
	@Column(nullable = false, length = 50)
	public String getVersion() {
		return version;
	}

	@ManyToOne(optional = false)
	@NotNull
	public CTypeLogModel getModel() {
		return model;
	}

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "root_node_id", nullable = false)
	public CTypeLogModelGroupColumn getRootNode() {
		return rootNode;
	}

	public void setRootNode(CTypeLogModelGroupColumn rootNode) {
		this.rootNode = rootNode;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setModel(CTypeLogModel model) {
		this.model = model;
	}
}
