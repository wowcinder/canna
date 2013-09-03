/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import xdata.etl.web.shared.entity.IdentityRpcEntity;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_2_hbase_table_mapping")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "btype", length = 20)
public class BusinessToHbaseTableMapping extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = 6554099088661273243L;
	@ManyToOne
	@JoinColumn(name = "hbase_table_version_id")
	private HbaseTableVersion hbaseTableVersion;

	@OneToMany(mappedBy = "mapping")
	protected List<BusinessColumn> columns;

	public BusinessToHbaseTableMapping() {
	}

	public List<? extends BusinessColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<BusinessColumn> columns) {
		this.columns = columns;
	}

	public HbaseTableVersion getHbaseTableVersion() {
		return hbaseTableVersion;
	}

	public void setHbaseTableVersion(HbaseTableVersion hbaseTableVersion) {
		this.hbaseTableVersion = hbaseTableVersion;
	}

}
