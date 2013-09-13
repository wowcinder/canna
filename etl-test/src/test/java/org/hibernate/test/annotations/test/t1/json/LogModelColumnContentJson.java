package org.hibernate.test.annotations.test.t1.json;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.test.annotations.test.t1.LogModelColumnContent;
import org.hibernate.test.annotations.test.t1.LogModelSubColumn;

@Entity
public class LogModelColumnContentJson extends
		LogModelColumnContent<LogModelSubColumn> {
	private String path;

	@Column(length = 150)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
