package xdata.etl.web.client.common.combox;

import java.util.List;

import xdata.etl.web.client.common.editer.EtlEditor;
import xdata.etl.web.client.gwt.GwtCallBack;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;

public class EtlComBox<V> extends ComboBox<V> {

	public abstract static class EtlComBoxDataInitor<V> {
		private GwtCallBack<List<V>> initback;

		protected abstract void run();

		public GwtCallBack<List<V>> getInitBack() {
			return initback;
		}
	}

	public interface AddItem<V> {
		V getAddItem();

		boolean isAddItem(V v);
	}

	private EtlComBoxDataInitor<V> dataInitor;

	private EtlEditor<V> addEditor;

	private AddItem<V> addItem;

	private boolean isInited = false;

	protected V lastSelected = null;

	public EtlComBox(ModelKeyProvider<V> keyProvider,
			LabelProvider<? super V> labelProvider) {
		super(new ListStore<V>(keyProvider), labelProvider);
		setEditable(false);
		setForceSelection(true);
		setTriggerAction(TriggerAction.ALL);
		this.addShowHandler(new ShowHandler() {
			@Override
			public void onShow(ShowEvent event) {
				init();
			}
		});
	}

	public void init() {
		if (!isInited && dataInitor != null) {
			dataInitor.run();
			isInited = true;
		} else {
			setValue(null);
			lastSelected = null;
		}
	}

	public GwtCallBack<List<V>> getInitCallBack() {
		return new GwtCallBack<List<V>>() {
			@Override
			public void _call(List<V> t) {
				initData(t);
			}
		};
	}

	public void initData(List<V> t) {
		this.getStore().clear();
		getStore().addAll(t);
		if (addEditor != null && addItem != null) {
			V v = addItem.getAddItem();
			getStore().add(v);
			this.addSelectionHandler(new SelectionHandler<V>() {
				@Override
				public void onSelection(SelectionEvent<V> event) {
					V selectItem = event.getSelectedItem();
					if (addItem.isAddItem(selectItem)) {
						addEditor.add();
					} else {
						setLastSelected(selectItem);
					}
				}
			});

		}
	}

	public void setDataInitor(EtlComBoxDataInitor<V> dataInitor) {
		this.dataInitor = dataInitor;
		this.dataInitor.initback = getInitCallBack();
	}

	public void setAddEditor(EtlEditor<V> addEditor, AddItem<V> addItem) {
		this.addEditor = addEditor;
		this.addEditor.setAddCallBack(new GwtCallBack<V>() {
			@Override
			public void _call(V t) {
				getStore().add(0, t);
				setValue(t);
			}

			@Override
			public void clean() {
				super.clean();
				setValue(getLastSelected());
			}
		});
		this.addItem = addItem;
	}

	public V getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(V lastSelected) {
		this.lastSelected = lastSelected;
	}

}
