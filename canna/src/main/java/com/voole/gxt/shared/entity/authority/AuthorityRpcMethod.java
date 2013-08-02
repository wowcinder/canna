package com.voole.gxt.shared.entity.authority;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.sencha.gxt.data.shared.TreeStore.TreeNode;
import com.voole.gxt.shared.entity.CannaTreeNode;

@Entity
@Table(name = "authority_rpc_method")
public class AuthorityRpcMethod extends CannaTreeNode implements Serializable {
	private static final long serialVersionUID = -4844431604861666174L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 40)
	private String name;
	@Column(length = 150, name = "pars")
	private String parsTypeStr;
	@Column(length = 32, unique = true)
	private String token;
	@ManyToOne
	@JoinColumn(name = "sid", nullable = false)
	private AuthorityRpcService service;
	@Column(name = "is_always_pass")
	@Type(type = "boolean")
	private boolean isAlwaysPass = false;
	@ManyToMany
	private List<AuthorityRpcGroup> authortityRpcGroups;

	public AuthorityRpcMethod() {
	}

	public AuthorityRpcMethod(Long id, String name, String parsTypeStr,
			Long sid, String simpleName) {
		this.id = id;
		this.name = name;
		this.parsTypeStr = parsTypeStr;
		if (sid != null) {
			AuthorityRpcService service = new AuthorityRpcService();
			service.setId(sid);
			service.setSimpleName(simpleName);
			this.service = service;
		}
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getParsTypeStr() {
		return parsTypeStr;
	}

	public String getToken() {
		return token;
	}

	public AuthorityRpcService getService() {
		return service;
	}

	public List<AuthorityRpcGroup> getAuthortityRpcGroups() {
		return authortityRpcGroups;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParsTypeStr(String parsTypeStr) {
		this.parsTypeStr = parsTypeStr;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setService(AuthorityRpcService service) {
		this.service = service;
	}

	public void setAuthortityRpcGroups(
			List<AuthorityRpcGroup> authortityRpcGroups) {
		this.authortityRpcGroups = authortityRpcGroups;
	}

	public boolean isAlwaysPass() {
		return isAlwaysPass;
	}

	public void setAlwaysPass(boolean isAlwaysPass) {
		this.isAlwaysPass = isAlwaysPass;
	}

	public String getLabel() {
		String str = this.service.getSimpleName() + ".";
		str += this.name + "(";
		if (this.parsTypeStr != null) {
			str += this.parsTypeStr;
		}
		str += ")";
		return str;
	}

	@Override
	public List<? extends TreeNode<CannaTreeNode>> getChildren() {
		return null;
	}

	@Override
	public void setChildren(List<? extends TreeNode<CannaTreeNode>> list) {

	}

	@Override
	public String getTreeLabel() {
		return getName() + "(" + getParsTypeStr() + ")";
	}

	@Override
	public void setTreeLabel(String treeLabel) {
		// setName(treeLabel);
	}

}
