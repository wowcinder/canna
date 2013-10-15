package org.hibernate.test.annotations.logModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {
		"groupColumn_id", "pos" }) })
public class CTypeLogModelColumn extends LogModelBase {

	private static final long serialVersionUID = -9028881995182453040L;
	private Integer pos;
	private CTypeLogModelGroupColumn groupColumn;

	@ManyToOne
	public CTypeLogModelGroupColumn getGroupColumn() {
		return groupColumn;
	}

	@NotNull
	@Column(nullable = false)
	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public void setGroupColumn(CTypeLogModelGroupColumn groupColumn) {
		this.groupColumn = groupColumn;
	}
}
