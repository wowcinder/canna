package org.hibernate.test.annotations.test.t1.json;

import javax.persistence.Entity;

import org.hibernate.test.annotations.test.t1.LogModel;
import org.hibernate.test.annotations.test.t1.LogModelType;
@Entity
public class LogModelJson extends LogModel<LogModelVersionJson> {
	public LogModelJson() {
		setMtype(LogModelType.JSON_TYPE);
	}
}
