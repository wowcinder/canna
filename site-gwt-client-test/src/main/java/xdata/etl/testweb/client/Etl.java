package xdata.etl.testweb.client;

import java.util.List;

import xdata.etl.testweb.shared.entity.menu.MenuGroup;
import xdata.etl.testweb.shared.entity.menu.MenuNode;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDragMoveEvent;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.DndDropEvent.DndDropHandler;
import com.sencha.gxt.dnd.core.client.Insert;
import com.sencha.gxt.dnd.core.client.TreeDragSource;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;

public class Etl implements EntryPoint {
	private static final MenuServiceAsync service = GWT
			.create(MenuService.class);
	private TreeStore<MenuNode> store;

	public void onModuleLoad() {
		FlowLayoutContainer con = new FlowLayoutContainer();
		con.addStyleName("margin-10");

		store = new TreeStore<MenuNode>(new ModelKeyProvider<MenuNode>() {

			public String getKey(MenuNode item) {
				return item.getId() + "";
			}
		});

		final Tree<MenuNode, String> tree = new Tree<MenuNode, String>(store,
				new ValueProvider<MenuNode, String>() {

					public String getValue(MenuNode object) {
						return object.getName();
					}

					public void setValue(MenuNode object, String value) {

					}

					public String getPath() {
						return null;
					}
				});
		tree.setWidth(300);
		Menu contextMenu = new Menu();

		final MenuItem insert = new MenuItem();
		insert.setText("添加Menu");

		contextMenu.add(insert);

		final MenuItem remove = new MenuItem();
		remove.setText("添加MenuGroup");

		contextMenu.add(remove);

		tree.setContextMenu(contextMenu);

		// contextMenu.addBeforeShowHandler(new BeforeShowHandler() {
		// @Override
		// public void onBeforeShow(BeforeShowEvent event) {
		// MenuNode node = tree.getSelectionModel().getSelectedItem();
		// if(node instanceof MenuGroup){
		// insert.disable();
		// }
		// }
		// });
		//
		// contextMenu.addHideHandler(new HideHandler() {
		//
		// @Override
		// public void onHide(HideEvent event) {
		// insert.enable();
		// remove.enable();
		// }
		// });

		TreeDragSource<MenuNode> source = new TreeDragSource<MenuNode>(tree);
		final TreeDropTarget<MenuNode> target = new TreeDropTarget<MenuNode>(
				tree) {
			protected void appendModel(MenuNode p, List<?> items, int index) {
				if (items.size() == 0)
					return;
				if (items.get(0) instanceof TreeStore.TreeNode) {
					@SuppressWarnings("unchecked")
					List<TreeStore.TreeNode<MenuNode>> nodes = (List<TreeStore.TreeNode<MenuNode>>) items;
					if (p == null) {
						getWidget().getStore().addSubTree(index, nodes);
					} else {
						getWidget().getStore().addSubTree(p, index, nodes);
					}
				} else {
					@SuppressWarnings("unchecked")
					List<MenuNode> models = (List<MenuNode>) items;
					if (p == null) {
						getWidget().getStore().insert(index, models);
					} else {
						getWidget().getStore().insert(p, index, models);
					}
				}
				Info.display("modify", "modify");
			}

			protected void handleAppendDrop(DndDropEvent event,
					TreeNode<MenuNode> item) {
				reset();
				return;
				// TODO not M, but TreeStore.TreeNode<M>
				/*
				List<?> models = (List<?>) event.getData();
				if (models.size() > 0) {
					MenuNode p = null;
					if (item != null) {
						p = item.getModel();
						appendModel(p, models, getWidget().getStore()
								.getChildCount(item.getModel()));
					} else {
						appendModel(p, models, getWidget().getStore()
								.getRootItems().size());
					}
					Info.display("handleAppendDrop", "handleAppendDrop");
				}*/
			}

			protected void handleInsertDrop(DndDropEvent event,
					TreeNode<MenuNode> item, int index) {
				List<?> droppedItems = (List<?>) event.getData();
				if (droppedItems.size() > 0) {
					int idx = getWidget().getStore().indexOf(item.getModel());
					idx = status == 0 ? idx : idx + 1;
					MenuNode p = getWidget().getStore().getParent(item.getModel());
					appendModel(p, droppedItems, idx);
					Info.display("handleInsertDrop", "handleInsertDrop");
				}
			}

			protected void handleInsert(DndDragMoveEvent event,
					final TreeNode<MenuNode> item) {
				Element e = getWidget().getView().getElementContainer(item);

				int height = e.getOffsetHeight();
				int mid = height / 2;
				int top = e.getAbsoluteTop();
				mid += top;
				int y = event.getDragMoveEvent().getNativeEvent().getClientY();
				boolean before = y < mid;

				boolean leaf = getWidget().isLeaf(item.getModel());

				if (((item.getModel() instanceof MenuGroup))
						&& (feedback == Feedback.BOTH || feedback == Feedback.APPEND)
						&& ((before && y > top + 4) || (!before && y < top
								+ height - 4))) {
					handleAppend(event, item);
					return;
				}
				super.handleInsert(event, item);
			}

			protected void showFeedback(DndDragMoveEvent event) {
				// TODO this might not get the right element
				final TreeNode<MenuNode> item = getWidget().findNode(
						event.getDragMoveEvent().getNativeEvent()
								.getEventTarget().<Element> cast());

				if (item == null && activeItem != null) {
					clearStyle(activeItem);
				}

				if (item != null
						&& event.getDropTarget().getWidget() == event
								.getDragSource().getWidget()) {
					@SuppressWarnings("unchecked")
					Tree<MenuNode, ?> source = (Tree<MenuNode, ?>) event
							.getDragSource().getWidget();
					List<MenuNode> list = source.getSelectionModel()
							.getSelection();
					MenuNode overModel = item.getModel();
					for (int i = 0; i < list.size(); i++) {
						MenuNode sel = list.get(i);
						if (overModel == sel) {
							Insert.get().hide();
							event.getStatusProxy().setStatus(false);
							return;
						}
						List<MenuNode> children = getWidget().getStore()
								.getAllChildren(sel);
						if (children.contains(item.getModel())) {
							Insert.get().hide();
							event.getStatusProxy().setStatus(false);
							return;
						}
					}
				}

				boolean append = feedback == Feedback.APPEND
						|| feedback == Feedback.BOTH;
				boolean insert = feedback == Feedback.INSERT
						|| feedback == Feedback.BOTH;

				if (item == null) {
					handleAppend(event, item);
				} else if (insert) {
					handleInsert(event, item);
				} else if (item.getModel() instanceof MenuGroup && append) {
					handleAppend(event, item);
				} else {
					if (activeItem != null) {
						clearStyle(activeItem);
					}
					status = -1;
					activeItem = null;
					appendItem = null;

					Insert.get().hide();
					event.getStatusProxy().setStatus(false);
				}
			}
		};
		target.addDropHandler(new DndDropHandler() {
			@Override
			public void onDrop(DndDropEvent event) {
				System.out.println(event.getData().getClass().getName());
				System.out.println(target.getWidget().findNode(
						event.getDragEndEvent().getTarget().getElement()
								.<Element> cast()));
				Info.display("drop", "drop");
			}
		});
		// target.setAllowDropOnLeaf(true);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.BOTH);

		con.add(tree);

		RootPanel.get().add(con);

		service.get(new AsyncCallback<List<MenuNode>>() {

			public void onSuccess(List<MenuNode> result) {
				initData(result);
			}

			public void onFailure(Throwable caught) {

			}
		});
	}

	protected void initData(List<MenuNode> result) {
		for (MenuNode menuNode : result) {
			initData(null, menuNode);
		}

	}
	
	public void reset(){
		List<MenuNode> list = store.getAll();
		store.clear();
		initData(list);
	}

	protected void initData(MenuNode parent, MenuNode menuNode) {
		if (menuNode == null) {
			return;
		}
		if (parent == null) {
			store.add(menuNode);
		} else {
			store.add(parent, menuNode);
		}
		if (menuNode instanceof MenuGroup) {
			List<MenuNode> nodes = ((MenuGroup) menuNode).getNodes();
			if (nodes == null || nodes.size() == 0) {
				return;
			}
			for (MenuNode node : nodes) {
				initData(menuNode, node);
			}
		}
	}
}
