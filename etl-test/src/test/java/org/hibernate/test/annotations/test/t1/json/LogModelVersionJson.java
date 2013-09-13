package org.hibernate.test.annotations.test.t1.json;

import javax.persistence.Entity;

import org.hibernate.test.annotations.test.t1.LogModelVersion;
@Entity
public class LogModelVersionJson extends LogModelVersion<LogModelJson, LogModelColumnJson> {

}
