/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.web.shared.entity.IdentityRpcEntity;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "btype", length = 20)
public class Business extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = -5376450072498870497L;
	@NotNull
	@Column(nullable = false, unique = true, length = 40)
	@Length(min = 1, max = 40)
	private String name;

	@OneToMany(mappedBy = "business")
	private List<BusinessVersion> versions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<? extends BusinessVersion> getVersions() {
		return versions;
	}

	public void setVersions(List<BusinessVersion> versions) {
		this.versions = versions;
	}

}
