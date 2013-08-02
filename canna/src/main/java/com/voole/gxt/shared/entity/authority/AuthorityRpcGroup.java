package com.voole.gxt.shared.entity.authority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.gxt.shared.entity.CannaEntity;
import com.voole.gxt.shared.entity.user.UserGroup;

@Entity
@Table(name = "authority_rpc_group")
public class AuthorityRpcGroup implements Serializable, CannaEntity {
	private static final long serialVersionUID = -5350900832747856932L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 20)
	@NotNull
	@Length(min = 1, max = 20)
	private String name;
	@ManyToMany
	@JoinTable(name = "authority_rpc_group_link_methods", joinColumns = { @JoinColumn(name = "group_id") }, inverseJoinColumns = { @JoinColumn(name = "authority_id") })
	private List<AuthorityRpcMethod> authorityRpcMethods = new ArrayList<AuthorityRpcMethod>();

	@ManyToMany
	@JoinTable(name = "user_group_link_authority_rpc_group", joinColumns = { @JoinColumn(name = "authority_group_id") }, inverseJoinColumns = { @JoinColumn(name = "user_group_id") })
	private List<UserGroup> userGroups;

	public AuthorityRpcGroup() {
	}

	public AuthorityRpcGroup(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<AuthorityRpcMethod> getAuthorityRpcMethods() {
		return authorityRpcMethods;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthorityRpcMethods(
			List<AuthorityRpcMethod> authorityRpcMethods) {
		this.authorityRpcMethods = authorityRpcMethods;
	}

	public List<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

}
