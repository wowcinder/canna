/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.testweb.server.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.testweb.shared.entity.menu.MenuGroup;
import xdata.etl.testweb.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年8月22日
 */
@Repository
public class MenuDaoImpl extends BaseDao implements MenuDao {

	public void delete(MenuNode node) {
		Session s = getSession();
		node = (MenuNode) s.load(MenuNode.class, node.getId());
		MenuNode parent = node.getParent();
		Criteria c = s.createCriteria(MenuNode.class);
		c.add(Restrictions.eq("prev", node));
		if (parent == null) {
			c.add(Restrictions.isNull("parent"));
		} else {
			c.add(Restrictions.eq("parent", parent));
		}
		MenuNode next = (MenuNode) c.uniqueResult();
		s.delete(node);
		next.setPrev(node.getPrev());
		s.update(next);
	}

	public void delete(MenuGroup node) {
		Session s = getSession();
		node = (MenuGroup) s.createCriteria(MenuGroup.class)
				.createAlias("nodes", "nodes", Criteria.LEFT_JOIN)
				.add(Restrictions.idEq(node.getId())).uniqueResult();
		MenuNode parent = node.getParent();
		Criteria c = s.createCriteria(MenuNode.class);
		c.add(Restrictions.eq("prev", node));
		if (parent == null) {
			c.add(Restrictions.isNull("parent"));
		} else {
			c.add(Restrictions.eq("parent", parent));
		}
		MenuNode next = (MenuNode) c.uniqueResult();
		s.delete(node);
		next.setPrev(node.getPrev());
		s.update(next);
	}

	public MenuNode insert(MenuNode node) {
		Session s = getSession();
		MenuNode parent = node.getParent();
		MenuNode prev = node.getPrev();
		Criteria c = s.createCriteria(MenuNode.class);
		if (parent == null) {
			c.add(Restrictions.isNull("parent"));
		} else {
			c.add(Restrictions.eq("parent", parent));
		}
		if (prev == null) {
			c.add(Restrictions.isNull("prev"));
		} else {
			c.add(Restrictions.eq("prev", prev));
		}
		MenuNode oldPrev = (MenuNode) c.uniqueResult();

		s.save(node);
		if (oldPrev != null) {
			oldPrev.setPrev(node);
			s.merge(oldPrev);
		}
		return node;
	}

	public List<MenuNode> get() {
		Session s = getSession();
		@SuppressWarnings("unchecked")
		List<MenuNode> list = s.createCriteria(MenuNode.class).list();

		MenuListGenerator generator = new MenuListGenerator(list);
		return generator.getNodes();
	}

	public static class MenuListGenerator {
		private Map<MenuPosition, MenuNode> positionToNode;

		public MenuListGenerator(List<MenuNode> list) {
			positionToNode = new HashMap<MenuPosition, MenuNode>();
			for (MenuNode menuNode : list) {
				positionToNode.put(MenuPosition.getSelfPosition(menuNode),
						menuNode);
			}
		}

		public List<MenuNode> getNodes() {
			return getNodes(null);
		}

		public List<MenuNode> getNodes(MenuGroup parent) {
			List<MenuNode> list = new ArrayList<MenuNode>();
			MenuPosition firstPosition = MenuPosition.getFirstPosition(parent);
			MenuNode nextNode = positionToNode.get(firstPosition);
			if (nextNode == null) {
				return null;
			}
			while (nextNode != null) {
				list.add(nextNode);
				if (nextNode instanceof MenuGroup) {
					List<MenuNode> nodes = getNodes((MenuGroup) nextNode);
					((MenuGroup) nextNode).setNodes(nodes);
				}
				MenuPosition nextPosition = MenuPosition
						.getNextPosition(nextNode);
				nextNode = positionToNode.get(nextPosition);
			}

			return list;
		}
	}

	public static class MenuPosition {
		private Integer parent;
		private Integer prev;

		public MenuPosition() {
		}

		public MenuPosition(MenuGroup parent2, MenuNode prev2) {
			if (parent2 == null) {
				this.parent = 0;
			} else {
				this.parent = parent2.getId();
			}

			if (prev2 == null) {
				this.prev = 0;
			} else {
				this.prev = prev2.getId();
			}
		}

		public Integer getParent() {
			return parent;
		}

		public void setParent(Integer parent) {
			this.parent = parent;
		}

		public Integer getPrev() {
			return prev;
		}

		public void setPrev(Integer prev) {
			this.prev = prev;
		}

		public static MenuPosition getFirstPosition(MenuGroup node) {
			return new MenuPosition(node, null);
		}

		public static MenuPosition getFirstPosition() {
			return new MenuPosition(null, null);
		}

		public static MenuPosition getSelfPosition(MenuNode node) {
			return new MenuPosition(node.getParent(), node.getPrev());
		}

		public static MenuPosition getNextPosition(MenuNode node) {
			return new MenuPosition(node.getParent(), node);
		}

		@Override
		public int hashCode() {
			return getParent().hashCode() + 3 * getPrev().hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj instanceof MenuPosition) {
				MenuPosition that = (MenuPosition) obj;
				return this.getParent().equals(that.getParent())
						&& this.getPrev().equals(that.getPrev());
			}
			return false;
		}
	}

}
