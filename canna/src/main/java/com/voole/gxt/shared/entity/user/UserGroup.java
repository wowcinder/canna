package com.voole.gxt.shared.entity.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.voole.gxt.shared.entity.CannaEntity;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;

@Entity
@Table(name = "user_group")
public class UserGroup implements Serializable, CannaEntity {
	private static final long serialVersionUID = 7747409497289967107L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 40)
	@NotNull
	@Length(min = 1, max = 40)
	private String name;

	@ManyToMany
	@NotNull
	@Size(min = 1)
	@JoinTable(name = "user_group_link_authority_rpc_group", joinColumns = { @JoinColumn(name = "user_group_id") }, inverseJoinColumns = { @JoinColumn(name = "authority_group_id") })
	private List<AuthorityRpcGroup> authGroups;
	@OneToMany(mappedBy = "group")
	private List<User> users;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<AuthorityRpcGroup> getAuthGroups() {
		return authGroups;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthGroups(List<AuthorityRpcGroup> authGroups) {
		this.authGroups = authGroups;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
