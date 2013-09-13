package org.hibernate.test.annotations.test.t1.c;

import javax.persistence.Entity;

import org.hibernate.test.annotations.test.t1.LogModelVersion;
@Entity
public class LogModelVersionCType extends LogModelVersion<LogModelCType, LogModelColumnCType> {

}
