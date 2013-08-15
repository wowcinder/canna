/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.hbasemeta;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.web.shared.entity.IdentityRpcEntity;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Entity
@Table(name = "hbase_table_column")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class HbaseTableColumn extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = -7351279578902858388L;
	@Column(length = 40, nullable = false)
	@Length(min = 1, max = 40)
	@NotNull
	private String name;
	@Column(length = 20)
	@Length(min = 1, max = 40)
	private String shortname;
	@Enumerated(EnumType.STRING)
	@Column(name = "column_type", nullable = false, length = 20)
	private HbaseTableColumnType type;
	private Integer pos;
	@ManyToOne(optional = false)
	@JoinColumn(name = "version_id")
	private HbaseTableVersion version;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ts", nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp")
	private Date timestamp;
	@Column(name = "description", columnDefinition = "text")
	private String desc;

	@Column(name = "create_time", nullable = false, updatable = false)
	private Date createTime = new Date();

	public HbaseTableColumn() {
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

	public HbaseTableColumnType getType() {
		return type;
	}

	public void setType(HbaseTableColumnType type) {
		this.type = type;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public HbaseTableVersion getVersion() {
		return version;
	}

	public void setVersion(HbaseTableVersion version) {
		this.version = version;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public enum HbaseTableColumnType {
		SHORT(Short.class), INT(Integer.class), LONG(Long.class), FLOAT(
				Float.class), DOUBLE(Double.class), STRING(String.class), CHAR(
				Character.class), BOOLEAN(Boolean.class), DATE(Date.class), TIME(
				Date.class), DATETIME(Date.class), TEXT(String.class);
		Class<?> clazz;

		HbaseTableColumnType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getClazz() {
			return clazz;
		}

		public void setClazz(Class<?> clazz) {
			this.clazz = clazz;
		}

	}
}
