package org.hibernate.test.annotations.test.t1.json;

import javax.persistence.Entity;

import org.hibernate.test.annotations.test.t1.LogModelPackageColumn;
@Entity
public class LogModelPackageColumnJson extends
		LogModelPackageColumn<LogModelVersionJson, LogModelColumnContentJson, LogModelSubColumnJson> {

}
