package xdata.etl.web.client.ui.businessmeta.editor.c;

import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.shared.BusinessType.BusinessColumnType;
import xdata.etl.web.shared.entity.businessmeta.c.CTypeBusinessColumn;
import xdata.etl.web.shared.entity.businessmeta.c.SimpleCTypeBusinessColumn;
import xdata.etl.web.shared.entity.businessmeta.c.SubMappingCTypeBusinessColumn;

public class CTypeBusinessColumnAdapterEditor extends
		RpcEntitySimpleEditor<Integer, CTypeBusinessColumn> {

	private SimpleCTypeBusinessColumnEditor simpleEditor;
	private SubMappingCTypeBusinessColumnEditor subMappingEditor;

	private BusinessColumnType type;

	public CTypeBusinessColumnAdapterEditor() {
		simpleEditor = new SimpleCTypeBusinessColumnEditor(null);
		subMappingEditor = new SubMappingCTypeBusinessColumnEditor(null);
	}

	@Override
	protected CTypeBusinessColumn newInstance() {
		return null;
	}

	@Override
	public void add() {
		if (type == BusinessColumnType.SIMPLE) {
			simpleEditor.add();
		} else {
			subMappingEditor.add();
		}
	}

	@Override
	public void edit(CTypeBusinessColumn v) {
		if (v instanceof SimpleCTypeBusinessColumn) {
			simpleEditor.edit((SimpleCTypeBusinessColumn) v);
		} else {
			subMappingEditor.edit((SubMappingCTypeBusinessColumn) v);
		}
	}

	public void setType(BusinessColumnType type) {
		this.type = type;
	}

	@Override
	public void setAddCallBack(
			final GwtCallBack<CTypeBusinessColumn> addCallBack) {
		simpleEditor
				.setAddCallBack(new GwtCallBack<SimpleCTypeBusinessColumn>() {
					@Override
					protected void _call(SimpleCTypeBusinessColumn t) {
						addCallBack.call(t);
					}

					@Override
					public void clean() {
						addCallBack.clean();
					}
				});

		subMappingEditor
				.setAddCallBack(new GwtCallBack<SubMappingCTypeBusinessColumn>() {

					@Override
					protected void _call(SubMappingCTypeBusinessColumn t) {
						addCallBack.call(t);
					}

					@Override
					public void clean() {
						addCallBack.clean();
					}
				});
	}

	@Override
	public void setUpdateCallBack(
			final GwtCallBack<CTypeBusinessColumn> updateCallBack) {
		simpleEditor
				.setUpdateCallBack(new GwtCallBack<SimpleCTypeBusinessColumn>() {

					@Override
					protected void _call(SimpleCTypeBusinessColumn t) {
						addCallBack.call(t);
					}

					@Override
					public void clean() {
						addCallBack.clean();
					}
				});

		subMappingEditor
				.setUpdateCallBack(new GwtCallBack<SubMappingCTypeBusinessColumn>() {

					@Override
					protected void _call(SubMappingCTypeBusinessColumn t) {
						addCallBack.call(t);
					}

					@Override
					public void clean() {
						addCallBack.clean();
					}
				});
	}

}
