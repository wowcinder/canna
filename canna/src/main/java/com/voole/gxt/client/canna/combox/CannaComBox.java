package com.voole.gxt.client.canna.combox;

import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.voole.gxt.client.canna.editor.CannaEditor;
import com.voole.gxt.client.canna.editor.CannaEditor.EditorAddHanlder;
import com.voole.gxt.client.canna.editor.CannaEditor.EditorCancelHanlder;
import com.voole.gxt.client.rpcclient.callback.GetCallback;

public abstract class CannaComBox<T> extends SimpleComboBox<T> implements
		GetCallback<List<T>>, EditorAddHanlder<T>, EditorCancelHanlder {
	protected boolean isAddabled = false;
	protected T lastSelected = null;
	protected HandlerRegistration addHr;

	protected static final String ADD_TEXT = "添加...";
	protected static final long ADD_ID = -1L;

	public CannaComBox(LabelProvider<? super T> labelProvider) {
		this(true, true, labelProvider);
	}

	public CannaComBox(boolean isAuto, boolean isAddabled,
			LabelProvider<? super T> labelProvider) {
		super(labelProvider);
		this.setTriggerAction(TriggerAction.ALL);
		this.isAddabled = isAddabled;
		if (isAuto) {
			autoGetStore();
		}
	}

	public abstract void autoGetStore();

	public abstract T getAddItem();

	public abstract CannaEditor<T> getEditor();

	public abstract boolean isAddItem(T t);

	@Override
	public void postGet(List<T> t) {
		this.getStore().clear();
		getStore().addAll(t);
		if (isAddabled()) {
			T l = getAddItem();
			getStore().add(l);
			if (addHr == null) {
				addHr = addSelectionHandler(new SelectionHandler<T>() {
					@Override
					public void onSelection(SelectionEvent<T> event) {
						T selectItem = event.getSelectedItem();
						if (isAddItem(selectItem)) {
							openAddWindow();
						} else {
							setLastSelected(selectItem);
						}
					}
				});
			}
		}
	}

	public void openAddWindow() {
		CannaEditor<T> le = getEditor();
		le.setAddHanlder(this);
		le.setCancelHanlder(this);
		le.add();
	}

	@Override
	public void postAdd(T t) {
		getStore().add(0, t);
		setValue(t);
	}

	@Override
	public void postCancel() {
		setValue(getLastSelected());
	}

	public boolean isAddabled() {
		return isAddabled;
	}

	public void setAddabled(boolean isAddabled) {
		this.isAddabled = isAddabled;
	}

	public T getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(T lastSelected) {
		this.lastSelected = lastSelected;
	}
}
