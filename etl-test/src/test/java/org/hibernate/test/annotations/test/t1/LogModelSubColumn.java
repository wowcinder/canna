package org.hibernate.test.annotations.test.t1;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class LogModelSubColumn<Content extends LogModelColumnContent, PackageColumn extends LogModelPackageColumn<?, Content, ?>>
		extends LogModelSuperColumn<Content> {

	private PackageColumn packageColumn;

	@ManyToOne(targetEntity = LogModelPackageColumn.class)
	public PackageColumn getPackageColumn() {
		return packageColumn;
	}

	public void setPackageColumn(PackageColumn packageColumn) {
		this.packageColumn = packageColumn;
	}

	@Override
	@Transient
	public LogModelType getMtype() {
		if (packageColumn != null) {
			return packageColumn.getMtype();
		}
		return null;
	}

}
