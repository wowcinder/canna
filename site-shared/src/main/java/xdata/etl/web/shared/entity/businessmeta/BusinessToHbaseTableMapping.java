/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import xdata.etl.web.shared.entity.IdentityRpcEntity;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_2_hbase_table_mapping")
@Inheritance(strategy = InheritanceType.JOINED)
public class BusinessToHbaseTableMapping extends IdentityRpcEntity<Integer> {

	private static final long serialVersionUID = 6554099088661273243L;
	@OneToMany(mappedBy="mapping")
	protected List<BusinessColumn> columns;

	public BusinessToHbaseTableMapping() {
	}

	public List<? extends BusinessColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<BusinessColumn> columns) {
		this.columns = columns;
	}

}
