package com.voole.gxt.shared.entity;

import java.util.List;

import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.TreeStore.TreeNode;

public abstract class CannaTreeNode implements
		TreeStore.TreeNode<CannaTreeNode>, CannaEntity {

	@Override
	public CannaTreeNode getData() {
		return this;
	}

	public abstract void setChildren(
			List<? extends TreeNode<CannaTreeNode>> list);

	public abstract String getTreeLabel();

	public abstract void setTreeLabel(String treeLabel);

}
