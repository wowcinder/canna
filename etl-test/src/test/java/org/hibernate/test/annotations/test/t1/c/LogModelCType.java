package org.hibernate.test.annotations.test.t1.c;

import javax.persistence.Entity;

import org.hibernate.test.annotations.test.t1.LogModel;
import org.hibernate.test.annotations.test.t1.LogModelType;
@Entity
public class LogModelCType extends LogModel<LogModelVersionCType> {
	public LogModelCType() {
		setMtype(LogModelType.C_TYPE);
	}
}
