/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.authority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.web.shared.entity.RpcEntity;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@Entity
@Table(name = "authority")
public class Authority implements RpcEntity<String> {
	private static final long serialVersionUID = -7252452619145327784L;
	@Id
	@Column(name = "token")
	private String id;
	@Column(length = 20, nullable = false)
	@Length(min = 1, max = 20)
	@NotNull
	private String name;
	private Integer displayOrder;
	@ManyToOne
	@JoinColumn(name = "gid")
	private AuthorityGroup group;

	@Override
	public void setId(String k) {
		this.id = k;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public AuthorityGroup getGroup() {
		return group;
	}

	public void setGroup(AuthorityGroup group) {
		this.group = group;
	}

}
