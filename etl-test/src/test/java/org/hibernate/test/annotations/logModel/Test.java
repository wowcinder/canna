package org.hibernate.test.annotations.logModel;

import org.hibernate.test.annotations.TestCase;

public class Test extends TestCase {

	public void test1() {

	}

	@Override
	protected Class<?>[] getAnnotatedClasses() {
		return new Class<?>[] { CTypeLogModel.class,
				CTypeLogModelVersion.class, CTypeLogModelColumn.class,
				CTypeLogModelSimpleColumn.class, CTypeLogModelGroupColumn.class };
	}

}
