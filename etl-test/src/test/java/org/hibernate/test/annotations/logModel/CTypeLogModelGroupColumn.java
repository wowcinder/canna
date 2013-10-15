package org.hibernate.test.annotations.logModel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class CTypeLogModelGroupColumn extends CTypeLogModelColumn {
	private static final long serialVersionUID = 1424230533361555956L;
	private List<CTypeLogModelColumn> columns;

	@OneToMany(mappedBy = "groupColumn")
	public List<CTypeLogModelColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<CTypeLogModelColumn> columns) {
		this.columns = columns;
	}

}
