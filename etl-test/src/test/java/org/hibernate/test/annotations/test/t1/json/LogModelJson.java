package org.hibernate.test.annotations.test.t1.json;

import javax.persistence.Entity;

import org.hibernate.test.annotations.test.t1.CTypeLogModel;
import org.hibernate.test.annotations.test.t1.LogModelType;
@Entity
public class LogModelJson extends CTypeLogModel<LogModelVersionJson> {
	public LogModelJson() {
		setMtype(LogModelType.JSON_TYPE);
	}
}
