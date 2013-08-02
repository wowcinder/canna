/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.authority;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.web.shared.entity.IdentityRpcEntity;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@Table(name = "authority_group")
@Entity
public class AuthorityGroup extends IdentityRpcEntity<Integer> {

	private static final long serialVersionUID = -1351283572556439616L;
	@Column(length = 20, unique = true, nullable = false)
	@Length(min = 1, max = 20)
	@NotNull
	private String name;

	@Column(name = "display_order")
	private Integer displayOrder;

	@OneToMany(mappedBy = "group")
	private List<Authority> authorities;

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

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

}
