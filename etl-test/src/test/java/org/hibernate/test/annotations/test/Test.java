package org.hibernate.test.annotations.test;

import org.hibernate.test.annotations.TestCase;
import org.hibernate.test.annotations.test.t1.LogModel;
import org.hibernate.test.annotations.test.t1.LogModelColumn;
import org.hibernate.test.annotations.test.t1.LogModelColumnContent;
import org.hibernate.test.annotations.test.t1.LogModelPackageColumn;
import org.hibernate.test.annotations.test.t1.LogModelSubColumn;
import org.hibernate.test.annotations.test.t1.LogModelSuperColumn;
import org.hibernate.test.annotations.test.t1.LogModelVersion;
import org.hibernate.test.annotations.test.t1.c.LogModelCType;
import org.hibernate.test.annotations.test.t1.c.LogModelColumnCType;
import org.hibernate.test.annotations.test.t1.c.LogModelColumnContentCType;
import org.hibernate.test.annotations.test.t1.c.LogModelPackageColumnCType;
import org.hibernate.test.annotations.test.t1.c.LogModelSubColumnCType;
import org.hibernate.test.annotations.test.t1.c.LogModelVersionCType;
import org.hibernate.test.annotations.test.t1.json.LogModelColumnContentJson;
import org.hibernate.test.annotations.test.t1.json.LogModelColumnJson;
import org.hibernate.test.annotations.test.t1.json.LogModelJson;
import org.hibernate.test.annotations.test.t1.json.LogModelPackageColumnJson;
import org.hibernate.test.annotations.test.t1.json.LogModelSubColumnJson;
import org.hibernate.test.annotations.test.t1.json.LogModelVersionJson;

public class Test extends TestCase {

	public void testT1() {

	}

	protected Class[] getAnnotatedClasses() {
		return new Class[] { LogModel.class, LogModelColumn.class,
				LogModelSuperColumn.class, LogModelColumnContent.class,
				LogModelVersion.class, LogModelPackageColumn.class,
				LogModelSubColumn.class, LogModelCType.class,
				LogModelColumnCType.class, LogModelColumnContentCType.class,
				LogModelVersionCType.class, LogModelPackageColumnCType.class,
				LogModelSubColumnCType.class, LogModelJson.class,
				LogModelColumnJson.class, LogModelColumnContentJson.class,
				LogModelVersionJson.class, LogModelPackageColumnJson.class,
				LogModelSubColumnJson.class };
	}
}
