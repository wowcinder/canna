package com.voole.gxt.shared.entity.authority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sencha.gxt.data.shared.TreeStore.TreeNode;
import com.voole.gxt.shared.entity.CannaTreeNode;

@Entity
@Table(name = "authority_rpc_service")
public class AuthorityRpcService extends CannaTreeNode implements Serializable {
	private static final long serialVersionUID = -2410676454694827926L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 40)
	private String name;
	@Column(length = 150)
	private String fullname;
	@Column(length = 150)
	private String simpleName;
	@Column(length = 150, nullable = false)
	private String path;
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
	private List<AuthorityRpcMethod> methods;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFullname() {
		return fullname;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public List<AuthorityRpcMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<AuthorityRpcMethod> methods) {
		this.methods = methods;
	}

	public void addMethod(AuthorityRpcMethod m) {
		if (methods == null) {
			methods = new ArrayList<AuthorityRpcMethod>();
		}
		methods.add(m);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	@Override
	public List<AuthorityRpcMethod> getChildren() {
		return methods;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setChildren(List<? extends TreeNode<CannaTreeNode>> list) {
		this.methods = (List<AuthorityRpcMethod>) list;
	}

	@Override
	public String getTreeLabel() {
		return getName();
	}

	@Override
	public void setTreeLabel(String treeLabel) {
		setName(treeLabel);
	}

}
