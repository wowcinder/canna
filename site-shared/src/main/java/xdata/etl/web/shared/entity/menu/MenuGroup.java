/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.menu;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Entity
@Table(name = "menu_group")
@PrimaryKeyJoinColumn(name = "id")
public class MenuGroup extends MenuNode {
	private static final long serialVersionUID = 2138324039371528785L;
	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	private List<MenuNode> nodes;

	public List<MenuNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuNode> nodes) {
		this.nodes = nodes;
	}

}
