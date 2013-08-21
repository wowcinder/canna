package xdata.etl.web.client.ui;

import java.io.Serializable;

import xdata.etl.web.shared.entity.RpcEntity;

import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class RpcEntityCenterView<K extends Serializable, V extends RpcEntity<K>>
		extends VerticalLayoutContainer implements CenterView {
	private CenterViewConfig centerViewConfig;

	@Override
	public CenterViewConfig getCenterViewConfig() {
		return this.centerViewConfig;
	}

	@Override
	public void setCenterViewConfig(CenterViewConfig centerViewConfig) {
		this.centerViewConfig = centerViewConfig;
	}

}
