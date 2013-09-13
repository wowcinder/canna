package org.hibernate.test.annotations.test.t1;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class LogModelPackageColumn<Version extends LogModelVersion, Content extends LogModelColumnContent, SubColumn extends LogModelSubColumn>
		extends LogModelColumn<Version, Content> {
	private List<SubColumn> subColumns;

	@OneToMany(mappedBy = "packageColumn", targetEntity = LogModelSubColumn.class)
	public List<SubColumn> getSubColumns() {
		return subColumns;
	}

	public void setSubColumns(List<SubColumn> subColumns) {
		this.subColumns = subColumns;
	}

}
