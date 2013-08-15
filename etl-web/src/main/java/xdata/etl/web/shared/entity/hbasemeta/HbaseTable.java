/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.hbasemeta;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
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
	@Column(length = 20)
	@NotNull
	@Length(min = 1, max = 20)
	private String shortname;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ts", nullable = false, updatable = false, columnDefinition = "timestamp")
	private Date timestamp;

	@Column(name = "description", columnDefinition = "text")
	private String desc;
	@OneToMany(mappedBy = "table", cascade = CascadeType.REMOVE)
	@OrderBy("version")
	private List<HbaseTableVersion> versions;

	@Column(name = "create_time", nullable = false, updatable = false)
	private Date createTime;

	@PrePersist
	public void prePersist() {
		this.createTime = new Date();
	}

	public HbaseTable() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
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

	public List<HbaseTableVersion> getVersions() {
		return versions;
	}

	public void setVersions(List<HbaseTableVersion> versions) {
		this.versions = versions;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
