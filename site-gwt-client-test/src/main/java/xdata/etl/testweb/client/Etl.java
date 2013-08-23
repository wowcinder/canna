package xdata.etl.testweb.client;

import java.io.Serializable;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class Etl implements EntryPoint {

	@Override
	public void onModuleLoad() {
		TreeStore<TestRecord> store = new TreeStore<TestRecord>(
				new ModelKeyProvider<TestRecord>() {

					@Override
					public String getKey(TestRecord item) {
						return item.getKey();
					}
				});
		store.add(new TestRecord("sldjflsjl"));
		Tree<TestRecord, String> tree = new Tree<TestRecord, String>(store,
				new ValueProvider<TestRecord, String>() {

					@Override
					public String getValue(TestRecord object) {
						return object.getKey();
					}

					@Override
					public void setValue(TestRecord object, String value) {

					}

					@Override
					public String getPath() {
						return null;
					}
				});
		
		RootPanel.get().add(tree);
	}

	public static class TestRecord implements Serializable {
		private static final long serialVersionUID = 190991400971013644L;
		private String key;

		public TestRecord() {
		}

		public TestRecord(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}
}
