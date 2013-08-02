package com.voole.gxt.client.canna.editor;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.voole.gxt.client.canna.CannaValidatorFactory;
import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.rpcclient.callback.compose.EditorCallback;

public abstract class CannaEditor<T> implements Editor<T>, EditorCallback<T> {

	public static interface EditorAddHanlder<T> {
		void postAdd(T t);
	}

	public static interface EditorUpdateHanlder<T> {
		void postUpdate(T t);
	}

	public static interface EditorCancelHanlder {
		void postCancel();
	}

	public static class CannaEditorWindow extends Window {
		private EditorCancelHanlder cancelHanlder;

		@Override
		protected void initTools() {
			super.initTools();
			if (isClosable()) {
				closeBtn.addSelectHandler(new SelectHandler() {
					@Override
					public void onSelect(SelectEvent event) {
						if (cancelHanlder != null) {
							cancelHanlder.postCancel();
						}
					}
				});
			}
		}

		public void addCloseBtnHanlder(final EditorCancelHanlder cancelHanlder) {
			this.cancelHanlder = cancelHanlder;
		}
	}

	@Ignore
	protected CannaRpcClient<T> rpc;
	@Ignore
	protected CannaEditorWindow root = new CannaEditorWindow();
	@Ignore
	protected boolean isAdd = false;
	@Ignore
	protected TextButton addBt = new TextButton("Save");;
	@Ignore
	protected TextButton cancelBt = new TextButton("Cancel");
	@Ignore
	protected EditorAddHanlder<T> addHanlder;
	@Ignore
	protected EditorUpdateHanlder<T> updateHanlder;
	@Ignore
	protected EditorCancelHanlder cancelHanlder;

	public CannaEditor(EditorAddHanlder<T> editorAddHanlder,
			EditorUpdateHanlder<T> editorUpdateHanlder) {
		this();
		if (editorAddHanlder != null) {
			setAddHanlder(editorAddHanlder);
		}
		if (editorUpdateHanlder != null) {
			setUpdateHanlder(editorUpdateHanlder);
		}
	}

	private HandlerRegistration showHandlerHr;

	public CannaEditor() {
		root.setButtonAlign(BoxLayoutPack.END);
		root.addButton(addBt);
		root.addButton(cancelBt);
		root.setModal(true);
		showHandlerHr = root.addShowHandler(new ShowHandler() {
			@Override
			public void onShow(ShowEvent event) {
				showHandlerHr.removeHandler();
				root.setMinHeight(root.getElement().getHeight(true));
			}
		});
		initView();

		cancelBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if (cancelHanlder != null) {
					cancelHanlder.postCancel();
				}
				root.hide();
			}
		});
		addBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				preAction();
				if (isAdd()) {
					doAdd();
				} else {
					doUpdate();
				}
				postAction();
			}
		});
	}

	protected void preAction() {
		addBt.disable();
	}

	protected void postAction() {
	}

	protected void addBtEnabled() {
		addBt.enable();
	}

	public void edit(T t) {
		isAdd = false;
		refreshHeadingText();
		doEdit(t);
		show();
	}

	public void add() {
		isAdd = true;
		refreshHeadingText();
		toAdd();
		show();
	}

	protected void refreshHeadingText() {
		root.setHeadingText(getHeadingText());
	}

	protected abstract void initView();

	protected void doAdd() {
		T t = flush();
		if (isViald(t)) {
			rpc.save(flush(), this);
		}
	}

	protected void doUpdate() {
		T t = flush();
		if (isViald(t)) {
			rpc.update(flush(), this);
		}
	}

	public boolean isViald(T t) {
		Set<ConstraintViolation<T>> sets = CannaValidatorFactory
				.getCannaValidator().validate(t);
		if (!sets.isEmpty()) {
			String sb = "";
			for (ConstraintViolation<?> cv : sets) {
				if (!"".equals(sb)) {
					sb += "<br />";
				}
				sb += cv.getPropertyPath() + " " + cv.getMessage();
			}
			Info.display("操作失败", sb);
			addBtEnabled();
			return false;
		}
		return true;
	}

	protected abstract T flush();

	public abstract void toAdd();

	public abstract void doEdit(T t);

	public abstract String getBaseHeadingText();

	public void postAdd(T t) {
		if (addHanlder == null) {
			Info.display("警告", "addHanlder is null");
			return;
		}
		if (t != null) {
			addHanlder.postAdd(t);
			hide();
		}
		addBtEnabled();
	}

	public void postUpdate(T t) {
		if (updateHanlder == null) {
			Info.display("警告", "updateHanlder is null");
			return;
		}
		if (t != null) {
			updateHanlder.postUpdate(t);
			hide();
		}
		addBtEnabled();
	}

	public String getAddHeadingText() {
		return "添加" + getBaseHeadingText();
	}

	public String getUpdateHeadingText() {
		return "修改" + getBaseHeadingText();
	}

	public String getHeadingText() {
		if (isAdd()) {
			return getAddHeadingText();
		}
		return getUpdateHeadingText();
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setAddHanlder(EditorAddHanlder<T> addHanlder) {
		this.addHanlder = addHanlder;
	}

	public void setUpdateHanlder(EditorUpdateHanlder<T> updateHanlder) {
		this.updateHanlder = updateHanlder;
	}

	public void setCancelHanlder(EditorCancelHanlder cancelHanlder) {
		this.cancelHanlder = cancelHanlder;
		this.root.addCloseBtnHanlder(cancelHanlder);
	}

	public void hide() {
		root.hide();
	}

	public void show() {
		root.show();
	}

	public EditorAddHanlder<T> getAddHanlder() {
		return addHanlder;
	}

	public EditorUpdateHanlder<T> getUpdateHanlder() {
		return updateHanlder;
	}
	
}
