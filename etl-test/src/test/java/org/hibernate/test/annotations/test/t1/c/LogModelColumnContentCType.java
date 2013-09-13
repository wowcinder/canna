package org.hibernate.test.annotations.test.t1.c;

import javax.persistence.Entity;

import org.hibernate.test.annotations.test.t1.LogModelColumnContent;
import org.hibernate.test.annotations.test.t1.LogModelSubColumn;

@Entity
public class LogModelColumnContentCType extends
		LogModelColumnContent<LogModelSubColumn> {
	private Integer pos;

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

}
