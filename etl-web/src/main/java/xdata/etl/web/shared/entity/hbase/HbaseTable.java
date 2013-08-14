/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.hbase;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.web.shared.entity.IdentityRpcEntity;

/**
 * @author XuehuiHe
 * @date 2013年8月14日
 */
@Entity
@Table(name = "hbase_table")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class HbaseTable extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = -5625914468739750008L;
	@Column(length = 40, unique = true)
	@NotNull
	@Length(min = 1, max = 40)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ts", nullable = false, updatable = false, columnDefinition = "timestamp")
	private Date timestamp;

	@Column(name = "description", columnDefinition = "text")
	private String desc;

	public HbaseTable() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
