package xdata.etl.web.client.common.combox;

import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;

public abstract class EtlComBox<T> extends SimpleComboBox<T> {
	protected boolean isAddabled = false;
	protected T lastSelected = null;
	protected HandlerRegistration addHr;

	protected static final String ADD_TEXT = "添加...";
	protected static final long ADD_ID = -1L;

	public EtlComBox(LabelProvider<? super T> labelProvider) {
		this(true, true, labelProvider);
		LabelProvider<T> labelProvider2 =  new LabelProvider<T>() {
			@Override
			public String getLabel(T item) {
				return null;
			}
		};
	}

	public EtlComBox(boolean isAuto, boolean isAddabled,
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

//	public abstract CannaEditor<T> getEditor();

	public abstract boolean isAddItem(T t);

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
//		CannaEditor<T> le = getEditor();
		// le.setAddHanlder(this);
		// le.setCancelHanlder(this);
		// le.add();
	}

	public void postAdd(T t) {
		getStore().add(0, t);
		setValue(t);
	}

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
