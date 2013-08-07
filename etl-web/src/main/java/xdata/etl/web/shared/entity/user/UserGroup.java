/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.web.shared.entity.IdentityRpcEntity;
import xdata.etl.web.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
public class UserGroup extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = 6538171186264110989L;

	@Column(length = 20, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 20)
	private String name;

	@OneToMany(mappedBy = "userGroup", cascade = CascadeType.REMOVE)
	private List<User> users;
	@ManyToMany
	@JoinTable(name = "user_group_to_authority", joinColumns = @JoinColumn())
	private List<Authority> authorities;

}
