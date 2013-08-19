package xdata.etl.web.client.common.combox;

import java.io.Serializable;

import xdata.etl.web.shared.entity.RpcEntity;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;

public class RpcEntityComBox<K extends Serializable, V extends RpcEntity<K>>
		extends EtlComBox<V> {

	public RpcEntityComBox(ModelKeyProvider<V> keyProvider,
			LabelProvider<V> labelProvider) {
		super(keyProvider, labelProvider);
	}
}
