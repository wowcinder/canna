/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.hbasemeta;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@Table(name = "hbase_table_version")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class HbaseTableVersion extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = 1172877225725071936L;

	@Column(length = 20, nullable = false)
	@NotNull
	@Length(min = 0, max = 20)
	private String version = "";

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ts", nullable = false, updatable = false, insertable = false, columnDefinition = "timestamp")
	private Date timestamp;

	@Column(name = "description", columnDefinition = "text")
	private String desc;
	@ManyToOne(optional = false)
	@JoinColumn(name = "table_id")
	@NotNull
	private HbaseTable table;
	@OneToMany(mappedBy = "version", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy("pos")
	private List<HbaseTableColumn> columns;

	@Column(name = "create_time", nullable = false, updatable = false)
	private Date createTime = new Date();

	public HbaseTableVersion() {
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
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

	public HbaseTable getTable() {
		return table;
	}

	public void setTable(HbaseTable table) {
		this.table = table;
	}

	public List<HbaseTableColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<HbaseTableColumn> columns) {
		this.columns = columns;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
