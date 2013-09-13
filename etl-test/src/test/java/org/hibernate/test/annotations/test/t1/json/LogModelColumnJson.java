package org.hibernate.test.annotations.test.t1.json;

import javax.persistence.Entity;

import org.hibernate.test.annotations.test.t1.LogModelColumn;
@Entity
public class LogModelColumnJson extends
		LogModelColumn<LogModelVersionJson, LogModelColumnContentJson> {

}
